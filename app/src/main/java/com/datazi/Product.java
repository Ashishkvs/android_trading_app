package com.datazi;

/**
 * Created by Ashish on 2/26/2018.
 */

public class Product {

    String name;
    String productId;
    String description;
    int quantity;
    boolean isEnabled;

    public Product() {

    }

    public Product(String name, String productId, String description, int quantity) {
        this.name = name;
        this.productId = productId;
        this.description = description;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}