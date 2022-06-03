package it.pagopa.devops.springbootshowcase;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RootController {

    @GetMapping("/")
    public ModelAndView passParametersWithModelAndView() {
        ModelAndView modelAndView = new ModelAndView("indexTemplate");
        modelAndView.addObject("message", "ciao");
        return modelAndView;
    }
    
}
