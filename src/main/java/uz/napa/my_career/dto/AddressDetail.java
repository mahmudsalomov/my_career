package uz.napa.my_career.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDetail {
    private Integer id;

    @NotBlank
    private String country;

    @NotEmpty
    private String region;

    @NotBlank
    private String city;

    @NotEmpty
    private String district;

    @NotBlank
    private String street;


    @NotBlank
    private String homeNum;

}
