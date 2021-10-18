package ua.goit.module2.service;

import ua.goit.module2.model.ProductsInBasket;

import java.util.Arrays;
import java.util.Scanner;

public class BasketReader {

    public static ProductsInBasket parseInputString(String line) {
        ProductsInBasket productsInBasket = new ProductsInBasket();
        if (line != null) {
            Arrays.stream(line.replaceAll("[^a-zA-Z]", "").split(""))
                    .filter(item -> !"".equals(item))
                    .forEach(item -> productsInBasket.merge(item, 1, Integer::sum));
        }
        return productsInBasket;
    }

    public static ProductsInBasket readFromCommandLine() {
        ProductsInBasket productsInBasket = new ProductsInBasket();
        System.out.println("Input string-basket:");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            productsInBasket = parseInputString(scanner.nextLine());
        }
        return productsInBasket;
    }
}
