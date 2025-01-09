# Build stage
FROM maven:3.9-amazoncorretto-21@sha256:17ae7b5533254592b8ab1a159cdb63777a692eab49754b708711854c0a68d6a4 AS builder

WORKDIR /build
COPY . .
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests

# Runtime stage
FROM amazoncorretto:21-alpine3.20@sha256:b66d1d797ca711a537667d22aea4713577338bd161447c91efaa61a8e6855981

WORKDIR /app

# Add dependencies for security and monitoring
RUN apk add --no-cache \
    curl \
    tzdata \
    && addgroup -S javauser \
    && adduser -S javauser -G javauser \
    # Set system timezone
    && cp /usr/share/zoneinfo/Europe/Rome /etc/localtime \
    && echo "Europe/Rome" > /etc/timezone

# Create log directory and set permissions
RUN mkdir -p /app/logs && \
    chown -R javauser:javauser /app/logs

# Copy application bundle
COPY --from=builder /build/target/*.jar app.jar

# Add Application Insights agent
ADD --chmod=644 https://github.com/microsoft/ApplicationInsights-Java/releases/download/3.4.17/applicationinsights-agent-3.4.17.jar applicationinsights-agent.jar

# Configure permissions
RUN chown -R javauser:javauser /app
USER javauser

# Configure JVM options for containerized environment
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=75.0 \
    -XX:InitialRAMPercentage=50.0 \
    -XX:+UseG1GC \
    -Djava.security.egd=file:/dev/./urandom \
    -Duser.timezone=Europe/Rome"

# Application Insights configuration
ENV APPLICATIONINSIGHTS_CONNECTION_STRING=""
ENV APPLICATIONINSIGHTS_ROLE_NAME="devops-java-springboot-color"

# Add volume for logs
VOLUME /app/logs

# Configure container
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s --start-period=30s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-javaagent:/app/applicationinsights-agent.jar", "-jar", "/app/app.jar"]