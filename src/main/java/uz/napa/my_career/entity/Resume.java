package uz.napa.my_career.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = ("resume"))
@AllArgsConstructor
@NoArgsConstructor
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = ("firstName"))
    private String firstName;
    @Column(name = ("lastNAme"))
    private String lastName;
    @Column
    private String address;
    @Column(name = ("phoneNumber"))
    private String phoneNumber;
    @Column
    private String email;
    @Column(name = ("cratedDate"))
    private LocalDateTime cratedDate;
    @Column(columnDefinition = "text")
    private String bio;
    @Column(columnDefinition = "text")
    private String interests;
    @ManyToOne
    private User user;
    @OneToMany
    private List<Skills> skills;
    @OneToMany
    private List<Education> educations;
    @OneToMany
    private List<Experience> experiences;

}
