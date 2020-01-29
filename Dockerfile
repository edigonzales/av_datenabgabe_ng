FROM adoptopenjdk/openjdk11:latest

EXPOSE 8080

WORKDIR /home/av_datenabgabe

ARG DEPENDENCY=build/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /home/av_datenabgabe/app/lib
COPY ${DEPENDENCY}/META-INF /home/av_datenabgabe/app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /home/av_datenabgabe/app
RUN chown -R 1001:0 /home/av_datenabgabe && \
    chmod -R g=u /home/av_datenabgabe

USER 1001

ENTRYPOINT ["java","-XX:MaxRAMPercentage=80.0", "-cp","/home/av_datenabgabe/app:/home/av_datenabgabe/app/lib/*","ch.so.agi.cadastraldatadisposal.CadastralDataDisposalApplication"]