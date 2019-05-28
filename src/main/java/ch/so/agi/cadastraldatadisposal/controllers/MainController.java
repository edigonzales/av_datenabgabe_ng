package ch.so.agi.cadastraldatadisposal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ch.so.agi.cadastraldatadisposal.models.Dataset;
import ch.so.agi.cadastraldatadisposal.services.DatasetService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class MainController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DatasetService dataSetService;

    List<Dataset> lstDatasets = new ArrayList<Dataset>();
    
    @GetMapping("/")
    public String getDataset(Model model) {
        lstDatasets = dataSetService.getDatasets();
        model.addAttribute("datasets", lstDatasets);
        return "dataset.table.html";
    }
}
