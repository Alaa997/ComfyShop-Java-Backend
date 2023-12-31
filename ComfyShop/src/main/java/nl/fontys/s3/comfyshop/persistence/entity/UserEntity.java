package nl.fontys.s3.comfyshop.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.ShoppingSessionEntity;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "shoppingSession")
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
    @JsonIgnore

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    private ShoppingSessionEntity shoppingSession;
}
