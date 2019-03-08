package ch.so.agi.cadastraldatadisposal.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.so.agi.cadastraldatadisposal.models.Dataset;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Service
public class DatasetService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public List<Dataset> getDatasets() {
        String sql = "SELECT\n" + 
                "    ogc_fid,\n" + 
                "    gem_bfs,\n" + 
                "    gem_name,\n" + 
                "    lieferdatum\n" + 
                "FROM\n" + 
                "    av_nfgeometer.datenabgabe_info_v\n" + 
                ";";
        
        RowMapper<Dataset> mapper = new BeanPropertyRowMapper<Dataset>(Dataset.class);       
        List<Dataset> resultList = jdbcTemplate.query(sql, mapper);
        
        return resultList;
    }
}
