package uz.napa.my_career.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.napa.my_career.dto.EducationDto;
import uz.napa.my_career.service.EducationService;

@RestController
@RequestMapping("/api/education")
public class EducationController {
    @Autowired
    private EducationService educationService;

    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable("id") Integer id) {
        EducationDto result = educationService.get(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping()
    public HttpEntity<?> create(@RequestBody EducationDto dto) {
        EducationDto result = educationService.create(dto);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping()
    public HttpEntity<?> update(@RequestBody EducationDto dto) {
        EducationDto result = educationService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        educationService.delete(id);
        return ResponseEntity.ok().build();
    }
}
