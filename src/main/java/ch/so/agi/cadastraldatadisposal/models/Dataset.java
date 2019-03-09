package ch.so.agi.cadastraldatadisposal.models;

public class Dataset {
    private int ogc_fid;
    private int gem_bfs;
    private String gem_name;
    private String lieferdatum;
    private String nfgeometer;
    private String pdf;
    private String itfch;
    private String itfso;
    private String dxf;
    private String shp;
    
    public int getOgc_fid() {
        return ogc_fid;
    }
    public void setOgc_fid(int ogc_fid) {
        this.ogc_fid = ogc_fid;
    }
    public int getGem_bfs() {
        return gem_bfs;
    }
    public void setGem_bfs(int gem_bfs) {
        this.gem_bfs = gem_bfs;
    }
    public String getGem_name() {
        return gem_name;
    }
    public void setGem_name(String gem_name) {
        this.gem_name = gem_name;
    }
    public String getLieferdatum() {
        return lieferdatum;
    }
    public void setLieferdatum(String lieferdatum) {
        this.lieferdatum = lieferdatum;
    }
    public String getNfgeometer() {
        return nfgeometer;
    }
    public void setNfgeometer(String nfgeometer) {
        this.nfgeometer = nfgeometer;
    }
    public String getPdf() {
        return pdf;
    }
    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
    public String getItfch() {
        return itfch;
    }
    public void setItfch(String itfch) {
        this.itfch = itfch;
    }
    public String getItfso() {
        return itfso;
    }
    public void setItfso(String itfso) {
        this.itfso = itfso;
    }
    public String getDxf() {
        return dxf;
    }
    public void setDxf(String dxf) {
        this.dxf = dxf;
    }
    public String getShp() {
        return shp;
    }
    public void setShp(String shp) {
        this.shp = shp;
    }
}
