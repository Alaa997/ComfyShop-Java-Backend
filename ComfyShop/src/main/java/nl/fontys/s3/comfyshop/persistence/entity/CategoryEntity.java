package nl.fontys.s3.comfyshop.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;
//import javax.validation.constraints.NotBlank;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;
//    @NotBlank
    @Length(min = 3 ,max = 20)
    @Column(name = "name")
    public String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    public List<ProductEntity> products;
}
