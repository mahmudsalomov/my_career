package uz.napa.my_career.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

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

    @Column(columnDefinition = "text")
    private String aboutMe;

//    @Column(columnDefinition = "text")
//    private String interests;

    @ManyToOne
    private User user;

    @Column(name = ("carted_date"))
    private LocalDateTime cratedDate;

    @Column(name = ("cover_letter"))
    private String coverLetter;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Skills> skills;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Experience> experienceSet;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Education> educationSet;
}
