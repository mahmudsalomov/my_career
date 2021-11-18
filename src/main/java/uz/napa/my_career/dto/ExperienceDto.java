package uz.napa.my_career.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExperienceDto {
    private Integer id;
    private String jobName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private OrganizationDto organization;

}
