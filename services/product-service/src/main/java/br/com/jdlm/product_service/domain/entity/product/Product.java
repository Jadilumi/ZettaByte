package br.com.jdlm.product_service.domain.entity.product;

import br.com.jdlm.product_service.domain.entity.category.Category;
import br.com.jdlm.product_service.domain.entity.review.Review;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer actualStock;

    @ManyToOne
    @JsonIgnoreProperties("products")
    private Category category;

    @OneToMany(mappedBy = "productId")
    @JsonIgnoreProperties("productId")
    private List<Review> reviews;

    private Boolean isActive;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public void removeProductFromStock(Integer quantity) {
        this.actualStock -= quantity;
    }
}
