package br.com.jdlm.product_service.controller;

import br.com.jdlm.product_service.domain.entity.review.Review;
import br.com.jdlm.product_service.domain.entity.review.ReviewDTO;
import br.com.jdlm.product_service.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/save")
    public ResponseEntity<Review> saveReview(@RequestBody ReviewDTO receivedReview) {
        return new ResponseEntity<>(reviewService.createReview(receivedReview), HttpStatus.CREATED);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable UUID id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>("Review deletado com sucesso", HttpStatus.GONE);
    }
}
