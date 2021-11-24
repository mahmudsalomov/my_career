package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.AddressDetail;
import uz.napa.my_career.dto.OrganizationDto;
import uz.napa.my_career.entity.Address;
import uz.napa.my_career.entity.Organization;
import uz.napa.my_career.exception.ServerBadRequestException;
import uz.napa.my_career.repository.AddressRepository;
import uz.napa.my_career.repository.OrganizationRepository;

import javax.persistence.Access;
import java.util.Optional;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private AddressRepository addressRepository;

    //Main functions
    public OrganizationDto create(OrganizationDto dto) {
        Organization organization = convertDtoToEntity(dto);
        if (dto.getAddress() != null) {
            Address addressEntity = AddressService.convertDtoToEntity(dto.getAddress());
            organization.setAddress(addressEntity);
            addressRepository.save(addressEntity);
        }
        organizationRepository.save(organization);
        dto.setId(organization.getId());
        return dto;
    }

    public OrganizationDto update(OrganizationDto dto) {
        Organization organization = convertDtoToEntity(dto);
        organization.setId(dto.getId());
        if (dto.getAddress() != null) {
            Address addressEntity = AddressService.convertDtoToEntity(dto.getAddress());
            organization.setAddress(addressEntity);
            addressRepository.save(addressEntity);
        }
        organizationRepository.save(organization);
        dto.setId(organization.getId());
        return dto;
    }

    public OrganizationDto get(Integer id) {
        Organization organization = getEntity(id);
        OrganizationDto organizationDto = convertEntityToDto(organization);
        if (organization.getAddress() != null) {
            AddressDetail addressDetail = AddressService.convertEntityToDto(organization.getAddress());
            organizationDto.setAddress(addressDetail);
        }
        return organizationDto;
    }

    public void delete(Integer id) {
        Organization organization = getEntity(id);
        organizationRepository.delete(organization);
    }

    //Secondary functions
    public Organization getEntity(Integer id) {
        Optional<Organization> optional = organizationRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("Organization with this id not found");
        }
        return optional.get();
    }

    public static Organization convertDtoToEntity(OrganizationDto dto) {
        return Organization.builder()
                .name(dto.getName())
                .build();
    }

    public static OrganizationDto convertEntityToDto(Organization entity) {
        return OrganizationDto.builder()
                .name(entity.getName())
                .build();
    }
}
