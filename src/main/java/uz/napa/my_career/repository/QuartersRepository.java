package uz.napa.my_career.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.napa.my_career.entity.Districts;
import uz.napa.my_career.entity.Quarters;

import java.util.List;

public interface QuartersRepository extends JpaRepository<Quarters,Integer> {

        List<Quarters> findAllByDistrictId(Integer id);

}
