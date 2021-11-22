package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.*;
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

    public ResumeDto getDetail(Long resumeId) {
        HashSet<ExperienceDto> experienceHashSet = new HashSet<>();
        HashSet<SkillsDto> skillsHashSet = new HashSet<>();
        HashSet<EducationDto> educationHashSet = new HashSet<>();

        Resume entity = getEntityById(resumeId);
        UserDto userDto = UserService.convertEntityToDto(getUserById(entity.getUser().getId()));
        ResumeDto dto = convertEntityToDto(entity);
        if (!entity.getExperienceSet().isEmpty()) {
            for (Experience experience : entity.getExperienceSet()) {
                ExperienceDto experienceDto = ExperienceService.convertEntityToDto(experience);
                if (experience.getOrganization() != null) {
                    OrganizationDto organizationDto = OrganizationService.convertEntityToDto(experience.getOrganization());
                    if (experience.getOrganization().getAddress() != null) {
                        AddressDetail addressDetail = AddressService.convertEntityToDto(experience.getOrganization().getAddress());
                        organizationDto.setAddress(addressDetail);
                    }
                    experienceDto.setOrganization(organizationDto);
                }
                experienceHashSet.add(experienceDto);
            }
            dto.setExperienceList(experienceHashSet);
        }
        if (!entity.getEducationSet().isEmpty()) {
            for (Education education : entity.getEducationSet()) {
                EducationDto educationDto = EducationService.convertEntityToDto(education);
                if (education.getOrganization() != null) {
                    OrganizationDto organizationDto = OrganizationService.convertEntityToDto(education.getOrganization());
                    if (education.getOrganization().getAddress() != null) {
                        AddressDetail addressDetail = AddressService.convertEntityToDto(education.getOrganization().getAddress());
                        organizationDto.setAddress(addressDetail);
                    }
                    educationDto.setOrganization(organizationDto);
                }
                educationHashSet.add(educationDto);
            }
            dto.setEducationList(educationHashSet);
        }
        if (!entity.getSkills().isEmpty()) {
            for (Skills skill : entity.getSkills()) {
                skillsHashSet.add(SkillsService.convertEntityToDto(skill));
            }
            dto.setSkillsList(skillsHashSet);
        }
        dto.setUserDto(userDto);
        return dto;
    }

    public ResumeDto create(ResumeDto dto) {
        HashSet<Experience> experienceHashSet = new HashSet<>();
        HashSet<Skills> skillsHashSet = new HashSet<>();
        HashSet<Education> educationHashSet = new HashSet<>();

        //find user by id
        Optional<User> userOptional = userRepository.findById(dto.getUserId());
        if (userOptional.isEmpty()) {
            throw new ServerBadRequestException("This user not found.");
        }

        //resume creating
        Resume resumeEntity = convertDtoToEntity(dto);

        //experience process
        if (!dto.getExperienceList().isEmpty()) {
            for (ExperienceDto experienceDto : dto.getExperienceList()) {
                Experience experienceEntity = ExperienceService.convertDtoToEntity(experienceDto);
                if (experienceDto.getOrganization() != null) {
                    Organization organizationEntity = OrganizationService.convertDtoToEntity(experienceDto.getOrganization());
                    if (experienceDto.getOrganization().getAddress() != null) {
                        Address addressEntity = AddressService.convertDtoToEntity(experienceDto.getOrganization().getAddress());
                        addressRepository.save(addressEntity);
                        organizationEntity.setAddress(addressEntity);
                    }
                    organizationRepository.save(organizationEntity);
                    experienceEntity.setOrganization(organizationEntity);
                }
                experienceHashSet.add(experienceEntity);
                experienceRepository.save(experienceEntity);
            }

        }

        if (!dto.getEducationList().isEmpty()) {
            for (EducationDto educationDto : dto.getEducationList()) {
                Education educationEntity = EducationService.convertDtoToEntity(educationDto);
                if (educationDto.getOrganization() != null) {
                    Organization organizationEntity = OrganizationService.convertDtoToEntity(educationDto.getOrganization());
                    if (educationDto.getOrganization().getAddress() != null) {
                        Address addressEntity = AddressService.convertDtoToEntity(educationDto.getOrganization().getAddress());
                        addressRepository.save(addressEntity);
                        organizationEntity.setAddress(addressEntity);
                    }
                    organizationRepository.save(organizationEntity);
                    educationEntity.setOrganization(organizationEntity);
                }
                educationHashSet.add(educationEntity);
                educationRepository.save(educationEntity);
            }
        }

        if (!dto.getSkillsList().isEmpty()) {
            for (SkillsDto skillsDto : dto.getSkillsList()) {
                Skills skillsEntity = SkillsService.convertDtoToEntity(skillsDto);
                if (skillsDto.getCategory() != null) {
                    skillCategoryRepository.save(skillsDto.getCategory());
                    skillsEntity.setCategory(skillsDto.getCategory());
                }
                skillsHashSet.add(skillsEntity);
                skillRepository.save(skillsEntity);
            }
        }
        resumeEntity.setUser(userOptional.get());
        resumeEntity.setExperienceSet(experienceHashSet);
        resumeEntity.setEducationSet(educationHashSet);
        resumeEntity.setSkills(skillsHashSet);
        resumeEntity.setCreatedDate(LocalDateTime.now());
        resumeRepository.save(resumeEntity);
        dto.setId(resumeEntity.getId());
        return dto;
    }

    public Resume update(ResumeDto dto) {
        HashSet<Experience> experienceHashSet = new HashSet<>();
        HashSet<Skills> skillsHashSet = new HashSet<>();
        HashSet<Education> educationHashSet = new HashSet<>();

        Resume resumeEntity = getEntityById(dto.getId());
        User userEntity = getUserById(dto.getUserId());
        //experience process
        if (!dto.getExperienceList().isEmpty()) {
            for (ExperienceDto experienceDto : dto.getExperienceList()) {
                Experience experienceEntity = ExperienceService.convertDtoToEntity(experienceDto);
                if (experienceDto.getOrganization() != null) {
                    Organization organizationEntity = OrganizationService.convertDtoToEntity(experienceDto.getOrganization());
                    if (experienceDto.getOrganization().getAddress() != null) {
                        Address addressEntity = AddressService.convertDtoToEntity(experienceDto.getOrganization().getAddress());
                        addressRepository.save(addressEntity);
                        organizationEntity.setAddress(addressEntity);
                    }
                    organizationRepository.save(organizationEntity);
                    experienceEntity.setOrganization(organizationEntity);
                }
                experienceHashSet.add(experienceEntity);
                experienceRepository.save(experienceEntity);
            }

        }

        if (!dto.getEducationList().isEmpty()) {
            for (EducationDto educationDto : dto.getEducationList()) {
                Education educationEntity = EducationService.convertDtoToEntity(educationDto);
                if (educationDto.getOrganization() != null) {
                    Organization organizationEntity = OrganizationService.convertDtoToEntity(educationDto.getOrganization());
                    if (educationDto.getOrganization().getAddress() != null) {
                        Address addressEntity = AddressService.convertDtoToEntity(educationDto.getOrganization().getAddress());
                        addressRepository.save(addressEntity);
                        organizationEntity.setAddress(addressEntity);
                    }
                    organizationRepository.save(organizationEntity);
                    educationEntity.setOrganization(organizationEntity);
                }
                educationHashSet.add(educationEntity);
                educationRepository.save(educationEntity);
            }
        }

        if (!dto.getSkillsList().isEmpty()) {
            for (SkillsDto skillsDto : dto.getSkillsList()) {
                Skills skillsEntity = SkillsService.convertDtoToEntity(skillsDto);
                if (skillsDto.getCategory() != null) {
                    skillCategoryRepository.save(skillsDto.getCategory());
                    skillsEntity.setCategory(skillsDto.getCategory());
                }
                skillsHashSet.add(skillsEntity);
                skillRepository.save(skillsEntity);
            }
        }

        //resume details updating
        resumeEntity.setId(dto.getId());
        resumeEntity.setUser(userEntity);
        resumeEntity.setExperienceSet(experienceHashSet);
        resumeEntity.setEducationSet(educationHashSet);
        resumeEntity.setSkills(skillsHashSet);
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

    //Secondary functions
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

    public static Resume convertDtoToEntity(ResumeDto dto) {
        return Resume.builder()
                .aboutMe(dto.getAboutMe())
                .coverLetter(dto.getCoverLetter())
                .build();
    }

    public static ResumeDto convertEntityToDto(Resume entity) {
        return ResumeDto.builder()
                .aboutMe(entity.getAboutMe())
                .coverLetter(entity.getCoverLetter())
                .createdDate(entity.getCreatedDate())
                .build();
    }
}
