package br.com.jdlm.stock_service.domain.entity.stock_movement;

import br.com.jdlm.stock_service.domain.entity.movement_type.MovementType;
import br.com.jdlm.stock_service.domain.entity.stock.Stock;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stocks_movements")
public class StockMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "movement_type")
    private MovementType movementType;

    @Column(name = "movement_quantity")
    private Integer movementQuantity;

    @ManyToOne
    @JsonIgnoreProperties("stockMovements")
    private Stock stockId;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
