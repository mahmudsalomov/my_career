package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.resume.ResumeCreateDto;
import uz.napa.my_career.dto.resume.ResumeDetailDto;
import uz.napa.my_career.entity.*;
import uz.napa.my_career.exception.ServerBadRequestException;
import uz.napa.my_career.repository.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

@Service
public class ResumeService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    ExperienceRepository experienceRepository;
    @Autowired
    EducationRepository educationRepository;
    @Autowired
    SkillRepository skillRepository;
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    SkillCategoryRepository skillCategoryRepository;

    public Resume getDetail(Long resumeId) {
        return getEntityById(resumeId);
    }

    public Resume create(ResumeCreateDto dto) {
        HashSet<Experience> experienceHashSet = new HashSet<>();
        HashSet<Skills> skillsHashSet = new HashSet<>();
        HashSet<Education> educationHashSet = new HashSet<>();
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
        resumeEntity.setCratedDate(LocalDateTime.now());

        //experience process
        if (!dto.getExperienceList().isEmpty()) {
            for (Experience experience : dto.getExperienceList()) {
                addressRepository.save(experience.getOrganization().getAddress());
                organizationRepository.save(experience.getOrganization());
                experienceHashSet.add(experience);
                experienceRepository.save(experience);
            }

        }

//        resumeEntity.setEducationSet(dto.getEducations());
        if (!dto.getEducations().isEmpty()) {
            for (Education education : dto.getEducations()) {
                addressRepository.save(education.getOrganization().getAddress());
                organizationRepository.save(education.getOrganization());
                educationHashSet.add(education);
                educationRepository.save(education);
            }
        }

//        resumeEntity.setSkills(dto.getSkillsList());
        if (!dto.getSkillsList().isEmpty()) {
            for (Skills skill : dto.getSkillsList()) {
                skillCategoryRepository.save(skill.getCategory());
                skillsHashSet.add(skill);
                skillRepository.save(skill);
            }
        }
        resumeEntity.setExperienceSet(experienceHashSet);
        resumeEntity.setEducationSet(educationHashSet);
        resumeEntity.setSkills(skillsHashSet);
        resumeRepository.save(resumeEntity);
        return resumeEntity;
    }


    public Resume update(Long id, ResumeCreateDto dto) {
        HashSet<Experience> experienceHashSet = new HashSet<>();
        HashSet<Skills> skillsHashSet = new HashSet<>();
        HashSet<Education> educationHashSet = new HashSet<>();

        Resume resumeEntity = getEntityById(id);
        User userEntity = getUserById(dto.getUserId());
        //experience process
        if (!dto.getExperienceList().isEmpty()) {
            for (Experience experience : dto.getExperienceList()) {
                addressRepository.save(experience.getOrganization().getAddress());
                organizationRepository.save(experience.getOrganization());
                experienceHashSet.add(experience);
                experienceRepository.save(experience);
            }

        }

//        resumeEntity.setEducationSet(dto.getEducations());
        if (!dto.getEducations().isEmpty()) {
            for (Education education : dto.getEducations()) {
                addressRepository.save(education.getOrganization().getAddress());
                organizationRepository.save(education.getOrganization());
                educationHashSet.add(education);
                educationRepository.save(education);
            }
        }

//        resumeEntity.setSkills(dto.getSkillsList());
        if (!dto.getSkillsList().isEmpty()) {
            for (Skills skill : dto.getSkillsList()) {
                skillCategoryRepository.save(skill.getCategory());
                skillsHashSet.add(skill);
                skillRepository.save(skill);
            }
        }

        //resume details updating
        resumeEntity.setUser(userEntity);
        resumeEntity.setAboutMe(dto.getAboutMe());
        resumeEntity.setCoverLetter(dto.getCoverLater());
        resumeEntity.setExperienceSet(dto.getExperienceList());
        resumeEntity.setEducationSet(dto.getEducations());
        resumeEntity.setSkills(dto.getSkillsList());
        resumeRepository.save(resumeEntity);
        return resumeEntity;
    }

    public Boolean delete(Long id) {
        //find resume
        Optional<Resume> optional = resumeRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("This resume not found.");
        }
        resumeRepository.delete(optional.get());
        return true;
    }


    public Resume getEntityById(Long id) {
        Optional<Resume> optional = resumeRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("Resume not found");
        }
        return optional.get();
    }

    public User getUserById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("This user not found.");
        }
        return optional.get();
    }
}
