package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.UserDetail;
import uz.napa.my_career.entity.Address;
import uz.napa.my_career.entity.User;
import uz.napa.my_career.exception.ServerBadRequestException;
import uz.napa.my_career.repository.AddressRepository;
import uz.napa.my_career.repository.RoleRepository;
import uz.napa.my_career.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private RoleRepository roleRepository;

    public UserDetail get(Long id) {
        User user = getUserEntity(id);
        UserDetail userDetail = new UserDetail();
        userDetail.setUsername(user.getUsername());
        userDetail.setEmail(user.getEmail());
        userDetail.setFirstname(user.getFirstname());
        userDetail.setLastname(user.getLastname());
        userDetail.setPhone(user.getPhone());
        userDetail.setAddress(user.getAddress());
        userDetail.setRoles(user.getRoles());
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
        Optional<User> optional = userRepository.findByEmail(user.getEmail());
        if (optional.isPresent()) {
            throw new ServerBadRequestException("Username already exist");
        }
        optional = userRepository.findByUsername(user.getUsername());
        if (optional.isPresent()) {
            throw new ServerBadRequestException("Email already exist");
        }
        Address oldAddress = user.getAddress();
        Address newAddress = new Address();
        newAddress.setCity(oldAddress.getCity());
        newAddress.setCountry(oldAddress.getCountry());
        newAddress.setDistrict(oldAddress.getDistrict());
        newAddress.setRegion(oldAddress.getRegion());
        newAddress.setStreet(oldAddress.getStreet());
        newAddress.setHomeNum(oldAddress.getHomeNum());
        addressRepository.save(newAddress);

        User entity = getUserEntity(userId);
        entity.setFirstname(user.getFirstname());
        entity.setLastname(user.getLastname());
        entity.setPhone(user.getPhone());
        entity.setAddress(newAddress);
        entity.setUsername(user.getUsername());
        userRepository.save(entity);

        return user;
    }




}
