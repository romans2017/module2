package ua.goit;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import ua.goit.model.ProductData;
import ua.goit.model.Products;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Market {

    private Products products = new Products();
    private final Map<String, Integer> mapProductId = new HashMap<>();

    //from file
    public Market readProducts() {
        Gson gson = new Gson();
        try (FileReader fileReader = new FileReader("src/main/resources/products.json")) {
            products = gson.fromJson(fileReader, Products.class);
        } catch (IOException | JsonIOException | JsonSyntaxException e) {
            e.printStackTrace();
        }
        return this;
    }

    //from Map
    public <T extends Map<String, ProductData>> Market readProducts(T productsMap) {
        products.putAll(productsMap);
        return this;
    }

    public Market readBasket(InputStream inputStream) {
        mapProductId.clear();
        Scanner scanner = new Scanner(inputStream);
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Arrays.stream(line.replaceAll("[^a-zA-Z]", "").split(""))
                    .filter(item -> !"".equals(item) && products.containsKey(item))
                    .forEach(item -> mapProductId.merge(item, 1, Integer::sum));
        }
        return this;
    }

    private float calculateProduct(String productId, int countInBasket) {

        int nonActionCount;
        int actionSet = 0;
        ProductData productData = products.get(productId);
        if (productData.getActionNumber() != 0) {
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

    public static void main(String[] args) {
        Market market = new Market();
        System.out.println(market.readProducts().readBasket(System.in).calculateTotalCost());
    }
}
