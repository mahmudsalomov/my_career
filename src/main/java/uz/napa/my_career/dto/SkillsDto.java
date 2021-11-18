package uz.napa.my_career.dto;

import lombok.*;
import uz.napa.my_career.entity.SkillCategory;

import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillsDto {
    private Integer id;
    private String name;
    private SkillCategory category;
}
