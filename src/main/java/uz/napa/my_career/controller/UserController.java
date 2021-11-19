package uz.napa.my_career.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.napa.my_career.dto.PasswordChangeDto;
import uz.napa.my_career.dto.UserDto;
import uz.napa.my_career.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable("id") Long id) {
        UserDto result = userService.get(id);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping()
    public HttpEntity<?> update(@RequestBody UserDto user) {
        UserDto result = userService.update(user);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping()
    public HttpEntity<?> create(@RequestBody UserDto user) {
        userService.create(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public HttpEntity<?> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/change/password/{id}")
    public HttpEntity<?> changePassword(@PathVariable("id") Long id, @RequestBody PasswordChangeDto dto) {
        userService.changePassword(id, dto);
        return ResponseEntity.ok().build();
    }
}
