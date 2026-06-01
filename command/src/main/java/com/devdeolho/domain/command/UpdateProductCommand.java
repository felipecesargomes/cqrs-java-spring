package com.devdeolho.domain.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public class UpdateProductCommand implements Command {
    private Integer id;
    private String imageUrl;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private BigDecimal value;

    public UpdateProductCommand() {
    }

    public UpdateProductCommand(Integer id, String imageUrl, String name, String description, BigDecimal value) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }
}
