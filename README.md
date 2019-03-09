[![Build Status](https://travis-ci.org/sogis/av_datenabgabe_ng.svg?branch=master)](https://travis-ci.org/sogis/av_datenabgabe_ng)
# av_datenabgabe_ng
Ablösung https://geoweb.so.ch/av_datenabgabe/

## Local dev
Vagrant-DB hochfahren:
```
vagrant up
```

Z.B. in DBeaver die SQL-Befehle aus beiden Dateien `create_table.sql` und `datenabgabe_info_v_201903071247.sql` ausführen. 

Die INSERT-Befehle wurden aus der alten sogis-DB mit DBeaver exportiert. Ursprünglich handelt es sich um eine View. Für das Entwickeln ist das egal. View muss sowieso angepasst werden (Links zu WebGIS, ...)

## TODO
Die View ist anzupassen:
- Kartenlink (done)
- Datenlinks (-> Andi fragen)
- sogis-Prod ist noch gar nichts geänder. sogis-Test die Kartenlinks.

```
CREATE OR REPLACE VIEW av_nfgeometer.datenabgabe_info_v
AS SELECT b.ogc_fid, b.gem_bfs, btrim(c.gmde_name::text) AS gem_name, 
    to_char(d.lieferdatum::timestamp with time zone, 'DD.MM.YYYY'::text) AS lieferdatum, 
    ((((btrim(a.vorname::text) || ' '::text) || btrim(a.name::text)) || ' ('::text) || a.firma::text) || ')'::text AS nfgeometer, 
    a.vorname, a.name, 
    'https://geo.so.ch/map/?bl=hintergrundkarte_sw&st=&l=&t=default&c='::text || round(ST_X(ST_PointOnSurface(c.wkb_geometry))) || '%2C'::text || round(ST_Y(ST_PointOnSurface(c.wkb_geometry))) || '&s=20000'::text AS pdf,  
    ('geoweb.so.ch/av_datenabgabe/av_daten/itf_ch/ch_'::text || b.gem_bfs) || '00.zip'::text AS itfch, 
    ('geoweb.so.ch/av_datenabgabe/av_daten/itf_so/'::text || b.gem_bfs) || '00.zip'::text AS itfso, 
    ('geoweb.so.ch/av_datenabgabe/av_daten/dxf_geobau/'::text || b.gem_bfs) || '.zip'::text AS dxf, 
    ('geoweb.so.ch/av_datenabgabe/av_daten/mopublic/shp/lv95/d/'::text || b.gem_bfs) || '.zip'::text AS shp, 
    btrim(a.firma::text) AS firma, btrim(a.firma_zusatz::text) AS firma_zusatz, 
    btrim(a.strasse::text) AS strasse, btrim(a.hausnummer::text) AS hausnr, 
    a.plz, btrim(a.ortschaft::text) AS ortschaft, 
    btrim(a.telefon::text) AS telefon, btrim(a.fax::text) AS fax, 
    btrim(a.email::text) AS email, btrim(a.web::text) AS web
   FROM av_nfgeometer.nfgeometer a, av_nfgeometer.geometer_gemeinde b, 
    geo_gemeinden_v c, 
    ( SELECT DISTINCT ON (gemeindegrenzen_gemeinde.gem_bfs) gemeindegrenzen_gemeinde.gem_bfs, 
            gemeindegrenzen_gemeinde.lieferdatum
           FROM av_avdpool_ng.gemeindegrenzen_gemeinde
          ORDER BY gemeindegrenzen_gemeinde.gem_bfs) d
  WHERE a.ogc_fid = b.nfgeometer_id AND b.gem_bfs = c.gem_bfs AND d.gem_bfs = b.gem_bfs;

COMMENT ON VIEW av_nfgeometer.datenabgabe_info_v IS 'View für Liste der AV-Operate.';

-- Permissions

ALTER TABLE av_nfgeometer.datenabgabe_info_v OWNER TO sogis_admin;
GRANT ALL ON TABLE av_nfgeometer.datenabgabe_info_v TO sogis_admin;
GRANT SELECT ON TABLE av_nfgeometer.datenabgabe_info_v TO av_verifikation;
GRANT SELECT ON TABLE av_nfgeometer.datenabgabe_info_v TO mspublic;
GRANT SELECT ON TABLE av_nfgeometer.datenabgabe_info_v TO av_import;
GRANT SELECT ON TABLE av_nfgeometer.datenabgabe_info_v TO public;
```


## Hints
- Env-Variablen auf macOS, die auch Eclipse resp. die Java-Anwendungen kennen: `launchctl setenv AV_DATENABGABE_ENV test`
- Falls Fehlermeldungen im Browser erscheinen, dass Bootsfaces-Komponenten nicht gefunden werden können, hat ein `./gradlew assemble` in der Konsole geholfen. 






