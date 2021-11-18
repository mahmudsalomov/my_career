package uz.napa.my_career.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizationDto {
    private Integer id;
    private String name;
    private AddressDetail address;
    private Integer addressId;
}
