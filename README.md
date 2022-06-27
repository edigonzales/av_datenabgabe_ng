[![CI/CD](https://github.com/sogis/av_datenabgabe_ng/actions/workflows/main.yml/badge.svg)](https://github.com/sogis/av_datenabgabe_ng/actions/workflows/main.yml)
# av_datenabgabe_ng
Ablösung https://geoweb.so.ch/av_datenabgabe/.

## Beschreibung
In Absprache mit den Nachführungsgeometern bietet das AGI eine einfache Möglichkeit die Daten der amtlichen Vermessung in verschiedenen Formaten herunterzuladen. Umgesetzt wurde dies mit einer Liste sämtlicher Gemeinden und Links zum Web GIS Client (zwecks PDF-Erstellung) und den Zipfiles mit den verschiedenen Datenformaten (ITF, SHP, DXF). 

Die bestehende Lösung muss abgelöst werden. Dabei handelt es sich wahrscheinlich um eine Provisorium/Wegwerfprodukt, da die Datenabgabe des AGI komplett neu konzipiert wird.

Umgesetzt wurde die neue Lösung mit Spring Boot. Als Datenquelle wird der Data-Service-Layer `ch.so.agi.av.nachfuehrungsgemeinden.data` und die Metainformationen aus dem `ch.so.agi.av.dm01avso24lv95`-Bucket bei AWS.

## Build

### Native

```
./gradlew clean aotTest nativeCompile -i
```

Jedoch erzeugt auch `aotTest` keinen Eintrag für Dataset.class in _reflect-config.json_. Aus diesem Grund wird die Klasse explizit mit `@TypeHint` hinzugefügt. Man kann nicht für ein Musl-Image kompilieren. Fehlermeldungen sagen mir nichts. Aus diesem Grund wir ein glibc-Image auf Basis ubi-minimal (Red Hat) verwendet. Es wird kein User hinzugefügt, auch wenn ich das tendenziell wieder eher mache, da gewisse Container-Orchestrierer einen wollen. Openshift jedoch nicht.


## Betriebsdokumentation
Bei jedem Git-Push wird mittels Travis das Docker-Image neu gebildet und als `sogis/cadastral-data-disposal` mit den Tags "Travis-Buildnummer" und "latest" auf Docker Hub abgelegt. Auf der Testumgebung des AGI wird viertelstündlich das latest-Image neu deployed.

### Credentials / Config
Mittels Spring Boot `application.properties` resp. ENV-Variablen (AWS-Keys: AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY).

### Java

### Native

### Docker
```
docker run -p 8080:8080 sogis/cadastral-data-disposal
```

## TODO
* More Tests
