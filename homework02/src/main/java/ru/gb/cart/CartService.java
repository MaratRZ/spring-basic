package ru.gb.cart;

import ru.gb.config.AppComponent;
import ru.gb.product.Product;
import ru.gb.product.ProductRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CartService {

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_YELLOW = "\u001B[33m";
    private final String ANSI_BLUE = "\u001B[34m";
    private final String ANSI_PURPLE = "\u001B[35m";
    private final String ANSI_CYAN = "\u001B[36m";

    private static BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

    private Map<Cart, String> carts = new TreeMap<>(Comparator.comparingLong(c -> c.getId()));
    private Cart currentCart;
    private ProductRepository productRepository;

    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Cart findCart(long cartId) {
        Cart cart = null;
        Iterator i = carts.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry entry = (Map.Entry) i.next();
            cart = (Cart) entry.getKey();
            if (cart.getId() == cartId) {
                return cart;
            }
        }
        return cart;
    }

    public void addCart(Cart cart, String cartName) {
        carts.put(cart, cartName);
        this.currentCart = cart;
    }

    public void deleteCart(Cart cart) {
        if (carts.isEmpty()) {
            return;
        } else if (cart.getId() == 1) {
            printError("It is forbidden to delete the main cart");
            printLine("");
        } else {
            carts.remove(cart);
        }
    }

    public void setCurrentCart(Cart cart) {
        this.currentCart = cart;
    }

    private void print(String s) {
        System.out.print(s);
    }

    private void printLine(String s) {
        System.out.println(s);
    }

    private void printError(String s) {
        System.out.println(ANSI_RED + s + ANSI_RESET);
    }

    private void printDoubleSeparator() {
        printLine("-==========================-");
    }

    private void printSingleSeparator() {
        printLine("----------------------------");
    }

    private void printHeader(String s) {
        printLine(ANSI_PURPLE + s + ANSI_RESET);
    }

    private void printCurrentCart() {
        printLine(ANSI_CYAN + "Current cart: " + ANSI_RESET + currentCart.getId() + ". " + carts.get(currentCart));
    }

    private void printExitToMainMenu() {
        printLine(ANSI_GREEN + "0. Exit to Main menu" + ANSI_RESET);
        printLine("");
    }

    private boolean isInteger(String s) {
        return s.matches("[-+]?\\d+");
    }

    public void showMainMenu() throws IOException {
        String selectedItem;
        do {
            printHeader("[ MAIN MENU ]");
            printCurrentCart();
            printDoubleSeparator();
            printLine("1. Select cart");
            printLine("2. New cart");
            printLine("3. Delete cart");
            printLine("4. Show products in current cart");
            printLine("5. Add product to current cart");
            printLine("6. Delete product from current cart");
            printSingleSeparator();
            printLine(ANSI_GREEN + "0. Application exit" + ANSI_RESET);
            printLine("");

            print("Select menu item: ");
            selectedItem = consoleReader.readLine();
            switch (selectedItem) {
                case "0":
                    printLine("0");
                    break;
                case "1":
                    showSelectCartMenu();
                    break;
                case "2":
                    createNewCart();
                    break;
                case "3":
                    deleteCart();
                    break;
                case "4":
                    showProductsInCurrentCart();
                    break;
                case "5":
                    addProductInCurrentCart();
                    break;
                case "6":
                    deleteProductFromCurrentCart();
                    break;
                default:
                    printError("Incorrect menu item. Please try again..");
                    printLine("");
                    break;
            }
        } while (!"0".equals(selectedItem));
    }

    private void showSelectCartMenu() throws IOException {
        String selectedItem;
        do {
            printLine("");
            printHeader("{ SELECT A CART TO MAKE IT CURRENT }");
            printCurrentCart();
            printDoubleSeparator();
            carts.forEach((c, n) -> System.out.printf("%d. %s\n", c.getId(), n));
            printSingleSeparator();
            printExitToMainMenu();
            print("Select menu item: ");

            selectedItem = consoleReader.readLine();
            if (!isInteger(selectedItem)) {
                printError("Incorrect menu item. Please try again..");
                printLine("");
                continue;
            }

            if (!"0".equals(selectedItem)) {
                Cart cart = findCart(Long.parseLong(selectedItem));
                if (cart == null) {
                    printError("Incorrect menu item. Please try again..");
                    printLine("");
                    continue;
                }
                currentCart = cart;
            }
        } while (!"0".equals(selectedItem));
        printLine("");
    }

    private void createNewCart() throws IOException {
        print("Enter cart name: ");
        String name = consoleReader.readLine();
        Cart cart = AppComponent.getCart();
        setCurrentCart(cart);
        AppComponent.cartService.addCart(cart, name);
        printLine("");
    }

    private void deleteCart() throws IOException {
        String selectedItem;
        do {
            printLine("");
            printHeader("{ SELECT CART TO DELETE }");
            printCurrentCart();
            printDoubleSeparator();
            carts.forEach((c, n) -> System.out.printf("%d. %s\n", c.getId(), n));
            printSingleSeparator();
            printExitToMainMenu();
            print("Select menu item: ");

            selectedItem = consoleReader.readLine();
            if (!isInteger(selectedItem)) {
                printError("Incorrect menu item. Please try again..");
                printLine("");
                continue;
            }

            if (!"0".equals(selectedItem)) {
                Cart cart = findCart(Long.parseLong(selectedItem));
                if (cart == null) {
                    printError("Incorrect menu item. Please try again..");
                    printLine("");
                    continue;
                }
                deleteCart(cart);
                currentCart = findCart(1);
            }
        } while (!"0".equals(selectedItem));
        printLine("");
    }

    private void showProductsInCurrentCart() throws IOException {
        String selectedItem;
        do {
            printLine("");
            printHeader("{ PRODUCT LIST }");
            printCurrentCart();
            printDoubleSeparator();
            currentCart.getProducts().forEach((p, i) -> printLine( p.getId() + ". " + p.getName() +
                    " (price: " + ANSI_BLUE + p.getPrice() + "$ )" +
                    ANSI_RESET + " [count = " + ANSI_CYAN + i + ANSI_RESET + "]"));
            printLine(ANSI_YELLOW + "Total sum: " + ANSI_BLUE + currentCart.getTotalCost().toPlainString() + "$" + ANSI_RESET);
            printSingleSeparator();
            printExitToMainMenu();

            selectedItem = consoleReader.readLine();
        } while (!"0".equals(selectedItem));
        printLine("");
    }

    private void addProductInCurrentCart() throws IOException {
        String selectedItem;
        do {
            printLine("");
            printHeader("{ ADD PRODUCT TO CART }");
            printCurrentCart();
            printDoubleSeparator();
            productRepository.getProducts()
                    .forEach(p -> printLine(p.getId() + ". " +
                            p.getName() +
                            " (price: " + ANSI_BLUE + p.getPrice() + ANSI_RESET + ")"));
            printSingleSeparator();
            printExitToMainMenu();
            print("Select menu item: ");

            selectedItem = consoleReader.readLine();
            if (!isInteger(selectedItem)) {
                printError("Incorrect menu item. Please try again..");
                printLine("");
                continue;
            }

            if (!"0".equals(selectedItem)) {
                ProductRepository productRepository = AppComponent.getProductRepository();
                Product product = productRepository.getProduct(Integer.parseInt(selectedItem));
                if (product == null) {
                    printError("Incorrect menu item. Please try again..");
                    printLine("");
                    continue;
                }
                currentCart.addProduct(product);
            }
        } while (!"0".equals(selectedItem));
        printLine("");
    }

    private void deleteProductFromCurrentCart() throws IOException {
        String selectedItem;
        do {
            printLine("");
            printHeader("{ DELETE PRODUCT FROM CART }");
            printCurrentCart();
            printDoubleSeparator();
            currentCart.getProducts().forEach((p, i) -> printLine( p.getId() + ". " + p.getName() +
                    " (price: " + ANSI_BLUE + p.getPrice() + "$ )" +
                    ANSI_RESET + " [count = " + ANSI_CYAN + i + ANSI_RESET + "]"));
            printLine(ANSI_YELLOW + "Total sum: " + ANSI_BLUE + currentCart.getTotalCost().toPlainString() + "$" + ANSI_RESET);
            printSingleSeparator();
            printExitToMainMenu();
            print("Select menu item: ");

            selectedItem = consoleReader.readLine();
            if (!isInteger(selectedItem)) {
                printError("Incorrect menu item. Please try again..");
                printLine("");
                continue;
            }

            if (!"0".equals(selectedItem)) {
                ProductRepository productRepository = AppComponent.getProductRepository();
                Product product = productRepository.getProduct(Integer.parseInt(selectedItem));
                if (product == null) {
                    printError("Incorrect menu item. Please try again..");
                    printLine("");
                    continue;
                }
                currentCart.deleteProduct(product);
            }
        } while (!"0".equals(selectedItem));
        printLine("");
    }
}
