package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.UserNetworksDto;
import uz.napa.my_career.entity.User;
import uz.napa.my_career.entity.UserNetworks;
import uz.napa.my_career.exception.ServerBadRequestException;
import uz.napa.my_career.repository.UserNetworksRepository;

import java.util.Optional;

@Service
public class UserNetworksService {
    @Autowired
    private UserNetworksRepository userNetworksRepository;

    public UserNetworksDto get(Integer id) {
        UserNetworks userNetworks = getEntity(id);
        return convertEntityToDto(userNetworks);
    }

    public UserNetworksDto create(UserNetworksDto dto) {
        UserNetworks userNetworks = convertDtoToEntity(dto);
        userNetworksRepository.save(userNetworks);
        dto.setId(userNetworks.getId());
        return dto;
    }

    public UserNetworksDto update(UserNetworksDto dto) {
        UserNetworks userNetworks = getEntity(dto.getId());
        userNetworks = convertDtoToEntity(dto);
        userNetworks.setId(dto.getId());
        userNetworksRepository.save(userNetworks);
        return dto;
    }

    public void delete(Integer id) {
        UserNetworks userNetworks = getEntity(id);
        userNetworksRepository.delete(userNetworks);
    }

    //Secondary functions
    public UserNetworks getEntity(Integer id) {
        Optional<UserNetworks> optional = userNetworksRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("User networks with this is not found");
        }
        return optional.get();
    }

    public static UserNetworks convertDtoToEntity(UserNetworksDto dto) {
        return UserNetworks.builder()
                .link(dto.getLink())
                .type(dto.getType())
                .build();
    }

    public static UserNetworksDto convertEntityToDto(UserNetworks entity) {
        return UserNetworksDto.builder()
                .link(entity.getLink())
                .type(entity.getType())
                .build();
    }
}
