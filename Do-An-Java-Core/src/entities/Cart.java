package entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    @Serial
    private final static long serialVersionUID = 6314293347342992295L;
    private static List<OrderItem> cartItems;

    public Cart() {

    }

    public Cart(List<OrderItem> cartItems) {
        Cart.cartItems = cartItems;
    }

    public List<OrderItem> getOrderItems() {
        return cartItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        cartItems = orderItems;
    }


    public void addItem(OrderItem orderItem){
        cartItems.add(orderItem);
    }

    public void removeItems(OrderItem orderItem){
        cartItems.remove(orderItem);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItems=" + cartItems +
                '}';
    }
}