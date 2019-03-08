# av_datenabgabe_ng
Ablösung https://geoweb.so.ch/av_datenabgabe/

## Local dev
Vagrant-DB hochfahren:
```
vagrant up
```

Z.B. in DBeaver die SQL-Befehle aus beiden Dateien `create_table.sql` und `datenabgabe_info_v_201903071247.sql` ausführen. 

Die INSERT-Befehle wurden aus der alten sogis-DB mit DBeaver exportiert. Ursprünglich handelt es sich um eine View. Für das Entwickeln ist das egal. View muss sowieso angepasst werden (Links zu WebGIS, ...)

## Hints
- Falls Fehlermeldungen im Browser erscheinen, dass Bootsfaces-Komponenten nicht gefunden werden können, hat ein `./gradlew assemble` in der Konsole geholfen. 






