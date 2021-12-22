package uz.napa.my_career.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.napa.my_career.entity.Districts;

import java.util.List;

public interface DistrictsRepository extends JpaRepository<Districts,Integer> {

    List<Districts> findAllByRegionId(Integer id);


}
