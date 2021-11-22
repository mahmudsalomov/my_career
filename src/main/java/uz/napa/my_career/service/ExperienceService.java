package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.ExperienceDto;
import uz.napa.my_career.dto.OrganizationDto;
import uz.napa.my_career.entity.Experience;
import uz.napa.my_career.entity.Organization;
import uz.napa.my_career.exception.ServerBadRequestException;
import uz.napa.my_career.repository.ExperienceRepository;

import java.util.Optional;

@Service
public class ExperienceService {
    @Autowired
    private ExperienceRepository experienceRepository;

    public ExperienceDto get(Integer id) {
        return convertEntityToDto(getEntity(id));
    }

    public ExperienceDto create(ExperienceDto dto) {
        Experience experience = convertDtoToEntity(dto);
        experienceRepository.save(experience);
        dto.setId(experience.getId());
        return dto;
    }

    public ExperienceDto update(ExperienceDto dto) {
        Experience experience = convertDtoToEntity(dto);
        experience.setId(dto.getId());
        experienceRepository.save(experience);
        return dto;
    }

    public void delete(Integer id) {
        Experience experience = getEntity(id);
        experienceRepository.delete(experience);
    }

    // Secondary functions
    public Experience getEntity(Integer id) {
        Optional<Experience> optional = experienceRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("Experience with this not found");
        }
        return optional.get();
    }

    public static Experience convertDtoToEntity(ExperienceDto dto) {
        return Experience.builder()
                .jobName(dto.getJobName())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .organization(Organization.builder()
                        .name(dto.getOrganization().getName())
                        .address(AddressService.convertDtoToEntity(dto.getOrganization().getAddress()))
                        .build())
                .build();
    }

    public static ExperienceDto convertEntityToDto(Experience entity) {
        return ExperienceDto.builder()
                .jobName(entity.getJobName())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .organization(OrganizationDto.builder()
                        .name(entity.getOrganization().getName())
                        .address(AddressService.convertEntityToDto(entity.getOrganization().getAddress()))
                        .build())
                .build();
    }
}

