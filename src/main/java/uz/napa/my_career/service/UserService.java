package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.AddressDetail;
import uz.napa.my_career.dto.PasswordChangeDto;
import uz.napa.my_career.dto.UserDto;
import uz.napa.my_career.entity.Address;
import uz.napa.my_career.entity.User;
import uz.napa.my_career.exception.ServerBadRequestException;
import uz.napa.my_career.repository.AddressRepository;
import uz.napa.my_career.repository.UserNetworksRepository;
import uz.napa.my_career.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserNetworksRepository userNetworksRepository;

    public UserDto get(Long id) {
        User user = getUserEntity(id);
        return convertEntityToDto(user);
    }

    public UserDto update(Long userId, UserDto user) {
        Optional<User> optional = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (optional.isPresent()) {
            throw new ServerBadRequestException("User with this email or username already exist");
        }

        User entity = getUserEntity(userId);
        entity.setFirstname(user.getFirstname());
        entity.setLastname(user.getLastname());
        entity.setPhone(user.getPhone());
        entity.setUsername(user.getUsername());
        entity.setEmail(user.getEmail());
        entity.setPassword(passwordEncoder.encode(user.getPassword()));
        entity.setRoles(user.getRoles());

        if (!user.getNetworks().isEmpty()) {
            userNetworksRepository.saveAll(user.getNetworks());
        }

        Address address = entity.getAddress();
        address.setId(entity.getAddress().getId());
        address.setCity(user.getAddress().getCity());
        address.setCountry(user.getAddress().getCountry());
        address.setRegion(user.getAddress().getRegion());
        address.setDistrict(user.getAddress().getDistrict());
        address.setStreet(user.getAddress().getStreet());
        address.setHomeNum(user.getAddress().getHomeNum());

        addressRepository.save(address);
        userRepository.save(entity);

        return user;
    }


    public void create(UserDto dto) {
//        checking email and username
        Optional<User> optional = userRepository.findByUsernameOrEmail(dto.getUsername(), dto.getEmail());
        if (optional.isPresent()) {
            throw new ServerBadRequestException("User with this email or username already exist");
        }
        User user = convertDtoToEntity(dto);
        userRepository.save(user);
    }


    public void delete(Long id) {
        User user = getUserEntity(id);
        userRepository.delete(user);
    }

    public void setInfo(UserDto detail) {
        Address address = addressRepository.save(Address
                .builder()
                .country(detail.getAddress().getCountry())
                .city(detail.getAddress().getCity())
                .district(detail.getAddress().getDistrict())
                .homeNum(detail.getAddress().getHomeNum())
                .region(detail.getAddress().getRegion())
                .street(detail.getAddress().getStreet())
                .build()
        );
        addressRepository.save(address);

        User user = User.builder()
                .firstname(detail.getFirstname())
                .lastname(detail.getLastname())
                .username(detail.getUsername())
                .lastname(detail.getLastname())
                .phone(detail.getPhone())
                .email(detail.getEmail())
                .roles(detail.getRoles())
                .active(true)
                .address(address)
                .password(passwordEncoder.encode(detail.getPassword()))
                .build();
        userRepository.save(user);

        userNetworksRepository.saveAll(user.getNetworks());
    }

    public void changePassword(Long id, PasswordChangeDto dto) {
        Optional<User> optional = userRepository.findByIdAndPassword(id, passwordEncoder.encode(dto.getOldPassword()));
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("Error, user or password invalid!");
        }
        String firstPassword = dto.getFirstNewPassword();
        String secondPassword = dto.getSecondNewPassword();
        if (firstPassword.equals(secondPassword)) {
            throw new ServerBadRequestException("Passwords invalid");
        }
        User user = optional.get();
        user.setPassword(passwordEncoder.encode(firstPassword));
        userRepository.save(user);
    }


    //Secondary functions
    public User getUserEntity(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("User not found");
        }
        return optional.get();
    }

    public UserDto convertEntityToDto(User entity) {
        UserDto user = UserDto.builder()
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .active(entity.isActive())
                .build();
        if (entity.getAddress() != null) {
            user.setAddress(AddressService.convertEntityToDto(entity.getAddress()));
        }
        return user;
    }

    public User convertDtoToEntity(UserDto dto) {
        User user = User.builder()
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .phone(dto.getPhone())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .active(dto.isActive())
                .build();
        if (dto.getAddress() != null) {
            Address address = AddressService.convertDtoToEntity(dto.getAddress());
            addressRepository.save(address);
            user.setAddress(address);
        }
        return user;
    }
}

