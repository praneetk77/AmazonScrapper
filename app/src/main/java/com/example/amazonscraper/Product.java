package com.example.amazonscraper;

public class Product {

    private String name;
    private String websiteURL;
    private String imageURL;

    public Product(String name, String websiteURL, String imageURL) {
        this.name = name;
        this.websiteURL = websiteURL;
        this.imageURL = imageURL;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
