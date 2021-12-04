package uz.napa.my_career.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeDto {
    @NotBlank(message = "Invalide old password")
    private String oldPassword;
    @NotBlank(message = "Invalid password")
    private String firstNewPassword;
    @NotBlank(message = "Invalid password")
    private String secondNewPassword;
}
