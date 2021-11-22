package uz.napa.my_career.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.napa.my_career.dto.ResumeDto;
import uz.napa.my_career.entity.Resume;
import uz.napa.my_career.service.ResumeService;

@RestController
@RequestMapping("/api/resume")

public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    //create resume
    @PostMapping()
    public HttpEntity<?> create(@RequestBody ResumeDto resume) {
        ResumeDto dto = resumeService.create(resume);
        return ResponseEntity.ok().body(dto);
    }

    //get resume
    @GetMapping("/{id}")
    public HttpEntity<?> getResume(@PathVariable("id") Long id) {
        ResumeDto result = resumeService.getDetail(id);
        return ResponseEntity.ok().body(result);
    }

    //update resume
    @PutMapping()
    public ResponseEntity<?> update(@RequestBody ResumeDto dto) {
        Resume result = resumeService.update(dto);
        return ResponseEntity.ok().body("");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        resumeService.delete(id);
        return ResponseEntity.ok().build();
    }

}