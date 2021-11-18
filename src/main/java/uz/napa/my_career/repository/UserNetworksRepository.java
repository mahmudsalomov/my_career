package uz.napa.my_career.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.napa.my_career.entity.UserNetworks;

public interface UserNetworksRepository extends JpaRepository<UserNetworks, Integer> {
}
