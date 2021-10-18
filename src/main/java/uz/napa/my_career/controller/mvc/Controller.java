package uz.napa.my_career.controller.mvc;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {


    @GetMapping("/")
    public String index(){
        return "index";
    }

}
