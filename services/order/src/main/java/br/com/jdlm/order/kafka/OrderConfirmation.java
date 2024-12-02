package br.com.jdlm.order.kafka;

import br.com.jdlm.order.custumer.CustomerResponse;
import br.com.jdlm.order.order.PaymentMethod;
import br.com.jdlm.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
