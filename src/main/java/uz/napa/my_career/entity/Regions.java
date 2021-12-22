package uz.napa.my_career.entity;

import javax.persistence.*;

@Entity
@Table(name = ("regions"))
public class Regions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}
