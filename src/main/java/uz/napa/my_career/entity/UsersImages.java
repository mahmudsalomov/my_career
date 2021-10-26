package uz.napa.my_career.entity;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = ("users_images"))
public class UsersImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = ("file_storage_id"))
    private Long fileStorageId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ("userId"),insertable = false, updatable = false)
    private FileStorage fileStorage;

    @Column(name = ("user_id"))
    private Long userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ("fileStorageId"),insertable = false, updatable = false)
    private User user;






}
