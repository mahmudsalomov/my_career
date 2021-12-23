package uz.napa.my_career.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = ("districts"))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Districts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne
    @JoinColumn(name = ("region_id"), insertable = false, updatable = false)
    private Regions region;

    @Column(name = ("region_id"), insertable = false, updatable = false)
    private Integer regionId;

}
