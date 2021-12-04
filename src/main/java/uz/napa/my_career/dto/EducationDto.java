package uz.napa.my_career.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationDto {
    private Integer id;
    @NotBlank(message = "Invalid school name")
    private String schoolName;
    @NotEmpty(message = "Empty diploma code")
    private String diplomaCode;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private OrganizationDto organization;
    private Integer organizationId;
}
