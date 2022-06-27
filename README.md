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

Jedoch erzeugt auch `aotTest` keinen Eintrag für Dataset.class in _reflect-config.json_. Aus diesem Grund wird die Klasse explizit mit `@TypeHint` hinzugefügt. Es wird für ein Musl-Alpine-Image kompiliert. Falls man ein glibc-Image verwenden will (z.B. quarkus-micro auf Basis ubi-micro) muss in der Github Action die musl-Ooption entfernt werden und im build.gradle --static und --libc=musl.

## Betriebsdokumentation
Bei jedem Git-Push wird mittels Travis das Docker-Image neu gebildet und als `sogis/cadastral-data-disposal` mit den Tags "Travis-Buildnummer" und "latest" auf Docker Hub abgelegt. Auf der Testumgebung des AGI wird viertelstündlich das latest-Image neu deployed.

### Credentials / Config
Mittels Spring Boot `application.properties` resp. ENV-Variablen. Es müssen mindestens die ENV-Variablen AWS_ACCESS_KEY_ID und AWS_SECRET_ACCESS_KEY gesetzt werden. Standardmässig zeigt SPRING_PROFILES_ACTIVE auf "prod" und muss bei Bedarf auch gesetzt werden. Alle anderen Einstellungen sind in den entsprechenden Properties-Dateien (könne jedoch überschrieben werden).

### Java

### Native

### Docker
```
docker run -p 8080:8080 -e AWS_ACCESS_KEY_ID=XXXXXX -e AWS_SECRET_ACCESS_KEY=YYYYYY sogis/cadastral-data-disposal
```

## TODO
* More Tests
