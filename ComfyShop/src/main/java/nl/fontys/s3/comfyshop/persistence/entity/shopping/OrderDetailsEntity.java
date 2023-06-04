package nl.fontys.s3.comfyshop.persistence.entity.shopping;

import lombok.*;
import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_details")
@ToString(exclude = {"orderItems"})
public class OrderDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "orderDetails", cascade = CascadeType.ALL)
    private List<OrderItemsEntity> orderItems;
}
