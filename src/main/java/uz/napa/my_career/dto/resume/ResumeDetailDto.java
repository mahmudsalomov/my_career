package uz.napa.my_career.dto.resume;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import uz.napa.my_career.entity.Address;
import uz.napa.my_career.entity.Education;
import uz.napa.my_career.entity.Experience;
import uz.napa.my_career.entity.Skills;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResumeDetailDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Address address;
    private LocalDateTime cratedDate;
    private String aboutMe;
    private String coverLater;
    private Set<Skills> skillsList;
    private Set<Experience> experienceList;
    private Set<Education> educationList;
}
