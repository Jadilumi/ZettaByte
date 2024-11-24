package br.com.jdlm.order.payment;

import br.com.jdlm.order.custumer.CustomerResponse;
import br.com.jdlm.order.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer) {
}
