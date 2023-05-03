package nl.fontys.s3.comfyshop.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;
    @NotBlank
    @Length(min = 3 ,max = 20)
    @Column(name = "name")
    public String name;
    @NotBlank
    @Column(name = "description")
    public String description;
    @NotNull
    public Double price;
//    @NotBlank
    @ManyToOne
    @JoinColumn(name = "category_id")
    public CategoryEntity category;
}
