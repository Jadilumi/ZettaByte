package br.com.jdlm.order.product;

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
