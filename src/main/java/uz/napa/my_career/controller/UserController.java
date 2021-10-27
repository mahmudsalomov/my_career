package uz.napa.my_career.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.napa.my_career.config.SecurityUtil;
import uz.napa.my_career.dto.UserDetail;
import uz.napa.my_career.entity.User;
import uz.napa.my_career.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public HttpEntity<?> get() {
        UserDetail result = userService.get(SecurityUtil.getUserId());
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/update")
    public HttpEntity<?> update(@RequestBody UserDetail user) {
        UserDetail result = userService.update(SecurityUtil.getUserId(), user);
        return ResponseEntity.ok().body(result);
    }


}
