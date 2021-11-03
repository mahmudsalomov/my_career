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

    public Resume create(ResumeCreateDto dto) {
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

//        resumeEntity.setExperienceSet(dto.getExperienceList());
        if (!dto.getExperienceList().isEmpty()) {
            for (Experience experience : dto.getExperienceList()) {
                experience = Experience.builder()
                        .jobName(experience.getJobName())
                        .startDate(experience.getStartDate())
                        .endDate(experience.getEndDate())
                        .build();
                experienceRepository.save(experience);
            }
        }

//        resumeEntity.setEducationSet(dto.getEducations());
        if (!dto.getEducations().isEmpty()) {
            for (Education education : dto.getEducations()) {
                if (education.getOrganization() != null) {
                    Organization organization = education.getOrganization();
                    if (organization.getAddress() != null) {
                        Address address = Address.builder()
                                .country(organization.getAddress().getCountry())
                                .region(organization.getAddress().getRegion())
                                .city(organization.getAddress().getCity())
                                .district(organization.getAddress().getCity())
                                .street(organization.getAddress().getDistrict())
                                .homeNum(organization.getAddress().getHomeNum())
                                .build();
                        addressRepository.save(address);
                        organization.setAddress(address);
                    }
                    organizationRepository.save(organization);
                }
                education = Education.builder()
                        .schoolName(education.getSchoolName())
                        .diplomaCode(education.getDiplomaCode())
                        .startDate(education.getStartDate())
                        .endDate(education.getEndDate())
                        //organization
//                       .organization(education.getOrganization())
                        .build();
                educationRepository.save(education);
            }
        }

//        resumeEntity.setSkills(dto.getSkillsList());
        if (!dto.getSkillsList().isEmpty()) {
            for (Skills skills : dto.getSkillsList()) {
                if (skills.getCategory() != null) {
                    SkillCategory category = skills.getCategory();
                    skillCategoryRepository.save(category);
                }
                skills = Skills.builder()
                        .name(skills.getName())
                        //category
//                        .category(skills.getCategory())
                        .build();
                skillRepository.save(skills);
            }
        }
        resumeRepository.save(resumeEntity);
        return resumeEntity;
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
