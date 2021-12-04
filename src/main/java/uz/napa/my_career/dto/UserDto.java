package uz.napa.my_career.dto;

import lombok.*;
import uz.napa.my_career.entity.Role;
import uz.napa.my_career.entity.UserNetworks;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;

    private String username;

    private String email;
    private String password;

    private String firstname;
    private String lastname;

    private String phone;

    private AddressDetail address;
    private Integer addressId;

    private boolean active;

    private Set<Role> roles;
    private Set<Short> rolesId;

    private Set<UserNetworksDto> networks;
    private Set<Integer> networksId;
}
