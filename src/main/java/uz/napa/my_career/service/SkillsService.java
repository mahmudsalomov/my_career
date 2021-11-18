package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.napa.my_career.dto.SkillsDto;
import uz.napa.my_career.entity.SkillCategory;
import uz.napa.my_career.entity.Skills;
import uz.napa.my_career.exception.ServerBadRequestException;
import uz.napa.my_career.repository.SkillCategoryRepository;
import uz.napa.my_career.repository.SkillRepository;

import java.util.Optional;

@Service
public class SkillsService {
    @Autowired
    private SkillRepository skillRepository;


    //Main functions
    public SkillsDto create(SkillsDto dto) {
        Skills skills = convertDtoToEntity(dto);
        skillRepository.save(skills);
        dto.setId(skills.getId());
        return dto;
    }

    public SkillsDto get(Integer id) {
        Skills skills = getEntity(id);
        return convertEntityToDto(skills);
    }

    public SkillsDto update(SkillsDto dto) {
        Skills skills = getEntity(dto.getId());
        skills = convertDtoToEntity(dto);
        skillRepository.save(skills);
        return dto;
    }

    public void delete(Integer id) {
        Skills skills = getEntity(id);
        skillRepository.delete(skills);
    }

    //Secondary functions

    public Skills getEntity(Integer id) {
        Optional<Skills> optional = skillRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("Skill category with this id not found");
        }
        return optional.get();
    }

    public static Skills convertDtoToEntity(SkillsDto dto) {
        return Skills.builder()
                .name(dto.getName())
                .category(SkillCategory.builder()
                        .name(dto.getCategory().getName())
                        .build())
                .build();
    }

    public static SkillsDto convertEntityToDto(Skills entity) {
        return SkillsDto.builder()
                .name(entity.getName())
                .category(SkillCategory.builder()
                        .name(entity.getCategory().getName())
                        .build())
                .build();
    }
}
