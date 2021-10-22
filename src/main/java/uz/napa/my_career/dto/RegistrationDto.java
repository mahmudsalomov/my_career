package uz.napa.my_career.dto;

import lombok.Data;
import uz.napa.my_career.entity.Role;

@Data
public class RegistrationDto {
    private String username;
    private String password;
    private String email;
    private Role role;
}
