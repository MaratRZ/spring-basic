package ru.gb.servlet;

import ru.gb.product.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        StringBuilder sb = new StringBuilder();
        sb.append("<table border=\"2\" style=\"border-collapse: collapse; width: 100%;\">\n" +
                "<tr>\n" +
                "<th bgcolor=\"#ffcc00\"style=\"text-align: center;\" colspan=\"3\">Products</th>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<th bgcolor=\"#ffcc00\"style=\"text-align: center;\">id</th>\n" +
                "<th bgcolor=\"#ffcc00\"style=\"text-align: center;\">title</th>\n" +
                "<th bgcolor=\"#ffcc00\"style=\"text-align: center;\">cost</th>\n" +
                "</tr>");

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Product product = new Product(i + 1, "Some product " + (i + 1), random.nextInt(100000));
            sb.append(getHtmlTableRow(product));
        }
        sb.append("</table>");
        resp.getWriter().write(sb.toString());
    }

    private String getHtmlTableRow(Product product) {
        return "<tr><td>" + product.getId()    + "</td>" +
                "<td>" + product.getTitle() + "</td>" +
                "<td>" + product.getCost()  + "</td></tr>";
    }
}
