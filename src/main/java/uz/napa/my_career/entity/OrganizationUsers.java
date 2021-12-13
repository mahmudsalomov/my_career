package uz.napa.my_career.entity;


import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Entity(name = ("organization_users"))
@Table(name = ("organization_users"))
public class OrganizationUsers {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Column(name = ("user_id"))
    private Long user_id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = ("user_id"),insertable = false,updatable = false)
    private User user;


    @Column(name = "organization_id")
    private Long organizationId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = ("organization_id"),insertable = false, updatable = false)
    private Organization organization;





}
