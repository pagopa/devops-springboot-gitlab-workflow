# Build stage
FROM maven:3.9-amazoncorretto-21 AS builder

WORKDIR /build
COPY . .
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests

# Runtime stage
FROM amazoncorretto:21-alpine3.20

WORKDIR /app

# Add dependencies for security and monitoring
RUN apk add --no-cache \
    curl \
    tzdata \
    && addgroup -S javauser \
    && adduser -S javauser -G javauser

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
    -Xlog:gc*:/app/gc.log \
    -Djava.security.egd=file:/dev/./urandom \
    -Duser.timezone=UTC"

# Application Insights configuration
ENV APPLICATIONINSIGHTS_CONNECTION_STRING=""
ENV APPLICATIONINSIGHTS_ROLE_NAME="devops-java-springboot-color"

# Configure container
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s --start-period=30s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-javaagent:/app/applicationinsights-agent.jar", "-jar", "/app/app.jar"]