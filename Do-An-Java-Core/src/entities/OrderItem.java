package entities;

import java.io.Serial;
import java.io.Serializable;

public class OrderItem implements Serializable {
    @Serial
    private final static long serialVersionUID = 6314293347342992295L;
    private int id;
    private int productID;
    private String name;
    private String color;
    private String memory;
    private int quantity;
    private double price;

    public OrderItem(int id, int productID , String name, String color, String memory, int quantity, double price) {
        this.id = id;
        this.productID = productID;
        this.name = name;
        this.color = color;
        this.memory = memory;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getMemory() {
        return memory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public int getProductID() {
        return productID;
    }



    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
