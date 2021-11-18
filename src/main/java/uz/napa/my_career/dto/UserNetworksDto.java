package uz.napa.my_career.dto;

import lombok.*;
import uz.napa.my_career.entity.enums.NetworksType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserNetworksDto {
    private Integer id;
    private NetworksType type;
    private String link;
}
