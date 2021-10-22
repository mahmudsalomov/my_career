package uz.napa.my_career.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.napa.my_career.dto.resume.ResumeCreate;
import uz.napa.my_career.entity.Resume;
import uz.napa.my_career.service.ResumeService;

@RestController
@RequestMapping("/resume")

public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @PostMapping("/create")
    public HttpEntity<?> create(@RequestBody ResumeCreate resume){

        resumeService.create(resume);

        return ResponseEntity.ok().build();
    }

}
