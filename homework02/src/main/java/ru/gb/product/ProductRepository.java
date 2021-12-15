package ru.gb.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {

    @Value("${productList}")
    private String[] strings;

    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @PostConstruct
    public void init(){
        for (String string : strings) {
            String[] data = string.split("\\|");
            products.add(new Product(data[0], new BigDecimal(data[1])));
        }
    }
}
