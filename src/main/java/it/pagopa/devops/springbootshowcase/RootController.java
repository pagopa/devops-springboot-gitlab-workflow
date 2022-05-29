package it.pagopa.devops.springbootshowcase;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootController {

    @Value("${MY_ENV_1:}")
    private String myEnv1;

    @GetMapping(path = "/", produces=MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> root()
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("root", "ok");
        return map;
    }

    @GetMapping(path = "/status", produces=MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> status()
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "ok");
        return map;
    }

    @GetMapping(path = "/envs", produces=MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> envs()
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("MY_ENV_1", myEnv1);
        return map;
    }
}
