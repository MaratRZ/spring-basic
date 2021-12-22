package ru.gb;

import ru.gb.cart.Cart;
import ru.gb.config.AppComponent;

import java.io.IOException;

import static ru.gb.config.AppComponent.cartService;

public class CartApp {

    public static void main(String[] args) throws IOException {
        Cart mainCart = AppComponent.getCart();
        cartService.addCart(mainCart, "Main");
        cartService.addCart(AppComponent.getCart(), "Additional");
        cartService.setCurrentCart(mainCart);
        cartService.showMainMenu();
    }
}
