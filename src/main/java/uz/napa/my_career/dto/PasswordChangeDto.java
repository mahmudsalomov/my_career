package uz.napa.my_career.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeDto {
    private String oldPassword;
    private String firstNewPassword;
    private String secondNewPassword;
}
