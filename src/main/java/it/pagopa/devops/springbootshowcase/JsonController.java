package it.pagopa.devops.springbootshowcase;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/json")
public class JsonController {

    @Value("${MY_ENV_1:}")
    private String myEnv1;

    // @GetMapping(path = "/", produces=MediaType.APPLICATION_JSON_VALUE)
    // public Map<String, String> root()
    // {
    //     HashMap<String, String> map = new HashMap<>();
    //     map.put("root", "ok");
    //     return map;
    // }

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
