package it.pagopa.devops.springbootshowcase;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Random;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    private static final List<String> COLORS = List.of("red", "green", "blue",
        "darkblue", "pink", "lightgoldenrodyellow", "black");

    @Value("${MY_APP_COLOR:}")
    private String myAppColor;

    record PageData(String color, String hostname, Map<String, String> envs) {}

    @GetMapping("/")
    public ModelAndView passParametersWithModelAndView() throws UnknownHostException {
        log.info("🚀 Starting to process request");

        String selectedColor = getOneColor(myAppColor);
        log.debug("🎨 Selected color: {}", selectedColor);

        String hostname = InetAddress.getLocalHost().getHostName();
        log.debug("🏠 Hostname: {}", hostname);

        Map<String, String> environments = envs();
        log.debug("⚙️ Loaded {} environment variables", environments.size());

        PageData pageData = new PageData(selectedColor, hostname, environments);

        ModelAndView modelAndView = new ModelAndView("indexTemplate");
        modelAndView.addObject("color", pageData.color());
        modelAndView.addObject("hostname", pageData.hostname());
        modelAndView.addObject("envs", pageData.envs());

        log.info("✅ Request processed successfully");
        return modelAndView;
    }

    private String getOneColor(String choiceColor) {
        log.debug("🎯 Selecting color. User choice: {}", choiceColor);

        if (choiceColor == null || choiceColor.isEmpty()) {
            log.debug("🎯 User choice is empty, selecting random");
            return getRandomColor();
        } else {
            log.debug("🎯 User choice is {}", choiceColor);
            return COLORS.stream()
                .filter(color -> color.equals(choiceColor))
                .findAny()
                .orElseGet(() -> {
                    log.warn("⚠️ Requested color {} not found, selecting random", choiceColor);
                    return getRandomColor();
                });
        }
    }

    private String getRandomColor() {
        var random = new Random();
        String color = COLORS.get(random.nextInt(COLORS.size()));
        log.debug("🎲 Randomly selected color: {}", color);
        return color;
    }

    private Map<String, String> envs() {
        log.debug("📝 Loading environment variables");
        return new TreeMap<>(System.getenv());
    }
}
