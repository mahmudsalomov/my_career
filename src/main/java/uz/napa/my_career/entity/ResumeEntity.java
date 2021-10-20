package uz.napa.my_career.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = ("resume"))

public class ResumeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = ("firstName"))
    private String firstName;
    @Column(name = ("lastName"))
    private String lastName;
    @Column(name = ("address"))
    private String address;
    @Column(name = ("phoneNumber"))
    private String phoneNumber;
    @Column(name = ("email"))
    private String email;
    @Column(name = ("cratedDate"))
    private LocalDateTime cratedDate;



}
