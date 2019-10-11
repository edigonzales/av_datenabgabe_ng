package ch.so.agi.cadastraldatadisposal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import ch.so.agi.cadastraldatadisposal.models.Dataset;
import ch.so.agi.cadastraldatadisposal.models.Feature;
import ch.so.agi.cadastraldatadisposal.models.Properties;
import ch.so.agi.cadastraldatadisposal.models.Response;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.collectingAndThen;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class MainController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    DatasetService dataSetService;

    List<Dataset> lstDatasets = new ArrayList<Dataset>();
    
    
    //https://geo-i.so.ch/api/data/v1/ch.so.agi.av.nachfuehrungsgemeinden.data/
    
    //@GetMapping("/")
    public String getDataset(Model model) {
//        lstDatasets = dataSetService.getDatasets();
//        model.addAttribute("datasets", lstDatasets);
        return "dataset.table.html";
    }
    
    @GetMapping("/")     
    public String show(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        Response response = restTemplate.getForObject("https://geo-i.so.ch/api/data/v1/ch.so.agi.av.nachfuehrungsgemeinden.data/", Response.class);

        ArrayList<Dataset> datasets = response.getFeatures().stream()
            .sorted((f1, f2) -> f1.getProperties().getGemeindename().compareTo(f2.getProperties().getGemeindename()))
            .map(f -> {
                Dataset dataset = new Dataset();
                Properties p = f.getProperties();
                dataset.setGem_name(p.getGemeindename());
                dataset.setGem_bfs(p.getBfsnr());
                dataset.setNfgeometer(p.getNfgVorname() + " " + p.getNfgName() + " (" + p.getFirma() + ")");
                return dataset;
            })
            .collect(collectingAndThen(toList(), ArrayList<Dataset>::new));
        
        model.addAttribute("datasets", datasets);
        return "dataset.table.html";
    }

    
}
