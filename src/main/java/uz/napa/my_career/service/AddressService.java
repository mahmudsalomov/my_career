package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.AddressDetail;
import uz.napa.my_career.entity.Address;
import uz.napa.my_career.exception.ServerBadRequestException;
import uz.napa.my_career.repository.AddressRepository;

import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository repository;

    //Get address DTO by id
    public AddressDetail get(Integer id) {
        Address address = getAddressEntity(id);
        return convertEntityToDto(address);
    }

    //Create new Address
    public AddressDetail create(AddressDetail addressDetail) {
        Address address = convertDtoToEntity(addressDetail);
        repository.save(address);
        addressDetail.setId(address.getId());
        return addressDetail;
    }

    //Update address
    public AddressDetail update(AddressDetail addressDetail) {
        Address address = getAddressEntity(addressDetail.getId());
        address = convertDtoToEntity(addressDetail);
        address.setId(addressDetail.getId());
        repository.save(address);
        return addressDetail;
    }

    //Delete address from DB
    public void delete(Integer id) {
        Address address = getAddressEntity(id);
        repository.delete(address);
    }


    //Secondary functions


    public static AddressDetail convertEntityToDto(Address entity) {
        return AddressDetail.builder()
                .country(entity.getCountry())
                .city(entity.getCity())
                .region(entity.getRegion())
                .district(entity.getDistrict())
                .street(entity.getStreet())
                .homeNum(entity.getHomeNum())
                .build();
    }

    public static Address convertDtoToEntity(AddressDetail dto) {
        return Address.builder()
                .country(dto.getCountry())
                .city(dto.getCity())
                .region(dto.getRegion())
                .district(dto.getDistrict())
                .street(dto.getStreet())
                .homeNum(dto.getHomeNum())
                .build();
    }

    public Address getAddressEntity(Integer id) {
        Optional<Address> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("Address not found");
        }
        return optional.get();
    }
}
