package br.com.jdlm.product_service.domain.entity.review;

import br.com.jdlm.product_service.domain.entity.product.Product;

public record ReviewDTO(Integer rating, String description, Product product) {
}
