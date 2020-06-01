package ru.itis.lazyMarket.dto;

import lombok.Getter;
import lombok.Setter;
import ru.itis.lazyMarket.models.Product;

import java.util.List;

@Getter
@Setter
public class ProdList {
    private List<String> productNames;
    private Integer userId;
    private Integer supermarketId;
}
