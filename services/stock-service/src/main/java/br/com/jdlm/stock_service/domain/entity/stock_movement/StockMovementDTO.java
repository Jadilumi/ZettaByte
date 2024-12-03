package br.com.jdlm.stock_service.domain.entity.stock_movement;

import br.com.jdlm.stock_service.domain.entity.movement_type.MovementType;
import br.com.jdlm.stock_service.domain.entity.stock.Stock;

public record StockMovementDTO(
        MovementType movementType,
        Stock stock,
        Integer movementQuantity
) {
}
