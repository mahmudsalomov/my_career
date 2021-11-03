package uz.napa.my_career.dto.resume;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import uz.napa.my_career.entity.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ResumeCreateDto {
    private  Long id;
    @NotBlank
    private Long userId;
    @NotBlank
    private String aboutMe;
    @NotBlank
    private String coverLater;
    @NotNull
    private Set<Skills> skillsList;
    @NotNull
    private Set<Education> educations;
    @NotNull
    private Set<Experience> experienceList;
}
