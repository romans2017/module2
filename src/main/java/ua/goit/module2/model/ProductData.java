package ua.goit.module2.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductData)) return false;
        ProductData that = (ProductData) o;
        return Float.compare(that.getPrice(), getPrice()) == 0 && getActionNumber() == that.getActionNumber() && Float.compare(that.getActionPrice(), getActionPrice()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), getActionNumber(), getActionPrice());
    }
}
