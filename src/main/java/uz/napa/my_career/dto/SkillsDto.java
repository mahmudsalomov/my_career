package uz.napa.my_career.dto;

import lombok.*;
import uz.napa.my_career.entity.SkillCategory;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillsDto {
    private Integer id;
    @NotBlank
    private String name;

    private SkillCategory category;
    private Short categoryId;
}
