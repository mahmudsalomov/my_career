package uz.napa.my_career.entity;

import lombok.*;
import uz.napa.my_career.entity.enums.NetworksType;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = ("user_networks"))
public class UserNetworks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private NetworksType type;

    private String link;










}
