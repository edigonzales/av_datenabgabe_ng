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

Umgesetzt wurde die neue Lösung mit Spring Boot. Als Datenquelle wird der Data-Service-Layer `ch.so.agi.av.nachfuehrungsgemeinden.data` und die Metainformationen aus dem `ch.so.agi.av.dm01avso24lv95`-Bucket bei AWS.

## Betriebsdokumentation
Bei jedem Git-Push wird mittels Travis das Docker-Image neu gebildet und als `sogis/cadastral-data-disposal` mit den Tags "Travis-Buildnummer" und "latest" auf Docker Hub abgelegt. Auf der Testumgebung des AGI wird viertelstündlich das latest-Image neu deployed.

### Credentials / Config
Mittels Spring Boot `application.properties` resp. ENV-Variablen (AWS-Keys: AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY).

### Docker
```
docker run -p 8080:8080 sogis/cadastral-data-disposal
```

## Entwicklerdokumentation

FIXME

## TODO
* Tests
