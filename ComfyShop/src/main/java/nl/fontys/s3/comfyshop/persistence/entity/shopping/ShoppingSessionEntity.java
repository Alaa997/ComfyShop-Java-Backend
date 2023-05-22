package nl.fontys.s3.comfyshop.persistence.entity.shopping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shopping_session")
public class ShoppingSessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Builder.Default
    @OneToMany(mappedBy = "shoppingSession", cascade = CascadeType.ALL)
    private List<CartItemEntity> cartItems = new ArrayList<>();
    @Builder.Default
    private boolean ordered = false;
}
