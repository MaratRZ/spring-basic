package ru.gb.cart;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.gb.product.Product;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Scope("prototype")
public class Cart {

    private static final AtomicLong cartSeq = new AtomicLong(0);

    private final long id;
    private Map<Product, Integer> products;

    public Cart() {
        this.id = cartSeq.incrementAndGet();
        products = new TreeMap<>(Comparator.comparingLong(Product::getId));
    }

    public int getProductCount(Product product) {
        if (!products.containsKey(product)) {
            return 0;
        }
        return products.get(product);
    }

    public void addProduct(Product product) {
        products.merge(product, 1, Integer::sum);
    }

    public void deleteProduct(Product product) {
        if (getProductCount(product) > 1) {
            products.put(product, getProductCount(product) - 1);
        } else if (getProductCount(product) == 1) {
            products.remove(product);
        }
    }

    public long getId() {
        return id;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public BigDecimal getTotalCost() {
        return products.entrySet().stream()
                .map(p -> p.getKey().getPrice().multiply(new BigDecimal(p.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
