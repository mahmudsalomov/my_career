package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.AddressDetail;
import uz.napa.my_career.dto.ExperienceDto;
import uz.napa.my_career.dto.OrganizationDto;
import uz.napa.my_career.entity.Address;
import uz.napa.my_career.entity.Experience;
import uz.napa.my_career.entity.Organization;
import uz.napa.my_career.exception.ServerBadRequestException;
import uz.napa.my_career.repository.AddressRepository;
import uz.napa.my_career.repository.ExperienceRepository;
import uz.napa.my_career.repository.OrganizationRepository;

import java.util.Optional;

@Service
public class ExperienceService {
    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrganizationRepository organizationRepository;

    public ExperienceDto get(Integer id) {
        Experience experience = getEntity(id);
        ExperienceDto experienceDto = convertEntityToDto(experience);
        if (experience.getOrganization() != null) {
            OrganizationDto organizationDto = OrganizationService.convertEntityToDto(experience.getOrganization());
            if (experience.getOrganization().getAddress() != null) {
                AddressDetail addressDetail = AddressService.convertEntityToDto(experience.getOrganization().getAddress());
                organizationDto.setAddress(addressDetail);
            }
            experienceDto.setOrganization(organizationDto);
        }
        return experienceDto;
    }

    public ExperienceDto create(ExperienceDto dto) {
        Experience experience = convertDtoToEntity(dto);
        if (dto.getOrganization() != null) {
            Organization organization = OrganizationService.convertDtoToEntity(dto.getOrganization());
            if (dto.getOrganization().getAddress() != null) {
                Address address = AddressService.convertDtoToEntity(dto.getOrganization().getAddress());
                addressRepository.save(address);
                organization.setAddress(address);
            }
            organizationRepository.save(organization);
            experience.setOrganization(organization);
        }
        experienceRepository.save(experience);
        dto.setId(experience.getId());
        return dto;
    }


    public ExperienceDto update(ExperienceDto dto) {
        Experience experience = convertDtoToEntity(dto);
        experience.setId(dto.getId());
        if (dto.getOrganization() != null) {
            Organization organization = OrganizationService.convertDtoToEntity(dto.getOrganization());
            if (dto.getOrganization().getAddress() != null) {
                Address address = AddressService.convertDtoToEntity(dto.getOrganization().getAddress());
                addressRepository.save(address);
                organization.setAddress(address);
            }
            organizationRepository.save(organization);
            experience.setOrganization(organization);
        }
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

                .build();
    }

    public static ExperienceDto convertEntityToDto(Experience entity) {
        return ExperienceDto.builder()
                .jobName(entity.getJobName())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .build();
    }
}

