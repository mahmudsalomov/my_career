package uz.napa.my_career.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.napa.my_career.entity.Address;
import uz.napa.my_career.entity.Role;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class    UserDetail {
    private Long id;

    private String username;

    private String email;
    private String password;

    private String firstname;
    private String lastname;

    private String phone;

    private AddressDetail address;

    private boolean active;

    private Set<Role> roles;
}
