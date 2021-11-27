package ru.gb.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Product {
    private long id;
    private String title;
    private int cost;
}
