package uz.napa.my_career.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.napa.my_career.entity.Experience;

public interface ExperienceRepository extends JpaRepository<Experience,Integer> {

}
