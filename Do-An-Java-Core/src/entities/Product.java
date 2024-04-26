package entities;

import service.ProductService;
import statics.TypeProduct;
import utils.IOUtil;
import utils.file.FileUtil;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Product implements Serializable {
    @Serial
    private final static long serialVersionUID = 6314293347342992295L;
    private int id;
    private String name;
    private String screen;
    private String ram;
    private String cpu;
    private List<ProductSample> productSamples;
    private List<Review> reviews;
    private String manufacturer;
    private TypeProduct typeProduct;
    private double avgRating;

    public Product() {
        this.productSamples = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public List<ProductSample> getProductSamples() {
        return productSamples;
    }

    public void setTypeProduct(TypeProduct typeProduct) {
        this.typeProduct = typeProduct;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }


    public void inputNewProduct() {
        System.out.print("Tên: ");
        this.setName(new Scanner(System.in).nextLine().trim().replace("\\s+", " "));
        System.out.print("Màn hình: ");
        this.setScreen(new Scanner(System.in).nextLine().trim().replace("\\s+", " "));
        System.out.print("RAM: ");
        this.setRam(new Scanner(System.in).nextLine().trim().replace("\\s+", " "));
        System.out.print("CPU: ");
        this.setCpu(new Scanner(System.in).nextLine().trim().replace("\\s+", " "));
        System.out.print("Hãng: ");
        this.setManufacturer(new Scanner(System.in).nextLine().trim().replace("\\s+", " "));
        this.setTypeProduct(TypeProduct.IPHONE);
    }

    public void displayProduct() {
        System.out.println("Màn hình: " + getScreen());
        System.out.println("RAM: " + getRam());
        System.out.println("CPU: " + getCpu());
        System.out.println("Loại sản phẩm: ");
        System.out.printf("%-20s%-10s%-15s\n", "Màu", "Bộ nhớ", "Giá");
        for (ProductSample productSample : getProductSamples()) {
            System.out.printf("%-20s%-10s%-15s\n", productSample.getColor(), productSample.getMemory(), IOUtil.formatPriceVND(productSample.getPrice()));
        }
        System.out.println();
        System.out.println("Bình luận: ");
        System.out.println("Lượt đánh giá trung bình: " + getAvgRating());
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        if (reviews.isEmpty()) {
            System.out.println();
            System.out.println("Chưa có lượt đánh giá nào!");
        } else {
            for (Review review : reviews) {
                System.out.println("----------------");
                System.out.println("Tên: " + review.getCustomer().getNameDisplay());
                System.out.println("Lượt đánh giá: " + review.getRating() + " sao");
                System.out.println("Bình luận: " + review.getComment());
                System.out.println("Ngày: " + review.getCreatedDate());
            }
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", screen='" + screen + '\'' +
                ", ram='" + ram + '\'' +
                ", cpu='" + cpu + '\'' + "\n" +
                ", productSamples=" + productSamples +
                ", manufacturer='" + manufacturer + '\'' +
                ", typeProduct=" + typeProduct + '\'' +
                ", avgRating=" + avgRating +
                '}' + "\n";
    }

}
