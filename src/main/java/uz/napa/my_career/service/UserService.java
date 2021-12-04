package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.AddressDetail;
import uz.napa.my_career.dto.PasswordChangeDto;
import uz.napa.my_career.dto.UserDto;
import uz.napa.my_career.dto.UserNetworksDto;
import uz.napa.my_career.entity.Address;
import uz.napa.my_career.entity.Role;
import uz.napa.my_career.entity.User;
import uz.napa.my_career.entity.UserNetworks;
import uz.napa.my_career.exception.ServerBadRequestException;
import uz.napa.my_career.repository.AddressRepository;
import uz.napa.my_career.repository.RoleRepository;
import uz.napa.my_career.repository.UserNetworksRepository;
import uz.napa.my_career.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserNetworksRepository userNetworksRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserNetworksService userNetworksService;

    public UserDto get(Long id) {
        User entity = getUserEntity(id);
        UserDto dto = convertEntityToDto(entity);
        if (entity.getAddress() != null) {
            dto.setAddress(AddressService.convertEntityToDto(entity.getAddress()));
        }
        if (entity.getRoles() != null) {
            dto.setRoles(entity.getRoles());
        }
        if (entity.getNetworks() != null) {
            Set<UserNetworks> userNetworksEntitySet = entity.getNetworks();
            Set<UserNetworksDto> userNetworksDtoSet = new HashSet<>();
            userNetworksEntitySet.forEach(networks -> {
                        userNetworksDtoSet.add(UserNetworksService.convertEntityToDto(networks));
                    }
            );
            dto.setNetworks(userNetworksDtoSet);
        }
        return dto;
    }

    public UserDto update(UserDto dto) {
        Optional<User> optional = userRepository.findByUsernameOrEmail(dto.getUsername(), dto.getEmail());
        if (optional.isPresent()) {
            throw new ServerBadRequestException("User with this email or username already exist");
        }
        User user = convertDtoToEntity(dto);
        user.setId(dto.getId());
        if (dto.getAddress() != null) {
            Address address = AddressService.convertDtoToEntity(dto.getAddress());
            addressRepository.save(address);
            user.setAddress(address);
        } else if (dto.getAddressId() != null) {
            Address address = addressService.getAddressEntity(dto.getAddressId());
            user.setAddress(address);
        }
        if (dto.getRoles() != null) {
            user.setRoles(dto.getRoles());
        } else if (dto.getRolesId() != null) {
            Set<Short> rolesId = dto.getRolesId();
            Set<Role> roleSet = new HashSet<>();
            rolesId.forEach(roleId -> {
                if (roleRepository.existsById(roleId)) {
                    roleSet.add(roleRepository.getById(roleId));
                }
            });
            user.setRoles(roleSet);
        }
        if (dto.getNetworks() != null) {
            Set<UserNetworksDto> userNetworksDtoSet = dto.getNetworks();
            Set<UserNetworks> userNetworksEntitySet = new HashSet<>();
            userNetworksDtoSet.forEach(userDto -> {
                userNetworksEntitySet.add(UserNetworksService.convertDtoToEntity(userDto));
            });
            userNetworksRepository.saveAll(userNetworksEntitySet);
            user.setNetworks(userNetworksEntitySet);
        } else if (dto.getNetworksId() != null) {
            Set<Integer> userNetworksIdSet = dto.getNetworksId();
            Set<UserNetworks> userNetworksEntitySet = new HashSet<>();
            userNetworksIdSet.forEach(userId -> {
                userNetworksEntitySet.add(userNetworksService.getEntity(userId));
            });
            user.setNetworks(userNetworksEntitySet);
        }
        userRepository.save(user);
        return dto;
    }


    public void create(UserDto dto) {
//        checking email and username
        Optional<User> optional = userRepository.findByUsernameOrEmail(dto.getUsername(), dto.getEmail());
        if (optional.isPresent()) {
            throw new ServerBadRequestException("User with this email or username already exist");
        }
        User user = convertDtoToEntity(dto);
        if (dto.getAddress() != null) {
            Address address = AddressService.convertDtoToEntity(dto.getAddress());
            addressRepository.save(address);
            user.setAddress(address);
        } else if (dto.getAddressId() != null) {
            Address address = addressService.getAddressEntity(dto.getAddressId());
            user.setAddress(address);
        }
        if (dto.getRoles() != null) {
            user.setRoles(dto.getRoles());
        }
        if (dto.getNetworks() != null) {
            Set<UserNetworksDto> userNetworksDtoSet = dto.getNetworks();
            Set<UserNetworks> userNetworksEntitySet = new HashSet<>();
            userNetworksDtoSet.forEach(userDto -> {
                userNetworksEntitySet.add(UserNetworksService.convertDtoToEntity(userDto));
            });
            userNetworksRepository.saveAll(userNetworksEntitySet);
            user.setNetworks(userNetworksEntitySet);
        } else if (dto.getNetworksId() != null) {
            Set<Integer> userNetworksIdSet = dto.getNetworksId();
            Set<UserNetworks> userNetworksEntitySet = new HashSet<>();
            userNetworksIdSet.forEach(userId -> {
                userNetworksEntitySet.add(userNetworksService.getEntity(userId));
            });
            user.setNetworks(userNetworksEntitySet);
        }
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
    }


    public void delete(Long id) {
        User user = getUserEntity(id);
        userRepository.delete(user);
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

    public static UserDto convertEntityToDto(User entity) {
        return UserDto.builder()
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .active(entity.isActive())
                .build();
    }

    public static User convertDtoToEntity(UserDto dto) {
        return User.builder()
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .phone(dto.getPhone())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .active(dto.isActive())
                .build();
    }
}

