package ru.itis.lazyMarket.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class BasketDto {
    private String firstName;
    private String lastName;
    private String supermarketName;
}
