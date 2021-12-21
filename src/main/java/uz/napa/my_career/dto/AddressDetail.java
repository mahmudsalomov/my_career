package uz.napa.my_career.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDetail {
    private Integer id;

    @NotEmpty
    private String country;

    @NotNull
    private String region_id;

    @NotNull
    private String district_id;

    @NotNull
    private String quarters;

    @NotBlank
    private String street;

    @NotBlank
    private String homeNum;

}
