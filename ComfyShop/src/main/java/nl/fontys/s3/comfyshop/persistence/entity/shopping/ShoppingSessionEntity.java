package nl.fontys.s3.comfyshop.persistence.entity.shopping;

import lombok.*;
import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude =  "user")
@Table(name = "shopping_session")
public class ShoppingSessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Builder.Default
//    @JsonIgnore
    @OneToMany(mappedBy = "shoppingSession", cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private List<CartItemEntity> cartItems = new ArrayList<>();
    @Builder.Default
    private boolean ordered = false;
}
