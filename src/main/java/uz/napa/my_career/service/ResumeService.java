package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.resume.ResumeCreate;
import uz.napa.my_career.dto.resume.ResumeDetail;
import uz.napa.my_career.entity.Resume;
import uz.napa.my_career.exception.ItemNotFoundException;
import uz.napa.my_career.repository.ResumeRepository;
import uz.napa.my_career.repository.SkillRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private SkillRepository skillRepository;

public ResumeDetail resumeDetail(Long resumeId){

    Resume resumeEntity = get(resumeId);

    ResumeDetail resumeDetail = new ResumeDetail();
//    resumeDetail.setFirstName(resumeEntity.getFirstName());
//    resumeDetail.setLastName(resumeEntity.getLastName());
//    resumeDetail.setAddress(resumeEntity.getAddress());
//    resumeDetail.setPhone(resumeEntity.getUser().getPhone());
//    resumeDetail.setEmail(resumeEntity.getUser().getEmail());
//   resumeDetail.setSkillsList(skillRepository.getList);

    return resumeDetail;
}
    private Resume get(Long resumeId) {

    Optional<Resume> optional =this.resumeRepository.findById(resumeId);
    if(!optional.isPresent()){
        throw  new ItemNotFoundException("Resume is not found");
    }
        return optional.get();
    }


    public void create(ResumeCreate resume) {

    Resume resumeEntity = new Resume();
    resumeEntity.setFirstName(resume.getFirstName());
    resumeEntity.setLastName(resume.getLastName());
    resumeEntity.setPhoneNumber(resume.getPhoneNumber());
    resumeEntity.setAddress(resume.getAddress());
    resumeEntity.setCratedDate(LocalDateTime.now());







    }
}
