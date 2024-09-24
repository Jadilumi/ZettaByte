package br.com.jdlm.product_service.domain.repository;

import br.com.jdlm.product_service.domain.entity.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
