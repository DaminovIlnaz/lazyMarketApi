package ru.itis.lazyMarket.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double price;
    @ManyToOne
    @JoinColumn(
            name = "supermarket_id"
    )
    @JsonIgnore
    private Supermarket supermarket;
    @ManyToOne
    @JoinColumn(
            name = "basket_id"
    )
    @JsonIgnore
    private Basket basket;

    public Basket setBasket(Basket basket) {
        this.basket = basket;
        return basket;
    }
}