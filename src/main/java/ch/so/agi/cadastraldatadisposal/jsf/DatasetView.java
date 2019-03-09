package ch.so.agi.cadastraldatadisposal.jsf;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
//import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import ch.so.agi.cadastraldatadisposal.models.Dataset;
import ch.so.agi.cadastraldatadisposal.services.DatasetService;

@Named("datasets")
@ViewScoped
public class DatasetView implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    DatasetService datasetService;
    
    private List<Dataset> datasetList;
    
    public List<Dataset> getDatasetList() {
        if (datasetList == null)
            datasetList = datasetService.getDatasets();
        return datasetList;
    }
}
