package uz.napa.my_career.controller.mvc;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/")
    public String index() {
        System.out.println("Main");
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        System.out.println("Log");
        return "login";
    }

    @GetMapping("/register")
    public String reg(){
        System.out.println("reg");
        return "reg";
    }

   @GetMapping("/create-resume")
   public String createResume(){
        return "create_resume";
   }

}
