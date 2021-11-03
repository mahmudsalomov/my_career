package uz.napa.my_career.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.napa.my_career.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization,Integer> {
}
