#---------------------------------------------------------------
# Payment Service Template
# (c) Copyright 2025 - Araf Karsh Hamid
# Container Name: mock-payment
# Microservices Containers - SpringBoot 2.5.3
#
# Version 0.1
#---------------------------------------------------------------
# Base Version
FROM arafkarsh/java:17-alpine

LABEL author="Araf Karsh Hamid <araf.karsh@gmail.com>"

RUN mkdir /Softwares/service

ARG SPRINGBOOT_JAR=mock-payment-spring-boot.jar

# Copy Service (Install)
COPY --chown=ozazo:dev application.properties /home/ozazo/

# SpringBoot Fat Jar
COPY --chown=ozazo:dev ${SPRINGBOOT_JAR} /Softwares/service/

# Change the Mode
RUN chmod -R 750 /Softwares/service/*

# Expose Microservice Port
EXPOSE 9090

# Start Microservice
ENTRYPOINT ["java","-jar","/Softwares/service/mock-payment-spring-boot.jar"]
