package com.devdeolho.domain;

import jakarta.persistence.*;

@Entity(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Review() {
    }

    public Review(Integer id, String userName, Integer rating, Product product) {
        this.id = id;
        this.userName = userName;
        this.rating = rating;
        this.product = product;
    }

    private Review(Builder builder) {
        this.id = builder.id;
        this.userName = builder.userName;
        this.rating = builder.rating;
        this.product = builder.product;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private String userName;
        private Integer rating;
        private Product product;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder rating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public Builder product(Product product) {
            this.product = product;
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
