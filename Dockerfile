FROM adoptopenjdk/openjdk11:jdk-11.0.2.9
VOLUME /tmp

ENV USER_NAME av_datenabgabe
ENV APP_HOME /home/$USER_NAME/app

RUN useradd -ms /bin/bash $USER_NAME
RUN mkdir $APP_HOME

ADD build/libs/cadastral-data-disposal*.jar $APP_HOME/app.jar
RUN chown $USER_NAME $APP_HOME/app.jar

USER $USER_NAME
WORKDIR $APP_HOME
RUN bash -c 'touch app.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
