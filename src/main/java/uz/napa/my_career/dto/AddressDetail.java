package uz.napa.my_career.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDetail {
    private Integer id;

    private String country;

    private String region;

    private String city;

    private String district;

    private String street;

    private String homeNum;
}
