package br.com.jdlm.product_service.domain.entity.product;

import br.com.jdlm.product_service.domain.entity.category.Category;
import br.com.jdlm.product_service.domain.entity.review.Review;

import java.math.BigDecimal;
import java.util.List;

public record ProductDTO(
        String name,
        String description,
        BigDecimal price,
        Category category,
        Integer actualStock,
        // List<String> images,
        List<Review> reviews,
        Boolean isActive) {
}
