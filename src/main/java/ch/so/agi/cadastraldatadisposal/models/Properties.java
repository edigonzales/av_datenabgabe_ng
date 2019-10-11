package ch.so.agi.cadastraldatadisposal.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({ "t_ili_tid", "gemeindename", "bfsnr", "nfg_name", "nfg_vorname", "nfg_titel", "firma",
//        "firma_zusatz", "strasse", "hausnummer", "plz", "ortschaft", "telefon", "web", "email", "uid" })
public class Properties {
    @JsonProperty("t_ili_tid")
    private String tIliTid;
    @JsonProperty("gemeindename")
    private String gemeindename;
    @JsonProperty("bfsnr")
    private Integer bfsnr;
    @JsonProperty("nfg_name")
    private String nfgName;
    @JsonProperty("nfg_vorname")
    private String nfgVorname;
    @JsonProperty("nfg_titel")
    private Object nfgTitel;
    @JsonProperty("firma")
    private String firma;
    @JsonProperty("firma_zusatz")
    private Object firmaZusatz;
    @JsonProperty("strasse")
    private String strasse;
    @JsonProperty("hausnummer")
    private String hausnummer;
    @JsonProperty("plz")
    private Integer plz;
    @JsonProperty("ortschaft")
    private String ortschaft;
    @JsonProperty("telefon")
    private String telefon;
    @JsonProperty("web")
    private String web;
    @JsonProperty("email")
    private String email;
    @JsonProperty("uid")
    private Object uid;

    public String getTIliTid() {
        return tIliTid;
    }

    public void setTIliTid(String tIliTid) {
        this.tIliTid = tIliTid;
    }

    public String getGemeindename() {
        return gemeindename;
    }

    public void setGemeindename(String gemeindename) {
        this.gemeindename = gemeindename;
    }

    public Integer getBfsnr() {
        return bfsnr;
    }

    public void setBfsnr(Integer bfsnr) {
        this.bfsnr = bfsnr;
    }

    public String getNfgName() {
        return nfgName;
    }

    public void setNfgName(String nfgName) {
        this.nfgName = nfgName;
    }

    public String getNfgVorname() {
        return nfgVorname;
    }

    public void setNfgVorname(String nfgVorname) {
        this.nfgVorname = nfgVorname;
    }

    public Object getNfgTitel() {
        return nfgTitel;
    }

    public void setNfgTitel(Object nfgTitel) {
        this.nfgTitel = nfgTitel;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public Object getFirmaZusatz() {
        return firmaZusatz;
    }

    public void setFirmaZusatz(Object firmaZusatz) {
        this.firmaZusatz = firmaZusatz;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public Integer getPlz() {
        return plz;
    }

    public void setPlz(Integer plz) {
        this.plz = plz;
    }

    public String getOrtschaft() {
        return ortschaft;
    }

    public void setOrtschaft(String ortschaft) {
        this.ortschaft = ortschaft;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getUid() {
        return uid;
    }

    public void setUid(Object uid) {
        this.uid = uid;
    }
}