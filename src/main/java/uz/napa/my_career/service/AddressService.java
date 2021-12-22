package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.AddressDetail;
import uz.napa.my_career.entity.Address;
import uz.napa.my_career.entity.Districts;
import uz.napa.my_career.entity.Quarters;
import uz.napa.my_career.entity.Regions;
import uz.napa.my_career.exception.ServerBadRequestException;
import uz.napa.my_career.repository.AddressRepository;
import uz.napa.my_career.repository.DistrictsRepository;
import uz.napa.my_career.repository.QuartersRepository;
import uz.napa.my_career.repository.RegionsRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AddressService{
    @Autowired
    private AddressRepository repository;
    @Autowired
    private RegionsRepository regionsRepository;
    @Autowired
    private DistrictsRepository districtsRepository;
    @Autowired
    private QuartersRepository quartersRepository;

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

    // Get Region from DB
    public List<Regions> getRegions() {
        return regionsRepository.findAll();
    }

    // Get District from Region Id
    public List<Districts> getDistricts(Integer id){
        return districtsRepository.findAllByRegionId(id);
    }

    //Get Quarters from District Id
    public List<Quarters> getQuarters(Integer id){
        return quartersRepository.findAllByDistrictId(id);
    }


    //Secondary functions


    public static AddressDetail convertEntityToDto(Address entity) {
        return AddressDetail.builder()
                .country(entity.getCountry())
                .region_id(entity.getRegion_id())
                .district_id(entity.getDistrict_id())
                .street(entity.getStreet())
                .homeNum(entity.getHomeNum())
                .build();
    }

    public static Address convertDtoToEntity(AddressDetail dto) {
        return Address.builder()
                .country(dto.getCountry())
                .region_id(dto.getRegion_id())
                .district_id(dto.getDistrict_id())
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
