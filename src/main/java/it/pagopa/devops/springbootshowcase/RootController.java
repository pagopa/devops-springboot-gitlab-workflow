package it.pagopa.devops.springbootshowcase;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Random;
import java.util.TreeMap;

@Controller
public class RootController {

    private List<String> colors = Arrays.asList(new String[]{"red","green","blue","darkblue","pink","lightgoldenrodyellow","black"});

    @Value("${MY_APP_COLOR:}")
    private String myAppColor;

    @GetMapping("/")
    public ModelAndView passParametersWithModelAndView() throws UnknownHostException {
        ModelAndView modelAndView = new ModelAndView("indexTemplate");
        modelAndView.addObject("color", getOneColor(myAppColor));
        modelAndView.addObject("hostname", InetAddress.getLocalHost().getHostName());
        modelAndView.addObject("envs", envs());
        return modelAndView;
    }


    private String getOneColor(String choiseColor){
        Random r = new Random();

        if (choiseColor != null && choiseColor != ""){
            return colors.stream()
                .filter(color -> color.equals(choiseColor))
                .findAny()
                .orElse(null);
        } else {
            return colors.get(r.nextInt(colors.size()));
        }
    }

    private Map<String, String> envs()
    {
        Map<String, String> map = new HashMap<>();
        Map<String, String> treeMap = new TreeMap<>(map);
        System.getenv().forEach((k, v) -> {
            treeMap.put(k, v);
        });

        return treeMap;
    }
    
}
