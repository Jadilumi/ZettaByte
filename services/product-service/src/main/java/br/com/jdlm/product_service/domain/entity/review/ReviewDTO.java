package br.com.jdlm.product_service.domain.entity.review;

import br.com.jdlm.product_service.domain.entity.product.Product;

import java.util.UUID;

public record ReviewDTO(Integer rating, String description, Product product, UUID clientId) {
}
