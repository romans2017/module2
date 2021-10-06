package ua.goit.model;

public class ProductData {
    private final float price;
    private final int actionNumber;
    private final float actionPrice;

    public ProductData(float price, int actionNumber, float actionPrice) {
        this.price = price;
        this.actionNumber = actionNumber;
        this.actionPrice = actionPrice;
    }

    public float getPrice() {
        return price;
    }

    public int getActionNumber() {
        return actionNumber;
    }

    public float getActionPrice() {
        return actionPrice;
    }
}
