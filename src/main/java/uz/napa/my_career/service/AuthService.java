package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.RegistrationDto;
import uz.napa.my_career.entity.Role;
import uz.napa.my_career.entity.enums.RoleName;
import uz.napa.my_career.entity.User;

import uz.napa.my_career.exception.ServerBadRequestException;
import uz.napa.my_career.payload.ResToken;
import uz.napa.my_career.payload.SignIn;
import uz.napa.my_career.repository.RoleRepository;
import uz.napa.my_career.repository.UserRepository;
import uz.napa.my_career.secret.JwtTokenProvider;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;


@Service
public class AuthService implements UserDetailsService {

    UserRepository userRepository;
    JwtTokenProvider jwtTokenProvider;
    AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public AuthService(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    public User loadByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user not found"));

    }

    public ResToken signIn(SignIn signIn) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signIn.getUsername(), signIn.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User principal = (User) authentication.getPrincipal();
            String jwt = jwtTokenProvider.generateToken(principal);
            return new ResToken(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void registration(RegistrationDto dto) {
        Optional<User> optional = userRepository.findByUsernameOrEmail(dto.getUsername(), dto.getEmail());
        if (optional.isPresent()) {
            throw new ServerBadRequestException("Profile with these username or email exist");
        }
        Role byRole = roleRepository.getByRole(RoleName.USER);
        System.out.println(byRole);
        User user = userRepository.save(User
                .builder()
                .active(false)
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .username(dto.getUsername())
                .roles(new HashSet<>(
                        Collections.singleton(roleRepository.findById((short) 2).orElseThrow())
                ))
                .build());
        userRepository.save(user);
    }

    public void validation(String jwt) {
        String userId = jwtTokenProvider.getUserIdFromToken(jwt);
        Optional<User> optional = userRepository.findById(Long.valueOf(userId));
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("User not found");
        }
        User user = optional.get();
        user.setActive(true);
        userRepository.save(user);

    }

//    public ApiResponse searchUser(String search) {
//        return new ApiResponseObject("Ok", true, userRepository.byUsername(search));
//    }

//    public ApiResponse all() {
//        return new ApiResponse("Ok", true, userRepository.findAll().stream().map(item -> dtoService.userDto(item)).collect(Collectors.toList()));
//    }
}
