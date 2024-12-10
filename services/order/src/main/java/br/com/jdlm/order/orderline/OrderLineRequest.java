package br.com.jdlm.order.orderline;

import java.util.UUID;

public record OrderLineRequest(
        Integer id,
        Integer orderId,
        UUID productId,
        double quantity ) {
}
