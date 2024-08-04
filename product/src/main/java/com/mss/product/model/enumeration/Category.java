package com.mss.product.model.enumeration;

public enum Category {
    ELECTRONICS("Electronics"),
    CLOTHING("Clothing"),
    FURNITURE("Furniture");

    public final String value;
    private Category(String value) {
        this.value = value;
    }
}
