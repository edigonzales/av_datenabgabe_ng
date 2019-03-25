[![Build Status](https://travis-ci.org/sogis/av_datenabgabe_ng.svg?branch=master)](https://travis-ci.org/sogis/av_datenabgabe_ng)
# av_datenabgabe_ng
Ablösung https://geoweb.so.ch/av_datenabgabe/.

Inhalt der Dokumentation:

1. Beschreibung
2. Betriebsdokumention
3. Entwicklerdokumentation
4. TODO
5. Hints

## Beschreibung
In Absprache mit den Nachführungsgeometern bietet das AGI eine einfache Möglichkeit die Daten der amtlichen Vermessung in verschiedenen Formaten herunterzuladen. Umgesetzt wurde dies mit einer Liste sämtlicher Gemeinden und Links zum Web GIS Client (zwecks PDF-Erstellung) und den Zipfiles mit den verschiedenen Datenformaten (ITF, SHP, DXF). 

Die bestehende Lösung muss abgelöst werden. Dabei handelt es sich wahrscheinlich um eine Provisorium/Wegwerfprodukt, da die Datenabgabe des AGI komplett neu konzipiert wird.

Umgesetzt wurde die neue Lösung mit Spring Boot und JSF.

## Betriebsdokumentation
Bei jedem Git-Push wird mittels Travis das Docker-Image neu gebildet und als `sogis/cadastral-data-disposal` mit den Tags "Travis-Buildnummer" und "latest" auf Docker Hub abgelegt. Auf der Testumgebung des AGI wird viertelstündlich das latest-Image neu deployed.

### Datenbankverbindungsparameter
Die Verbindungsparameter werden über Spring Boot Profile gesteuert. Für jede Umgebung gibt es ein `application-[dev|test|prod]properties`. Diese spezielle, zusätzliche Propertiesfile kann mit der speziellen Spring-Boot-Umgebungsvariable `SPRING_PROFILES_ACTIVE` gesteuert werden: `SPRING_PROFILES_ACTIVE=[dev|test|prod]` vorhanden sein. 

### Docker
```
docker run -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=dev" sogis/cadastral-data-disposal
```

## Entwicklerdokumentation

Die Spring-Boot-Applikation:

1. Wandelt den HTTP-Get-Request um in eine Datenbankabfrage.
2. Das Resultat der Datenbankabfrage wird mit mittels JSF in einer Tabelle gerendert.

### Ausführen des Programmes 

DB lokal:
- Vagrant-DB hochfahren. Erstmalig ie SQL-Befehle aus beiden Dateien `dev_setup/create_table.sql` und `dev_setup/datenabgabe_info_v_201903071247.sql` ausführen.
- Umgebungsvariable setzen für das Spring Boot Profil mit den passenden Datenbankverbindungsparametern.
- Spring Boot Applikation starten


## TODO
### Tests
- version.txt
- index.xhtml (braucht Datenbank -> Testcontainers)

### View (Intranet-sogis-DB):
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






