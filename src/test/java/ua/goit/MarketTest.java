package ua.goit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ua.goit.module2.Market;
import ua.goit.module2.model.ProductData;
import ua.goit.module2.model.Products;
import ua.goit.module2.service.BasketReader;

import java.util.HashMap;

public class MarketTest {
    private Market testMarket;

    @Before
    public void init() {

        Products testProducts = new Products();

        testProducts.put("a", new ProductData(1f, 0, 0f));
        testProducts.put("A", new ProductData(3f, 2, 2f));
        testProducts.put("B", new ProductData(5f, 0, 0f));

        testMarket = new Market();
        testMarket.setProducts(testProducts);
    }

    @Test
    public void testCalculateTotalCostSimple() {
        testMarket.setProductsInBasket(BasketReader.parseInputString("aAB"));
        Assert.assertEquals(9f, testMarket.calculateTotalCost(), 0.0f);
    }

    @Test
    public void testCalculateTotalCostComposite() {
        testMarket.setProductsInBasket(BasketReader.parseInputString("BB2BCdBBBBAAAA"));
        Assert.assertEquals(39f, testMarket.calculateTotalCost(), 0.0f);
    }

    @Test
    public void testCalculateTotalCostBreak() {
        testMarket.setProductsInBasket(BasketReader.parseInputString("\n"));
        Assert.assertEquals(0f, testMarket.calculateTotalCost(), 0.0f);
    }

    @Test
    public void testCalculateTotalCostEmpty() {
        testMarket.setProductsInBasket(BasketReader.parseInputString(""));
        Assert.assertEquals(0f, testMarket.calculateTotalCost(), 0.0f);
    }

    @Test
    public void testCalculateTotalCostNull() {
        testMarket.setProductsInBasket(BasketReader.parseInputString(null));
        Assert.assertEquals(0f, testMarket.calculateTotalCost(), 0.0f);
    }

}
