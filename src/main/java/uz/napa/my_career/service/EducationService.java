package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.EducationDto;
import uz.napa.my_career.dto.OrganizationDto;
import uz.napa.my_career.entity.Address;
import uz.napa.my_career.entity.Education;
import uz.napa.my_career.entity.Organization;
import uz.napa.my_career.exception.ServerBadRequestException;
import uz.napa.my_career.repository.AddressRepository;
import uz.napa.my_career.repository.EducationRepository;
import uz.napa.my_career.repository.OrganizationRepository;

import java.util.Optional;

@Service
public class EducationService {
    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private AddressRepository addressRepository;

    //Main functions
    public EducationDto create(EducationDto dto) {
        Education entity = convertDtoToEntity(dto);
        if (dto.getOrganization() != null) {
            Organization organizationEntity = OrganizationService.convertDtoToEntity(dto.getOrganization());
            organizationRepository.save(organizationEntity);
            if (dto.getOrganization().getAddress() != null) {
                Address addressEntity = AddressService.convertDtoToEntity(dto.getOrganization().getAddress());
                addressRepository.save(addressEntity);
            }
            entity.setOrganization(organizationEntity);
        }
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
                .build();
    }

    public static EducationDto convertEntityToDto(Education entity) {
        return EducationDto.builder()
                .diplomaCode(entity.getDiplomaCode())
                .endDate(entity.getEndDate())
                .startDate(entity.getStartDate())
                .schoolName(entity.getSchoolName())
                .build();
    }
}
