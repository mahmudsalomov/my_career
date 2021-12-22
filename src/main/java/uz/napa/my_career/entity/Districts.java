package uz.napa.my_career.entity;


import javax.persistence.*;

@Entity
@Table(name = ("districts"))
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
