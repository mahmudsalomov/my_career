package uz.napa.my_career.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.napa.my_career.entity.Skills;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skills,Integer> {

//    List<Skills> findAllBy

}
