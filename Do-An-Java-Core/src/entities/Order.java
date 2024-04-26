package entities;

import statics.TypeStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Order implements Serializable{
    @Serial
    private final static long serialVersionUID = 6314293347342992295L;
    private int id;
    private List<OrderItem> orderItems;
    private LocalDateTime orderTime;
    private TypeStatus status;
    private double totalPrice;
    private Customer customer;
    private int rated; // 0 is rated - 1 is not rated


    public Order(List<OrderItem> orderItems, TypeStatus status, double totalPrice, Customer customer) {
        this.orderItems = orderItems;
        this.orderTime = LocalDateTime.now();
        this.status = status;
        this.totalPrice = totalPrice;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public TypeStatus getStatus() {
        return status;
    }

    public void setStatus(TypeStatus status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getRated() {
        return rated;
    }

    public void setRated(int rated) {
        this.rated = rated;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderItems=" + orderItems +
                ", orderTime=" + orderTime +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                ", customer=" + customer +
                '}';
    }
}
