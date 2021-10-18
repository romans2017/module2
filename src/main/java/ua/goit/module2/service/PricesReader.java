package ua.goit.module2.service;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import ua.goit.module2.model.Products;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PricesReader {

    public static String readFromFile(String filePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Products readPrices(String jsonString) {
        Gson gson = new Gson();
        Products products = new Products();
        try {
            products = gson.fromJson(jsonString, Products.class);
        } catch (JsonIOException | JsonSyntaxException e) {
            e.printStackTrace();
        }
        if (products == null) {
            products = new Products();
        }
        return products;
    }
}
