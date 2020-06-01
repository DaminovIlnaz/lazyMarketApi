package ru.itis.lazyMarket.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Basket {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(
            mappedBy = "basket"
    )
    private List<Product> products;
    @ManyToOne
    @JoinColumn(
            name = "supermarket_id"
    )
    private Supermarket supermarket;
    @ManyToOne
    @JoinColumn(
            name = "buyer_id"
    )
    private Buyer buyer;

}

