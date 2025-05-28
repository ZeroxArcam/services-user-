# Etapa 1: Construcción con Gradle
FROM gradle:8.5-jdk17-jammy AS builder

WORKDIR /app

COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle/

# Añade esta línea para dar permisos de ejecución
RUN chmod +x ./gradlew

# Descargamos las dependencias
RUN ./gradlew dependencies --no-daemon

COPY src ./src/

# Construimos la aplicación
RUN ./gradlew build -x test --no-daemon

# ... (resto del Dockerfile) ...