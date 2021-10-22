package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.AddressDetail;
import uz.napa.my_career.dto.UserDetail;
import uz.napa.my_career.entity.Address;
import uz.napa.my_career.entity.User;
import uz.napa.my_career.exception.ServerBadRequestException;
import uz.napa.my_career.repository.AddressRepository;
import uz.napa.my_career.repository.RoleRepository;
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

    public UserDetail get(Long id) {
        User user = getUserEntity(id);
        UserDetail userDetail = UserDetail.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .phone(user.getPhone())
                .roles(user.getRoles())
                .build();

        AddressDetail addressDetail = new AddressDetail();
        if (user.getAddress() != null) {
            Optional<Address> optional = addressRepository.findById(user.getAddress().getId());
            if (optional.isPresent()) {
                Address address = optional.get();
                addressDetail.setCity(user.getAddress().getCity());
                addressDetail.setDistrict(user.getAddress().getDistrict());
                addressDetail.setCountry(user.getAddress().getCountry());
                addressDetail.setHomeNum(user.getAddress().getHomeNum());
                addressDetail.setRegion(user.getAddress().getRegion());
                addressDetail.setStreet(user.getAddress().getStreet());
                addressDetail.setId(user.getAddress().getId());
            }
        }


        userDetail.setAddress(addressDetail);
        return userDetail;
    }


    public User getUserEntity(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("User not found");
        }
        return optional.get();
    }

    public UserDetail update(Long userId, UserDetail user) {
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


    public void create(UserDetail user) {
        Optional<User> optional = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (optional.isPresent()) {
            throw new ServerBadRequestException("User with this email or username already exist");
        }
        AddressDetail addressDetail = user.getAddress();
        Address address = new Address();
        address.setCity(addressDetail.getCity());
        address.setCountry(addressDetail.getCountry());
        address.setDistrict(addressDetail.getDistrict());
        address.setRegion(addressDetail.getRegion());
        address.setStreet(addressDetail.getStreet());
        address.setHomeNum(addressDetail.getHomeNum());
        addressRepository.save(address);


        User entity = User.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .phone(user.getPhone())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .address(address)
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();

        userRepository.save(entity);
    }


    public void delete(Long id) {
        User user = getUserEntity(id);
        userRepository.delete(user);
    }

    public void setInfo(UserDetail detail) {
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
    }

}
