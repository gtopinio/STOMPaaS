package github.gtopinio.STOMPaaS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController {

    @GetMapping("/")
    String homeRoute() {
        return "main/index";
    }

    @RequestMapping("/**")
    public String handleUndefinedRoutes() {
        return "redirect:/";
    }
}
