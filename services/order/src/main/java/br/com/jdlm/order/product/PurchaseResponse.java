package br.com.jdlm.order.product;

import java.math.BigDecimal;

public record PurchaseResponse(
        Integer produtcId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
