package ch.so.agi.cadastraldatadisposal.models;

public class Dataset {
    private int ogc_fid;
    private int gem_bfs;
    private String gem_name;
    private String lieferdatum;
    
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
}
