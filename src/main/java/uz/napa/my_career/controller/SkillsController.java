package uz.napa.my_career.controller;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.napa.my_career.dto.SkillsDto;
import uz.napa.my_career.entity.SkillCategory;
import uz.napa.my_career.entity.Skills;
import uz.napa.my_career.service.SkillCategoryService;
import uz.napa.my_career.service.SkillsService;

@RestController
@RequestMapping("/api/skills")
public class SkillsController {
    @Autowired
    private SkillsService skillsService;

    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Integer id) {
        SkillsDto result = skillsService.get(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping()
    public HttpEntity<?> create(@RequestBody SkillsDto dto) {
        SkillsDto result = skillsService.create(dto);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping
    public HttpEntity<?> update(@RequestBody SkillsDto dto) {
        SkillsDto result = skillsService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable("id") Integer id) {
        skillsService.delete(id);
        return ResponseEntity.ok().build();
    }
}
