package it.pagopa.devops.springbootshowcase;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Random;

@Controller
public class RootController {

    private List<String> colors = Arrays.asList(new String[]{"red","green","blue","blue2","darkblue","pink","lightgoldenrodyellow","black"});

    @GetMapping("/")
    public ModelAndView passParametersWithModelAndView() throws UnknownHostException {
        ModelAndView modelAndView = new ModelAndView("indexTemplate");
        modelAndView.addObject("color", getOneColor(""));
        modelAndView.addObject("hostname", InetAddress.getLocalHost().getHostName());
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
    
}
