package com.datazi;

/**
 * Created by Ashish on 2/26/2018.
 */

public class Product {
    String id;
    String name;
    String description;
    double quantity;
    String imageUrl;
    boolean enable;


    public Product(String id, String name, String description, double quantity, String imageUrl,boolean enable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.imageUrl=imageUrl;
        this.enable = enable;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}