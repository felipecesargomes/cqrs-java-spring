package com.devdeolho.handler;

import com.devdeolho.domain.Review;
import com.devdeolho.domain.command.CreateReviewCommand;
import com.devdeolho.repository.ProductRepository;
import com.devdeolho.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateReviewCommandHandler implements CommandHandler<CreateReviewCommand> {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void handle(CreateReviewCommand command) {
        final var product = productRepository.findById(command.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found: " + command.getProductId()));

        final var review = Review.builder()
                .userName(command.getUserName())
                .rating(command.getRating())
                .product(product)
                .build();

        reviewRepository.save(review);
    }
}
