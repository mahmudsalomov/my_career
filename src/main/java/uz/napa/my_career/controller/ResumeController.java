package uz.napa.my_career.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.napa.my_career.dto.resume.ResumeCreateDto;
import uz.napa.my_career.dto.resume.ResumeDetailDto;
import uz.napa.my_career.entity.Resume;
import uz.napa.my_career.service.ResumeService;

@RestController
@RequestMapping("/resume")

public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    //create resume
    @PostMapping("/create")
    public HttpEntity<?> create(@RequestBody ResumeCreateDto resume){
        Resume dto = resumeService.create(resume);
        return ResponseEntity.ok().body(dto);
    }

    //get resume
    @GetMapping("/detail/{id}")
    public HttpEntity<?> getResume(@PathVariable("id") Long id){
        Resume resume = resumeService.getDetail(id);
        return ResponseEntity.ok().body(resume);
    }

    //update resume
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ResumeCreateDto dto){
       resumeService.update(id,dto);
       return ResponseEntity.ok().build();
    }



}