package uz.napa.my_career.dto.resume;

import uz.napa.my_career.entity.Address;
import uz.napa.my_career.entity.Education;
import uz.napa.my_career.entity.Experience;
import uz.napa.my_career.entity.Skills;
import java.time.LocalDateTime;
import java.util.Set;

public class ResumeDeleteDto {
    private Long id;
    private Long userId;
    private LocalDateTime cratedDate;
    private String aboutMe;
    private String coverLater;
    private Set<Skills> skillsList;
    private Set<Experience> experienceList;
    private Set<Education> educationList;
}
