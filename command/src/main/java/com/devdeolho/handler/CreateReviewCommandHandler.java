package com.devdeolho.handler;

import com.devdeolho.domain.Review;
import com.devdeolho.domain.command.CreateReviewCommand;
import com.devdeolho.domain.enums.Event;
import com.devdeolho.domain.message.Message;
import com.devdeolho.repository.ProductRepository;
import com.devdeolho.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateReviewCommandHandler implements CommandHandler<CreateReviewCommand> {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, Message<?>> kafkaTemplate;

    @Override
    @Transactional
    public void handle(CreateReviewCommand command) {
        final var product = productRepository.findById(command.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found: " + command.getProductId()));

        final var review = Review.builder()
                .userName(command.getUserName())
                .rating(command.getRating())
                .product(product)
                .build();

        final var savedReview = reviewRepository.save(review);
        kafkaTemplate.send("review-created", new Message<>(Event.REVIEW_CREATED, savedReview));
    }
}
