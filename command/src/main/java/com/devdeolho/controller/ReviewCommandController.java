package com.devdeolho.controller;

import com.devdeolho.bus.CommandBus;
import com.devdeolho.domain.command.CreateReviewCommand;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewCommandController {

    @Autowired
    private CommandBus commandBus;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createReview(@RequestBody @Valid CreateReviewCommand createReviewCommand) {
        commandBus.execute(createReviewCommand);
    }

}
