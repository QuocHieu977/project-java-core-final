package entities;

import utils.IOUtil;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

public class Review implements Serializable {
    @Serial
    private final static long serialVersionUID = 6314293347342992295L;
    private static int autoID;
    private int id;
    private int productID;
    private Customer customer;
    private int rating;
    private String comment;
    private LocalDate createdDate;

    public Review() {
        this.id = ++autoID;
        createdDate = LocalDate.now();
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void inputReview(){
        System.out.print("Mời bạn số sao: ");
        rating = IOUtil.intNumberInteger(1, 5, "Số sao đánh giá từ 1 - 5. Vui lòng bạn hãy nhập lại: ");
        System.out.print("Mời bạn nhập mô tả: ");
        comment = new Scanner(System.in).nextLine();
    }
}
