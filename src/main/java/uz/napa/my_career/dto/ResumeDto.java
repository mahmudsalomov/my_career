package uz.napa.my_career.dto;

import uz.napa.my_career.entity.Education;
import uz.napa.my_career.entity.Experience;
import uz.napa.my_career.entity.Skills;
import uz.napa.my_career.entity.User;

import java.time.LocalDateTime;
import java.util.Set;

public class ResumeDto {
    private Long id;
    private String aboutMe;
    private User user;
    private LocalDateTime cratedDate;
    private String coverLetter;
    private Set<Skills> skills;
    private Set<Experience> experienceSet;
    private Set<Education> educationSet;

}