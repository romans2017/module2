package ua.goit.module2;

import ua.goit.module2.model.ProductData;
import ua.goit.module2.model.Products;
import ua.goit.module2.model.ProductsInBasket;
import ua.goit.module2.service.BasketReader;
import ua.goit.module2.service.PricesReader;

import java.util.*;

public class Market {

    private final Products products;
    private final ProductsInBasket mapProductId;

    public Market() {
        this.products = new Products();
        this.mapProductId = new ProductsInBasket();
    }

    private float calculateProduct(String productId, int countInBasket) {

        int nonActionCount;
        int actionSet = 0;
        ProductData productData = products.get(productId);
        if (productData == null) {
            return 0f;
        } else if (productData.getActionNumber() != 0) {
            nonActionCount = countInBasket % productData.getActionNumber();
            actionSet = (countInBasket - nonActionCount) / productData.getActionNumber();
        } else {
            nonActionCount = countInBasket;
        }

        return (float) nonActionCount * productData.getPrice() + (float) actionSet * productData.getActionPrice();
    }

    public float calculateTotalCost() {
        return mapProductId
                .entrySet()
                .stream()
                .map(item -> calculateProduct(item.getKey(), item.getValue()))
                .reduce(0f, Float::sum);
    }

    public <T extends HashMap<String, ProductData>> Market setProducts(T products) {
        this.products.clear();
        this.products.putAll(products);
        return this;
    }

    public <T extends HashMap<String, Integer>> Market setProductsInBasket(T mapProductId) {
        this.mapProductId.clear();
        this.mapProductId.putAll(mapProductId);
        return this;
    }

    public static void main(String[] args) {
        Market market = new Market();
        market.setProducts(PricesReader.readPrices(PricesReader.readFromFile("src/main/resources/products.json")))
                .setProductsInBasket(BasketReader.readFromCommandLine());

        System.out.println("Total cost: " + market.calculateTotalCost());
    }
}
