package com.dieldev.ecommerce.kafka.order;

import java.math.BigDecimal;
import java.util.List;

import br.com.jdlm.order.order.PaymentMethod;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products

) {
}
