package uz.napa.my_career.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.napa.my_career.entity.Role;
import uz.napa.my_career.entity.RoleName;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Short> {
    Role getByRole(RoleName role);
}
