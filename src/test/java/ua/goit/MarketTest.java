package ua.goit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.goit.model.ProductData;
import ua.goit.model.Products;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class MarketTest {
    private Market testMarket;

    @Before
    public void init() {
        Products testProducts = new Products();

        testProducts.put("a", new ProductData(1f, 0, 0f));
        testProducts.put("A", new ProductData(3f, 2, 2f));
        testProducts.put("B", new ProductData(5f, 0, 0f));

        testMarket = new Market();
        testMarket.readProducts(testProducts);
    }

    @Test
    public void testCalculateTotalCostSimple() {
        testMarket.readBasket(new ByteArrayInputStream("aAAB".getBytes(StandardCharsets.UTF_8)));
        Assert.assertEquals(8f, testMarket.calculateTotalCost(), 0.0f);
    }

    @Test
    public void testCalculateTotalCostComposite() {
        testMarket.readBasket(new ByteArrayInputStream("BB2BCdBBBBAAAA".getBytes(StandardCharsets.UTF_8)));
        Assert.assertEquals(39f, testMarket.calculateTotalCost(), 0.0f);
    }

    @Test
    public void testCalculateTotalCostBreak() {
        testMarket.readBasket(new ByteArrayInputStream("\n".getBytes(StandardCharsets.UTF_8)));
        Assert.assertEquals(0f, testMarket.calculateTotalCost(), 0.0f);
    }

    @Test
    public void testCalculateTotalCostEmpty() {
        testMarket.readBasket(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)));
        Assert.assertEquals(0f, testMarket.calculateTotalCost(), 0.0f);
    }

    @After
    public void close() {
        testMarket = null;
    }
}
