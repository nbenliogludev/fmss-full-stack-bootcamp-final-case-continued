# Temel imaj olarak OpenJDK 21 kullan
FROM openjdk:21-jdk-slim

# Çalışma dizinini ayarla
WORKDIR /app

# Maven'i yükle ve bağımlılıkları indir
RUN apt-get update && \
    apt-get install -y maven

# Maven kullanarak projeyi build etmek için gerekli dosyaları kopyala
COPY pom.xml ./
COPY src ./src

# Projeyi build et
RUN mvn clean package -DskipTests

# Uygulama jar dosyasını çalışma dizinine kopyala
RUN cp ./target/user-service-0.0.1-SNAPSHOT.jar /app.jar

RUN rm -rf /app

# Container içinde çalışacak komutu belirle
CMD ["java", "-jar", "/app.jar"]

# Container'ın çalışacağı portu belirt
EXPOSE 8080
