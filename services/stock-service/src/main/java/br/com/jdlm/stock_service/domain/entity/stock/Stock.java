package br.com.jdlm.stock_service.domain.entity.stock;

import br.com.jdlm.stock_service.domain.entity.stock_movement.StockMovement;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "product_id", unique = true, nullable = false)
    private UUID productId;

    private Integer actualStock;

    @OneToMany(mappedBy = "stockId")
    @JsonIgnoreProperties("stockId")
    private List<StockMovement> stockMovements;

    public void incraseStock(Integer stock) {
        this.actualStock += stock;
    }

    public void decreaseStock(Integer stock) {
        this.actualStock -= stock;
    }
}
