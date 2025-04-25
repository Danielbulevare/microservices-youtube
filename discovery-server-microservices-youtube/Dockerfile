FROM eclipse-temurin:17

#Directorio donde se colocará la aplicación en el contenedor
WORKDIR /app

#Copia el archivo .jar del proyecto al directorio /app en el contenedor
COPY target/discovery-server-microservices-youtube-0.0.1-SNAPSHOT.jar /app/discovery-server-microservices-youtube.jar

#Exponer el puerto que usa la aplicación
EXPOSE 8761

#Comando para ejecutar la aplicación
CMD ["java", "-jar", "/app/discovery-server-microservices-youtube.jar"]