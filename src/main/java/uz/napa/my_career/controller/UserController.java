package uz.napa.my_career.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.napa.my_career.config.SecurityUtil;
import uz.napa.my_career.dto.UserDetail;
 import uz.napa.my_career.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get/{id}")
    public HttpEntity<?> get(@PathVariable("id") Long id) {
        UserDetail result = userService.get(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/update")
    public HttpEntity<?> update(@RequestBody UserDetail user) {
        UserDetail result = userService.update(user.getId(), user);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/create")
    public HttpEntity<?> create(@RequestBody UserDetail user) {
        userService.create(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/log-info")
    public HttpEntity<?> setNameSurnamePhoneAddress(@RequestBody UserDetail user) {
        userService.setInfo(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public HttpEntity<?> getUserInfo(){
        Long id = SecurityUtil.getUserId();
        return ResponseEntity.ok().body(id);
    }
}
