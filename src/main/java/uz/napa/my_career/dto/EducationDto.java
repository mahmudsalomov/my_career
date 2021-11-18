package uz.napa.my_career.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationDto {
    private Integer id;
    private String schoolName;
    private String diplomaCode;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private OrganizationDto organization;
}
