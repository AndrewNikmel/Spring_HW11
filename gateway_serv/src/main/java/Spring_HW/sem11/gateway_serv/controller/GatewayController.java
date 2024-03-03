package Spring_HW.sem11.gateway_serv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GatewayController {

    @RequestMapping("/")
    public String swagger () {
        return "redirect:/swagger-ui.html";
    }
}
