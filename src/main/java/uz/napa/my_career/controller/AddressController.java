package uz.napa.my_career.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.napa.my_career.dto.AddressDetail;
import uz.napa.my_career.service.AddressService;

import java.net.http.HttpRequest;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable("id") Integer id) {
        AddressDetail result = addressService.get(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public HttpEntity<?> create(@RequestBody AddressDetail address) {
        AddressDetail result = addressService.create(address);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping
    public HttpEntity<?> update(@RequestBody AddressDetail address) {
        AddressDetail result = addressService.update(address);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        addressService.delete(id);
        return ResponseEntity.ok().build();
    }

}
