package it.pagopa.devops.springbootshowcase;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/app")
public class AppController {

    @Value("${MY_ENV_1:}")
    private String myEnv1;

    @GetMapping(path = "", produces=MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> mainApp()
    {
        return root();
    }

    @GetMapping(path = "/", produces=MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> root()
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("root", "ok");
        return map;
    }

    @GetMapping(path = "/envs", produces=MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> envs()
    {
        HashMap<String, String> map = new HashMap<>();
        System.getenv().forEach((k, v) -> {
            map.put(k, v);
        });
        return map;
    }

    /*
     * Liveness & Readiness
     */
    @GetMapping(path = "/live", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Boolean> live()
    {
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("live", true);
        return map;
    }

    @GetMapping(path = "/ready", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Boolean> ready()
    {
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("ready", true);
        return map;
    }
}
