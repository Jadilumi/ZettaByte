package br.com.jdlm.product_service.domain.entity.product;

import java.math.BigDecimal;
import java.util.UUID;

public record PurchaseResponse(
        UUID productId,
        String name,
        String description,
        BigDecimal price,
        Integer quantity
) {
}