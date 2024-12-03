package br.com.jdlm.order.custumer;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
