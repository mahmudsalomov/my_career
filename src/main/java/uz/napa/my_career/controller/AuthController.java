package uz.napa.my_career.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.napa.my_career.dto.RegistrationDto;
import uz.napa.my_career.entity.User;
import uz.napa.my_career.payload.ResToken;
import uz.napa.my_career.payload.SignIn;
import uz.napa.my_career.secret.CurrentUser;
import uz.napa.my_career.service.AuthService;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody SignIn signIn) {
        ResToken resToken = authService.signIn(signIn);
        return ResponseEntity.status(resToken != null ? 200 : 401).body(resToken);
    }

    @PostMapping("/registration")
    public HttpEntity<?> register(@RequestBody RegistrationDto dto) {
        authService.registration(dto);
        return ResponseEntity.ok    ().build();
    }

    @GetMapping("/validation/{jwt}")
    public HttpEntity<?> validate(@PathVariable("jwt") String jwt) {
        authService.validation(jwt);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public HttpEntity<?> me(@CurrentUser User user) {
        if (user != null) {
            return ResponseEntity.ok(user);
        } else return null;
    }


}
