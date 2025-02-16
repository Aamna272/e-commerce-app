package com.example.semesterproject.activities;

public class Product {
    private String name;
    private String image;
    private String category;
    private double price;

    public Product(String title, String image, String category, double price) {
        this.name = title;
        this.image = image;
        this.category = category;
        this.price = price;
    }

    public String getTitle() { return name; }
    public String getImage() { return image; }

    public String getCategory() { return category; }
    public double getPrice() { return price; }
}
