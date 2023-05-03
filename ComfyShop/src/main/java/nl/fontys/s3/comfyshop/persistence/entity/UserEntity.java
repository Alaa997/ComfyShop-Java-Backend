package nl.fontys.s3.comfyshop.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;
    public String email;
    public String password;
    public String firstName;
    public String lastName;
    public String address;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

}
