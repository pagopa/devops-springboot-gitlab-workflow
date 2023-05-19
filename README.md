# devops-java-springboot-color

Application written in Spring boot to test pipelines using the gitlab workflow

## Thanks to

<https://github.com/mmumshad/simple-webapp-color> for the idea about colors

## Structure

### Endpoints

#### Root Page `/` (RootController)

Show a simple page with a color, the color is getted by an env var called `MY_APP_COLOR`.
If this var is null or empty a random color is choosed from a list.

#### App `/app` (AppController)

##### `/app/envs`

This endpoint contains a subendpoint called  that show all the env variables

#### Status `/status` (StatusController)

Has liveness and readiness endpoints for k8s

## How to use

You can use this simple application or locally or with docker compose, or with k8s usign the helm chart.

> Is mandatory to setup this two env variables:
> * MY_MANDATORY_VALUE
> * MY_MANDATORY_SECRET

This variables are for asserts of helm template

### Locally

Run the application usign this command

```sh
export MY_APP_COLOR=green MY_MANDATORY_VALUE=dummy MY_MANDATORY_SECRET=dummy && mvn spring-boot:run
```

In this way you are able to set a color as env variable and run a spring boot server (with tomcat)

### Docker compose

Use the docker compose to run the application, be ware to set the env variables used by the application

## How to release (into domain folder)

```sh
helm dep update &&  sleep 1 &&  helm template -f values-dev.yaml \.


helm upgrade -i -n idpay -f values-dev.yaml devops-java-springboot-color \.
```
