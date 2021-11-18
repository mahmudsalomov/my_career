package uz.napa.my_career.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.napa.my_career.dto.UserNetworksDto;
import uz.napa.my_career.service.UserNetworksService;

@RestController
@RequestMapping("/api/user_networks")
public class UserNetworksController {
    @Autowired
    private UserNetworksService userNetworksService;

    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable("id") Integer id) {
        UserNetworksDto result = userNetworksService.get(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public HttpEntity<?> create(@RequestBody UserNetworksDto dto) {
        UserNetworksDto result = userNetworksService.create(dto);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping
    public HttpEntity<?> update(@RequestBody UserNetworksDto dto) {
        UserNetworksDto result = userNetworksService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable("id") Integer id) {
        userNetworksService.delete(id);
        return ResponseEntity.ok().build();
    }
}
