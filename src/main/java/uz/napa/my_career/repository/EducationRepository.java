package uz.napa.my_career.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.napa.my_career.entity.Education;

public interface EducationRepository extends JpaRepository<Education,Integer> {

}
