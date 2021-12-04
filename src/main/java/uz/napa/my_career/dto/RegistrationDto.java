package uz.napa.my_career.dto;

import lombok.Data;
import uz.napa.my_career.entity.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegistrationDto {
    @NotBlank(message = "Invalid username")
    private String username;
    @NotBlank(message = "Invalid password")
    @Size(min = 8)
    private String password;
    @Email(message = "Ivalid email")
    private String email;

    private Role role;
}
