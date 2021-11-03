package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.resume.ResumeCreateDto;
import uz.napa.my_career.dto.resume.ResumeDetailDto;
import uz.napa.my_career.dto.resume.ResumeUpdateDto;
import uz.napa.my_career.entity.Resume;
import uz.napa.my_career.entity.User;
import uz.napa.my_career.exception.ServerBadRequestException;
import uz.napa.my_career.repository.ResumeRepository;
import uz.napa.my_career.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ResumeService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResumeRepository resumeRepository;

    public void getDetail(Long resumeId) {

        Resume resumeEntity = getEntityById(resumeId);
        ResumeDetailDto dto = new ResumeDetailDto();

        dto.setFirstName(resumeEntity.getUser().getFirstname());
        dto.setLastName(resumeEntity.getUser().getLastname());
        dto.setEmail(resumeEntity.getUser().getEmail());
        dto.setPhone(resumeEntity.getUser().getPhone());
        dto.setAddress(resumeEntity.getUser().getAddress());
        dto.setCratedDate(LocalDateTime.now());
        dto.setAboutMe(resumeEntity.getAboutMe());
        dto.setCoverLater(resumeEntity.getCoverLetter());
        dto.setExperienceList(resumeEntity.getExperienceSet());
        dto.setEducationList(resumeEntity.getEducationSet());
        dto.setSkillsList(resumeEntity.getSkills());

    }

    public ResumeCreateDto create(ResumeCreateDto dto ){

        //checkin for email
//        Optional<User> optional = UserRepository.findByEmail(dto.getEmail());
//        if (optional.isPresent()){
//            throw new ServerBadRequestException("This Email already exists.");
//        }

        Resume resumeEntity = new Resume();
        //user creating
        User userEntity = resumeEntity.getUser();
        userEntity.setFirstname(dto.getFirstName());
        userEntity.setLastname(dto.getLastName());
        userEntity.setEmail(dto.getEmail());
        userEntity.setPhone(dto.getPhoneNumber());
        userEntity.setAddress(dto.getAddress());
        //resume creating
        resumeEntity.setUser(userEntity);
        resumeEntity.setAboutMe(dto.getAboutMe());
        resumeEntity.setCoverLetter(dto.getCoverLater());
        resumeEntity.setExperienceSet(dto.getExperienceList());
        resumeEntity.setEducationSet(dto.getEducations());
        resumeEntity.setSkills(dto.getSkillsList());
        resumeEntity.setCratedDate(LocalDateTime.now());
        return dto;
    }

    public Boolean update(Long id, ResumeCreateDto dto){
        Resume resumeEntity = getEntityById(id);
        User userEntity = resumeEntity.getUser();

        //find resume
//                Optional<User> optional = UserRepository.findById(dto.getId());
//        if (!optional.isPresent()){
//            throw new ServerBadRequestException("This resume not found.");
//        }

        //user details updating
        userEntity.setFirstname(dto.getFirstName());
        userEntity.setLastname(dto.getLastName());
        userEntity.setEmail(dto.getEmail());
        userEntity.setPhone(dto.getPhoneNumber());
        userEntity.setAddress(dto.getAddress());
        //resume details updating
        resumeEntity.setUser(userEntity);
        resumeEntity.setAboutMe(dto.getAboutMe());
        resumeEntity.setCoverLetter(dto.getCoverLater());
        resumeEntity.setExperienceSet(dto.getExperienceList());
        resumeEntity.setEducationSet(dto.getEducations());
        resumeEntity.setSkills(dto.getSkillsList());
        return true;
    }

    public Boolean delete(Long id, ResumeCreateDto dto){
        //find resume
//                Optional<User> optional = UserRepository.findById(dto.getId());
//        if (!optional.isPresent()){
//            throw new ServerBadRequestException("This resume not found.");
//        }


        return true;
    }











    public Resume getEntityById(Long id) {
        Optional<Resume> optional = resumeRepository.findById(id);
        if (optional.isEmpty()){
            throw new ServerBadRequestException("Resume not found");
        }
        return optional.get();
    }

}
