package uz.napa.my_career.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String schoolName;
//    private String direction;
    private String diplomaCode;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @OneToOne
    private Organization organization;
}
