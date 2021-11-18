package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.napa.my_career.entity.SkillCategory;
import uz.napa.my_career.exception.ServerBadRequestException;
import uz.napa.my_career.repository.SkillCategoryRepository;

import java.util.Optional;

@Service
public class SkillCategoryService {
    @Autowired
    private SkillCategoryRepository skillCategoryRepository;


    //Main functions
    public SkillCategory create(SkillCategory category) {
        skillCategoryRepository.save(category);
        return category;
    }

    public SkillCategory get(Short id) {
        return getEntity(id);
    }

    public SkillCategory update(SkillCategory category) {
        skillCategoryRepository.save(category);
        return category;
    }

    public void delete(Short id) {
        SkillCategory category = getEntity(id);
        skillCategoryRepository.delete(category);
    }

    //Secondary functions

    public SkillCategory getEntity(Short id) {
        Optional<SkillCategory> optional = skillCategoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ServerBadRequestException("Skill category with this id not found");
        }
        return optional.get();
    }
}
