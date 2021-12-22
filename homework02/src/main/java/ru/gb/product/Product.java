package ru.gb.product;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

@Getter
public class Product {
    private static final AtomicLong count = new AtomicLong(0);

    private long id;
    private String name;
    private BigDecimal price;

    public Product(String title, BigDecimal price) {
        this.id = count.incrementAndGet();
        this.name = title;
        this.price = price;
    }
}
