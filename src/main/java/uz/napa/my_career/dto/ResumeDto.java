package uz.napa.my_career.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.napa.my_career.entity.Address;
import uz.napa.my_career.entity.Education;
import uz.napa.my_career.entity.Experience;
import uz.napa.my_career.entity.Skills;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ResumeDto {
    private Long id;

    private Long userId;
    private UserDto userDto;

    private LocalDateTime createdDate;

    private String aboutMe;

    private String coverLetter;

    private Set<SkillsDto> skillsList;

    private Set<ExperienceDto> experienceList;

    private Set<EducationDto> educationList;
}
