package br.com.jdlm.product_service.service;

import br.com.jdlm.product_service.domain.entity.product.Product;
import br.com.jdlm.product_service.domain.entity.review.Review;
import br.com.jdlm.product_service.domain.entity.review.ReviewDTO;
import br.com.jdlm.product_service.domain.repository.ReviewRepository;
import br.com.jdlm.product_service.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductService productService;

    public Review createReview(ReviewDTO receivedReview) {
        Review createdReview = new Review();

        Product relatedProduct = productService.getProductById(receivedReview.product().getId());

        BeanUtils.copyProperties(receivedReview, createdReview);

        createdReview.setProductId(relatedProduct);

        return reviewRepository.save(createdReview);
    }

    public void deleteReview(UUID id) {
        reviewRepository.findById(id).orElseThrow(() -> new CustomException("Review Not Found", HttpStatus.NOT_FOUND));
        reviewRepository.deleteById(id);
    }

}
