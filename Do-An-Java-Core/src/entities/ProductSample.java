package entities;

import entities.Product;
import utils.IOUtil;

import java.io.Serial;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductSample implements Serializable {
    @Serial
    private final static long serialVersionUID = 6314293347342992295L;
    private static int autoId;
    private int id;
    private String color;
    private String memory;
    private double price;
    private int quantity;
    private int quantitySold;

    public ProductSample() {
        this.id = ++autoId;
    }

    public int getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public String getMemory() {
        return memory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = Math.max(quantity, 0);
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public boolean isEnoughQuantity(int quantityNeeded) {
        return this.quantity >= quantityNeeded;
    }

    public void inputProductSample() {
        System.out.print("Nhập bộ nhớ: ");
        memory = new Scanner(System.in).nextLine().trim().replace("\\s+", " ");
        System.out.print("Nhập màu: ");
        color = new Scanner(System.in).nextLine().trim().replace("\\s+", " ");
        System.out.print("Nhập giá: ");
        price = IOUtil.doubleNumberInteger(1, "Vui lòng hãy nhập lại: ");
        System.out.print("Nhập số lượng: ");
        quantity = IOUtil.intNumberInteger(1, "Vui lòng hãy nhập lại: ");
    }

    @Override
    public String toString() {
        return "ProductSample{" +
                "id='" + id + '\'' +
                ", color='" + color + '\'' +
                ", memory='" + memory + '\'' +
                ", price=" + IOUtil.formatPriceVND(price) +
                ", quantity=" + quantity +
                ", quantitySold=" + quantitySold +
                '}' + "\n";
    }
}
