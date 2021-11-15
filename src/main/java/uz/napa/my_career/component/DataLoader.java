package uz.napa.my_career.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.napa.my_career.entity.Role;
import uz.napa.my_career.entity.enums.RoleName;
import uz.napa.my_career.entity.User;
import uz.napa.my_career.repository.RoleRepository;
import uz.napa.my_career.repository.UserRepository;

import java.util.Collections;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        Role adminRole=roleRepository.save(new Role(RoleName.ADMIN));
        Role userRole=roleRepository.save(new Role(RoleName.USER));

        User admin = userRepository.save(User
                .builder()
                .active(true)
                .email("admin")
                .password(passwordEncoder.encode("admin"))
                .username("admin")
                .roles(Collections.singleton(adminRole))
                .phone("admin")
                .build());

        User user = userRepository.save(User
                .builder()
                .active(true)
                .email("user")
                .password(passwordEncoder.encode("user"))
                .username("user")
                .roles(Collections.singleton(userRole))
                .phone("user")
                .build());

    }
}
