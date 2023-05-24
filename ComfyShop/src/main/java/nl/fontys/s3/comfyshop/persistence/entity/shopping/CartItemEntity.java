package nl.fontys.s3.comfyshop.persistence.entity.shopping;

import lombok.*;
import nl.fontys.s3.comfyshop.persistence.entity.ProductEntity;

import javax.persistence.*;
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart_item")
@ToString(exclude = {"shoppingSession"})
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "session_id")
    private ShoppingSessionEntity shoppingSession;

    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    private int quantity;
}

