package uz.napa.my_career.controller.mvc;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String reg() {
        return "reg";
    }

    @GetMapping("/create-resume")
    public String createResume() {
        return "create_resume";
    }

}
