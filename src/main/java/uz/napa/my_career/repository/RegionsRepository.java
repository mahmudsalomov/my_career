package uz.napa.my_career.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.napa.my_career.entity.Regions;

@Repository
public interface RegionsRepository extends JpaRepository<Regions, Integer> {

}
