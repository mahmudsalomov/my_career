package uz.napa.my_career.entity;


import javax.persistence.*;

@Entity
@Table(name = ("quarters"))
public class Quarters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne
    @JoinColumn(name = ("district_id"), insertable = false, updatable = false)
    private Districts districts;

    @Column(name = ("district_id"), insertable = false, updatable = false)
    private Integer districtId;

}
