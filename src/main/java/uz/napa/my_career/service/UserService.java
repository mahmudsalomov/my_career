package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.AddressDetail;
import uz.napa.my_career.dto.UserDetail;
import uz.napa.my_career.entity.Address;
import uz.napa.my_career.entity.Role;
import uz.napa.my_career.entity.User;
import uz.napa.my_career.exception.ServerBadRequestException;
import uz.napa.my_career.repository.AddressRepository;
import uz.napa.my_career.repository.RoleRepository;
import uz.napa.my_career.repository.UserRepository;

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
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetail get(Long id) {
        User user = getUserEntity(id);
        UserDetail userDetail = new UserDetail();
        userDetail.setId(user.getId());
        userDetail.setUsername(user.getUsername());
        userDetail.setEmail(user.getEmail());
        userDetail.setFirstname(user.getFirstname());
        userDetail.setLastname(user.getLastname());
        userDetail.setPhone(user.getPhone());
        userDetail.setRoles(user.getRoles());

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


        User entity = new User();
        entity.setFirstname(user.getFirstname());
        entity.setLastname(user.getLastname());
        entity.setPhone(user.getPhone());
        entity.setEmail(user.getEmail());
        entity.setPassword(passwordEncoder.encode(user.getPassword()));
        entity.setAddress(address);
        entity.setUsername(user.getUsername());
        entity.setRoles(user.getRoles());
        userRepository.save(entity);
    }


    public void delete(Long id) {
        User user = getUserEntity(id);
        userRepository.delete(user);
    }
}

