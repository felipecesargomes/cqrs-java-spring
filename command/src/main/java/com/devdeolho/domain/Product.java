package com.devdeolho.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    private String imageUrl;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)

    private BigDecimal value;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    public Product() {
    }

    public Product(String imageUrl, String name, String description, BigDecimal value, List<Review> reviews) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.description = description;
        this.value = value;
        this.reviews = reviews;
    }

    private Product(Builder builder) {
        this.id = builder.id;
        this.imageUrl = builder.imageUrl;
        this.name = builder.name;
        this.description = builder.description;
        this.value = builder.value;
        this.reviews = builder.reviews;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return builder()
                .id(this.id)
                .imageUrl(this.imageUrl)
                .name(this.name)
                .description(this.description)
                .value(this.value)
                .reviews(new ArrayList<>(this.reviews));
    }

    public static class Builder {
        private Integer id;
        private String imageUrl;
        private String name;
        private String description;
        private BigDecimal value;
        private List<Review> reviews = new ArrayList<>();

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder value(BigDecimal value) {
            this.value = value;
            return this;
        }

        public Builder reviews(List<Review> reviews) {
            this.reviews = reviews;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
