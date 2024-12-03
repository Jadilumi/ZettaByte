package br.com.jdlm.stock_service.domain.entity.stock;

import java.util.UUID;

public record StockDTO(
        UUID productId, Integer actualStock) {
}
