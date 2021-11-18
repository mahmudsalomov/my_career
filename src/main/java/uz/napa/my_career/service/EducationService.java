package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.EducationDto;
import uz.napa.my_career.dto.OrganizationDto;
import uz.napa.my_career.entity.Education;
import uz.napa.my_career.entity.Organization;
import uz.napa.my_career.exception.ServerBadRequestException;
import uz.napa.my_career.repository.EducationRepository;

import java.util.Optional;

@Service
public class EducationService {
    @Autowired
    private EducationRepository educationRepository;


    //Main functions
    public EducationDto create(EducationDto dto) {
        Education entity = convertDtoToEntity(dto);
        educationRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public EducationDto get(Integer id) {
        Education entity = getEntity(id);
        return convertEntityToDto(entity);
    }

    public EducationDto update(EducationDto dto) {
        Education education = getEntity(dto.getId());
        education = convertDtoToEntity(dto);
        education.setId(dto.getId());
        educationRepository.save(education);
        return dto;
    }

    public void delete(Integer id) {
        Education education = getEntity(id);
        educationRepository.delete(education);
    }

    //Secondary functions
    public Education getEntity(Integer id) {
        Optional<Education> optional = educationRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("Education with this id not found!");
        }
        return optional.get();
    }

    public static Education convertDtoToEntity(EducationDto dto) {
        return Education.builder()
                .diplomaCode(dto.getDiplomaCode())
                .schoolName(dto.getSchoolName())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .organization(Organization.builder()
                        .name(dto.getOrganization().getName())
                        .address(AddressService.convertDtoToEntity(dto.getOrganization().getAddress()))
                        .build())
                .build();
    }

    public static EducationDto convertEntityToDto(Education entity) {
        return EducationDto.builder()
                .diplomaCode(entity.getDiplomaCode())
                .endDate(entity.getEndDate())
                .startDate(entity.getStartDate())
                .schoolName(entity.getSchoolName())
                .organization(OrganizationDto.builder()
                        .name(entity.getOrganization().getName())
                        .address(AddressService.convertEntityToDto(entity.getOrganization().getAddress()))
                        .build())
                .build();
    }
}
