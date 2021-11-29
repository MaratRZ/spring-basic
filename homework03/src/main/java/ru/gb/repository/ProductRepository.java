package ru.gb.repository;

import org.springframework.stereotype.Component;
import ru.gb.entity.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class ProductRepository {

    List<Product> products = new ArrayList<>();

    public Product save(Product product) {
        products.add(product);
        product.setId(products.size()-1);
        return Product.builder()
                .id(product.getId())
                .title(product.getTitle())
                .cost(product.getCost())
                .build();
    }

    public Product edit(Product product) {
        return products.set(product.getId(), product);
    }

    public Optional<Product> findById(int id) {
        if (id < products.size()) {
            return Optional.ofNullable(products.get(id));
        } else {
            return Optional.empty();
        }
    }

    public Iterable<Product> findAll() {
        return new ArrayList<>(products);
    }

    public long count() {
        return products.size();
    }

    public void deleteById(int id) {
        if (id < products.size()) {
            products.remove(id);
        }
    }

    public Product getRandomProduct() {
        if (products.size() > 0) {
            Random random = new Random();
            return products.get(random.nextInt(products.size() - 1));
        } else {
            return null;
        }
    }
}
