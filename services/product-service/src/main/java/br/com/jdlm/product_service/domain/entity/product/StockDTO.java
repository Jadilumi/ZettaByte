package br.com.jdlm.product_service.domain.entity.product;

import java.util.UUID;

public record StockDTO(UUID productId, Integer actualStock) {
}
