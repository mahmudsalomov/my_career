package uz.napa.my_career.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import uz.napa.my_career.entity.enums.RoleName;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column
    @Enumerated(EnumType.STRING) //
    private RoleName role;

    public Role(RoleName role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.name();
    }
}
