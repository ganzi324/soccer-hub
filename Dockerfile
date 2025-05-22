# Build stage
FROM eclipse-temurin:21-alpine AS build

ARG ENV
ENV ENV=$ENV

WORKDIR /app

COPY . .
RUN chmod +x ./gradlew

RUN if [ "$ENV" = "develop" ]; then \
      ./gradlew build --no-daemon ; \
    else \
      ./gradlew build -x test -x asciidoctor -x copyDocument --no-daemon ; \
    fi

# Runtime stage
FROM eclipse-temurin:21-alpine

WORKDIR /app

# Copy only the built JAR from the build stage
COPY --from=build /app/build/libs/*.jar ./app.jar

# Use a non-root user for security
RUN addgroup -S travelmate && adduser -S travelmateuser -G travelmate
USER travelmateuser

CMD ["java", "-jar", "app.jar"]
