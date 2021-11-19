package uz.napa.my_career.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.napa.my_career.dto.OrganizationDto;
import uz.napa.my_career.service.OrganizationService;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable("id") Integer id) {
        OrganizationDto result = organizationService.get(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public HttpEntity<?> create(@RequestBody OrganizationDto dto) {
        OrganizationDto result = organizationService.create(dto);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping
    public HttpEntity<?> update(@RequestBody OrganizationDto dto) {
        OrganizationDto result = organizationService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable("id") Integer id) {
        organizationService.delete(id);
        return ResponseEntity.ok().build();
    }
}
