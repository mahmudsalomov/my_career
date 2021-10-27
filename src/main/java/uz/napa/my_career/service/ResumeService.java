package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.resume.ResumeDetailDto;
import uz.napa.my_career.entity.Address;
import uz.napa.my_career.entity.Resume;
import uz.napa.my_career.repository.AddressRepository;
import uz.napa.my_career.repository.ResumeRepository;
import uz.napa.my_career.repository.SkillRepository;

@Service
public class ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private AddressRepository addressRepository;

    public ResumeDetailDto getDetail(Long resumeId){

        Address addressEntity = new Address();


        Resume resumeEntity = new Resume();
        ResumeDetailDto dto  = new ResumeDetailDto();
        dto.setFirstName(resumeEntity.getUser().getFirstname());
        dto.setLastName(resumeEntity.getUser().getLastname());
        dto.setEmail(resumeEntity.getUser().getEmail());
        dto.setPhone(resumeEntity.getUser().getPhone());
        dto.setAddress(resumeEntity.getUser().getAddress());











        return null;
    }



















//
//public ResumeDetailDto resumeDetail(Long resumeId){
//
//    Resume resumeEntity = get(resumeId);
//
//    ResumeDetailDto resumeDetail = new ResumeDetailDto();
//    resumeDetail.setFirstName(resumeEntity.getFirstName());
//    resumeDetail.setLastName(resumeEntity.getLastName());
//    resumeDetail.setAddress(resumeEntity.getAddress());
//    resumeDetail.setPhone(resumeEntity.getUser().getPhone());
//    resumeDetail.setEmail(resumeEntity.getUser().getEmail());
//   resumeDetail.setSkillsList(skillRepository.getList);
//
//    return resumeDetail;
//}
    
//    private Resume get(Long resumeId) {
//
//    Optional<Resume> optional =this.resumeRepository.findById(resumeId);
//    if(!optional.isPresent()){
//        throw  new ItemNotFoundException("Resume is not found");
//    }
//        return optional.get();
//    }


//    public ResumeCreateDto create(ResumeCreateDto resume) {
//
////    Resume resumeEntity = new Resume();
////    resumeEntity.setUser().setFirstName(resume.getFirstName());
////    resumeEntity.setUser().setLastName(resume.getLastName());
////    resumeEntity.setPhoneNumber(resume.getPhoneNumber());
////    resumeEntity.setAddress(resume.getAddress());
////    resumeEntity.setCratedDate(LocalDateTime.now());
////    resumeEntity.setSkills(resume.getSkillsList());
////    resumeEntity.setEducations(resume.getEducations());
////    resumeEntity.setExperiences(resume.getExperienceList());
////    resumeEntity.setActive(false);
//
//    this.resumeRepository.save(resumeEntity);
//    resume.setId(resumeEntity.getId());
//    return resume;
//    }



}
