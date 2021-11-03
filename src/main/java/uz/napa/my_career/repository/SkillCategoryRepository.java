package uz.napa.my_career.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.napa.my_career.entity.SkillCategory;

public interface SkillCategoryRepository extends JpaRepository<SkillCategory,Short> {
}
