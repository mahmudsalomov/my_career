package uz.napa.my_career.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.napa.my_career.dto.ExperienceDto;
import uz.napa.my_career.entity.SkillCategory;
import uz.napa.my_career.service.SkillCategoryService;

@RestController
@RequestMapping("/api/skill_category")
public class SkillCategoryController {
    @Autowired
    private SkillCategoryService skillCategoryService;

    @GetMapping("/{id}")
    public HttpEntity<?> get(@PathVariable Short id) {
        SkillCategory result = skillCategoryService.get(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping()
    public HttpEntity<?> create(@RequestBody SkillCategory dto) {
        SkillCategory result = skillCategoryService.create(dto);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping
    public HttpEntity<?> update(@RequestBody SkillCategory dto) {
        SkillCategory result = skillCategoryService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable("id") Short id) {
        skillCategoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
