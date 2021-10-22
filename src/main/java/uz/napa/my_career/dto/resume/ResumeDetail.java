package uz.napa.my_career.dto.resume;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import uz.napa.my_career.entity.Experience;
import uz.napa.my_career.entity.Skills;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResumeDetail {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private List<Skills> skillsList;
    private List<Experience> experienceList;
}
