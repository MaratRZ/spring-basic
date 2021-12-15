package ru.gb.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.cart.Cart;
import ru.gb.cart.CartService;
import ru.gb.product.ProductRepository;

public class AppComponent {
    public static ApplicationContext context;
    public static CartService cartService;
    static {
        context = new AnnotationConfigApplicationContext(ru.gb.config.AppConfig.class);
        cartService = new CartService(AppComponent.getProductRepository());
    }

    public static ProductRepository getProductRepository() {
        return (ProductRepository) context.getBean("productRepository");
    }

    public static Cart getCart() {
        return (Cart) context.getBean("cart");
    }
}
