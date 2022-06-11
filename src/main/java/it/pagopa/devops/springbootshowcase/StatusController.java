package it.pagopa.devops.springbootshowcase;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusController {

    @GetMapping(path = "", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> mainStatus()
    {
        return root();
    }

    @GetMapping(path = "/", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> root()
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "ok");
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
