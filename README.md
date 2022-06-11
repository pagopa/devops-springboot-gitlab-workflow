# devops-java-springboot-color

Application written in Spring boot to test pipelines using the gitlab workflow

## Thanks to

<https://github.com/mmumshad/simple-webapp-color> for the idea about colors

## How to release

```sh
helm dep update &&  sleep 1 &&  helm template -f values-dev.yaml .


 helm upgrade -i -n idpay -f values-dev.yaml devops-java-springboot-color . 
```
