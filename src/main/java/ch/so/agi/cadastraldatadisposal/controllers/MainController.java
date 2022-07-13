package ch.so.agi.cadastraldatadisposal.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ch.so.agi.cadastraldatadisposal.models.Dataset;
import ch.so.agi.cadastraldatadisposal.models.Properties;
import ch.so.agi.cadastraldatadisposal.models.Response;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import static java.util.stream.Collectors.toList;

import java.text.SimpleDateFormat;

import static java.util.stream.Collectors.collectingAndThen;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class MainController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${app.dataServiceUrl}")
    private String dataServiceUrl;

    @Value("${app.pdfMapUrl}")
    private String pdfMapUrl;

    @Value("${app.s3BaseUrl}")
    private String s3BaseUrl;

    @Value("${app.itfsoBucketName}")
    private String itfsoBucketName;

    @Value("${app.itfchBucketName}")
    private String itfchBucketName;

    @Value("${app.dxfBucketName}")
    private String dxfBucketName;
    
    @Value("${app.mopublicBucketName}")
    private String mopublicBucketName;
    
    List<Dataset> lstDatasets = new ArrayList<Dataset>();
    
    private S3Client s3;
    
    @PostConstruct
    public void doLog() {
        Region region = Region.EU_CENTRAL_1;
        s3 = S3Client.builder().region(region).build();
    }
    
    @GetMapping("/ping")
    public ResponseEntity<String> ping()  {
        return new ResponseEntity<String>("av_datenabgabe_ng: " + getHost(), HttpStatus.OK);
    }
    
    @GetMapping("/")     
    public String show(Model model) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        log.info("Start S3 request");
        
        ListObjectsRequest listObjects = ListObjectsRequest
                .builder()
                .bucket(itfsoBucketName)
                .build();

        ListObjectsResponse res = s3.listObjects(listObjects);
        List<S3Object> objects = res.contents();

        Map<String, Date> objectMap = new HashMap<>();
        for (ListIterator iterVals = objects.listIterator(); iterVals.hasNext(); ) {
            S3Object myValue = (S3Object) iterVals.next();
            Date lastModified = Date.from(myValue.lastModified());
            objectMap.put(myValue.key(), lastModified);
         }
        
        log.info("End S3 request");

        log.info("Start data service request");
        RestTemplate restTemplate = new RestTemplate();
        Response response = restTemplate.getForObject(dataServiceUrl, Response.class);

        ArrayList<Dataset> datasets = response.getFeatures().stream()
            .sorted((f1, f2) -> f1.getProperties().getGemeindename().compareTo(f2.getProperties().getGemeindename()))
            .map(f -> {
                Dataset dataset = new Dataset();
                Properties p = f.getProperties();
                dataset.setGem_name(p.getGemeindename());
                dataset.setGem_bfs(p.getBfsnr());
                
                String key = String.valueOf(p.getBfsnr())+"00.zip";
                Date date = objectMap.get(key);
                if (date == null) {
                    log.error("key not found: " + key);
                } else {
                    dataset.setLieferdatum(format.format(date));  
                }
                
                dataset.setPdf(pdfMapUrl.replace("{{BFS_NR}}", String.valueOf(p.getBfsnr())));
                dataset.setItfch(s3BaseUrl + itfchBucketName + "/"+ String.valueOf(p.getBfsnr()) + "00.itf.zip");
                dataset.setItfso(s3BaseUrl + itfsoBucketName + "/"+ String.valueOf(p.getBfsnr()) + "00.zip");
                dataset.setDxf(s3BaseUrl + dxfBucketName + "/"+ String.valueOf(p.getBfsnr()) + "00.zip");
                dataset.setShp(s3BaseUrl + mopublicBucketName + "/"+ String.valueOf(p.getBfsnr()) + "_shp.zip");
                dataset.setNfgeometer(p.getNfgVorname() + " " + p.getNfgName() + " (" + p.getFirma() + ")");
                
                return dataset;
            })
            .collect(collectingAndThen(toList(), ArrayList<Dataset>::new));        
        log.info("End data service request");

        model.addAttribute("datasets", datasets);
        return "dataset_table";
    }
    
    private String getHost() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }
}
