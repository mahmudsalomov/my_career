package uz.napa.my_career.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = ("resume"))
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private LocalDateTime createdDate;

    @Column(name = ("cover_letter"))
    private String coverLetter;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Skills> skills;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Experience> experienceSet;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Education> educationSet;
}
