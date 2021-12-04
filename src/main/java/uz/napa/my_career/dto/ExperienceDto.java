package uz.napa.my_career.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExperienceDto {
    private Integer id;
    @NotBlank(message = "Invalid job name")
    private String jobName;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private OrganizationDto organization;
    private Integer organizationId;

}
