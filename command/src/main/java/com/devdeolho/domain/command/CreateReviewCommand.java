package com.devdeolho.domain.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateReviewCommand implements Command {
    @NotBlank
    private String userName;
    @NotNull
    private Integer rating;
    @NotNull
    private Integer productId;

    public CreateReviewCommand() {
    }

    public CreateReviewCommand(String userName, Integer rating, Integer productId) {
        this.userName = userName;
        this.rating = rating;
        this.productId = productId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
