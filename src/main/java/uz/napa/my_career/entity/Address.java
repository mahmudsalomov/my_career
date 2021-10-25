package uz.napa.my_career.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "address")
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String country;

    private String region;

    private String city;

    private String district;

    private String street;

    @Column(name = ("home_number"))
    private String homeNum;

}

