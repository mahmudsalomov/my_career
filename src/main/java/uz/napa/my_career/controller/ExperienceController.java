package uz.napa.my_career.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.napa.my_career.dto.ExperienceDto;
import uz.napa.my_career.service.ExperienceService;

@RestController
@RequestMapping("/api/experience")
public class ExperienceController {
    @Autowired
    private ExperienceService experienceService;

    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        ExperienceDto result = experienceService.get(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping()
    public HttpEntity<?> create(@RequestBody ExperienceDto dto) {
        ExperienceDto result = experienceService.create(dto);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping
    public HttpEntity<?> update(@RequestBody ExperienceDto dto) {
        ExperienceDto result = experienceService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable("id") Integer id) {
        experienceService.delete(id);
        return ResponseEntity.ok().build();
    }

}
