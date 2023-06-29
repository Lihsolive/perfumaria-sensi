package model;

import utils.Utils;

//classe para gerar um id para o produto
public class Product {
    private static int count = 1;

    private int id;
    private String name;
    private Double price;

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
        this.id = count;
        Product.count ++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String toString() {
        return "Id: " + this.getId() +
                "\nNome: " + this.getName() +
                "\nPreço " + Utils.doubleToString(this.getPrice());
    }
}
