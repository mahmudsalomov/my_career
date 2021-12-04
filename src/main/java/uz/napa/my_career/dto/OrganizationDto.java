package uz.napa.my_career.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizationDto {
    private Integer id;
    @NotBlank(message = "Invalid organization name")
    private String name;

    private AddressDetail address;
    private Integer addressId;
}
