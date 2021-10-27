package uz.napa.my_career.dto.resume;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import uz.napa.my_career.entity.Education;
import uz.napa.my_career.entity.Experience;
import uz.napa.my_career.entity.Skills;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ResumeCreateDto {
    private  Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String phoneNumber;
    @Email
    private String email;
    @NotBlank
    private String address;
    @NotNull
    private List<Skills> skillsList;
    @NotNull
    private List<Education> educations;
    @NotNull
    private List<Experience> experienceList;



}
