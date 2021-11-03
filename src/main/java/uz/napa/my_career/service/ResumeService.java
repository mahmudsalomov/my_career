package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.resume.ResumeCreateDto;
import uz.napa.my_career.dto.resume.ResumeDetailDto;
import uz.napa.my_career.dto.resume.ResumeUpdateDto;
import uz.napa.my_career.entity.Experience;
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

        //find user by id
        Optional<User> optional = userRepository.findById(dto.getId());
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("This user not found.");
        }
        User userEntity = optional.get();

        //find resume details
        dto.setCratedDate(LocalDateTime.now());
        dto.setAboutMe(resumeEntity.getAboutMe());
        dto.setCoverLater(resumeEntity.getCoverLetter());

        dto.setExperienceList(resumeEntity.getExperienceSet());
        dto.setEducationList(resumeEntity.getEducationSet());
        dto.setSkillsList(resumeEntity.getSkills());

    }

    public ResumeCreateDto create(ResumeCreateDto dto) {
        Resume resumeEntity = new Resume();

        //find user by id
        Optional<User> optional = userRepository.findById(dto.getUserId());
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("This user not found.");
        }
        User userEntity = optional.get();

        //resume creating
        resumeEntity.setUser(userEntity);
        resumeEntity.setAboutMe(dto.getAboutMe());
        resumeEntity.setCoverLetter(dto.getCoverLater());


//        resumeEntity.setExperienceSet(dto.getExperienceList());
//        resumeEntity.setEducationSet(dto.getEducations());
//        resumeEntity.setSkills(dto.getSkillsList());
        resumeEntity.setCratedDate(LocalDateTime.now());
        resumeRepository.save(resumeEntity);
        return dto;
    }

    public Boolean update(Long id, ResumeCreateDto dto) {

        Resume resumeEntity = getEntityById(id);
        User userEntity = resumeEntity.getUser();

        //        find user by id
        Optional<User> optional = userRepository.findById(dto.getId());
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("This user not found.");
        }

        //resume details updating
        resumeEntity.setUser(userEntity);
        resumeEntity.setAboutMe(dto.getAboutMe());
        resumeEntity.setCoverLetter(dto.getCoverLater());
        resumeEntity.setExperienceSet(dto.getExperienceList());
        resumeEntity.setEducationSet(dto.getEducations());
        resumeEntity.setSkills(dto.getSkillsList());
        return true;
    }

    public Boolean delete(Long id, ResumeCreateDto dto) {
        //find resume
        Optional<User> optional = userRepository.findById(dto.getId());
        if (!optional.isPresent()) {
            throw new ServerBadRequestException("This resume not found.");
        }


        return true;
    }


    public Resume getEntityById(Long id) {
        Optional<Resume> optional = resumeRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("Resume not found");
        }
        return optional.get();
    }

}
