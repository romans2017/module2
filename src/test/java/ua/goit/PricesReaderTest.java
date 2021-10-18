package ua.goit;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import org.junit.Assert;
import org.junit.Test;
import ua.goit.module2.model.ProductData;
import ua.goit.module2.model.Products;
import ua.goit.module2.service.PricesReader;

import java.io.EOFException;

public class PricesReaderTest {

    @Test
    public void testReadPrices() {

        String jsonString = "{\n" +
                "\"1\":{\"price\":2.0,\"actionNumber\":0,\"actionPrice\":0.0},\n" +
                "\"A\":{\"price\":2.0,\"actionNumber\":1,\"actionPrice\":0.0}}";

        Products testProducts = new Products();
        testProducts.put("1", new ProductData(2, 0, 0));
        testProducts.put("A", new ProductData(2, 1, 0));

        Assert.assertEquals(testProducts, PricesReader.readPrices(jsonString));
    }

    @Test
    public void testReadPricesEmpty() {

        Products testProducts = new Products();

        String jsonString = null;
        Assert.assertEquals(testProducts, PricesReader.readPrices(jsonString));

        jsonString = "";
        Assert.assertEquals(testProducts, PricesReader.readPrices(jsonString));
    }

    @Test
    public void testReadPricesNotValid() {

        Products testProducts = new Products();

        String jsonString = "{sfdsd";
        Assert.assertEquals(testProducts, PricesReader.readPrices(jsonString));
    }

}