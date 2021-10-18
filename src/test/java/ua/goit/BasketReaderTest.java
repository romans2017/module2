package ua.goit;

import org.junit.Assert;
import org.junit.Test;
import ua.goit.module2.model.ProductsInBasket;
import ua.goit.module2.service.BasketReader;

public class BasketReaderTest {

    @Test
    public void testParseInputString() {
        ProductsInBasket testProductsInBasket = new ProductsInBasket();
        testProductsInBasket.put("A", 2);
        testProductsInBasket.put("b", 1);
        testProductsInBasket.put("a", 1);

        Assert.assertEquals(testProductsInBasket, BasketReader.parseInputString("Aba 1A 1"));
    }

}