package github.gtopinio.STOMPaaS.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebRouteController {

    @GetMapping("/")
    String homeRoute() {
        return "main/index";
    }
}
