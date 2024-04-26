package service;

import entities.*;
import utils.IOUtil;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class CartService {
    final ProductService productService = new ProductService();
    final Cart cart = new Cart();

    public void addCartItem(Product product) {
        ProductSample productSample = productService.chooseProductSample(product);

        System.out.print("Bạn muốn mua bao nhiêu sản phẩm: ");
        int quantity = IOUtil.intNumberInteger(1, "Vui lòng hãy nhập lại: ");

        if (productSample.isEnoughQuantity(quantity)) {
            int count = 0;
            if (cart.getOrderItems().isEmpty()) {
                cart.addItem(new OrderItem((productSample.getId()), product.getId(), product.getName(),
                        productSample.getColor(), productSample.getMemory(), quantity, productSample.getPrice()));
                System.out.println("Bạn đã thêm vào giỏ hàng thành công...");
            } else {
                try {
                    for (OrderItem e : cart.getOrderItems()) {
                        if (e.getColor().equals(productSample.getColor()) && e.getMemory().equals(productSample.getMemory())) {
                            e.setQuantity(e.getQuantity() + quantity);
                            System.out.println("Bạn đã thêm vào giỏ hàng thành công...");
                            count ++;
                            break;
                        }
                    }
                    if (count == 0) {
                        cart.addItem(new OrderItem(productSample.getId(), product.getId(), product.getName(),
                                productSample.getColor(), productSample.getMemory(), quantity, productSample.getPrice()));
                        System.out.println("Bạn đã thêm vào giỏ hàng thành công...");
                    }
                } catch (ConcurrentModificationException e) {
                    e.printStackTrace();
                }

            }
        } else {
            System.out.println("Hiện tại số lương sản phẩm trong cửa hàng không đủ.");
        }
    }

    public void removeItemInCart(String dName) {
        OrderItem orderItem = findByName(dName);
        if (orderItem != null) {
            cart.removeItems(orderItem);
            System.out.println("Bạn đã xoá thành công!");
        } else
            System.out.println("Không tìm thấy sản phẩm.");
    }

    public void updateQuantityCart(int check) {
        showCartItem();
        if (cart.getOrderItems() != null) {
            if (!cart.getOrderItems().isEmpty()) {
                System.out.println("---------------------------");
                System.out.print("Chọn sản phẩm: ");
                int choose = IOUtil.intNumberInteger(1, cart.getOrderItems().size(), "Vui lòng hãy nhập lại: ");
                OrderItem orderItem = cart.getOrderItems().get(choose - 1);
                System.out.print("Nhập số lượng: ");
                int quantity = IOUtil.intNumberInteger(1, "Vui lòng hãy nhập lại: ");
                // 0 - add product in cart
                if (check == 0) {
                    orderItem.setQuantity(orderItem.getQuantity() + quantity);
                }
                // 1 - less product in cart
                if (check == 1) {
                    orderItem.setQuantity(orderItem.getQuantity() - quantity);
                }
                System.out.println("Bạn đã cập nhật số lượng sản phẩm thành công!");

                // if product quantity equals 0 --> remove this product in cart
                try {
                    for (OrderItem orderItem1 : cart.getOrderItems()) {
                        if (orderItem1.getQuantity() == 0) {
                            cart.getOrderItems().remove(orderItem1);
                        }
                    }
                } catch (ConcurrentModificationException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void showCartItem() {
        if (cart.getOrderItems() == null || cart.getOrderItems().isEmpty()) {
            System.out.println("Bạn chưa sản phẩm nào giỏ hàng");
        } else {
            for (int i = 0; i < cart.getOrderItems().size(); i++) {
                System.out.println("---------------------------");
                System.out.println((i + 1) + ". Sản phẩm: " + (i + 1));
                System.out.println("\tTên sản phẩm: " + cart.getOrderItems().get(i).getName());
                System.out.println("\tMàu: " + cart.getOrderItems().get(i).getColor());
                System.out.println("\tBộ nhớ: " + cart.getOrderItems().get(i).getMemory());
                System.out.println("\tSố lượng: " + cart.getOrderItems().get(i).getQuantity());
                System.out.println("\tGiá: " + IOUtil.formatPriceVND(cart.getOrderItems().get(i).getPrice()));
            }
        }
    }

    public OrderItem findByName(String fName) {
        if (cart.getOrderItems() == null) {
            cart.setOrderItems(new ArrayList<>());
        } else {
            for (OrderItem e : cart.getOrderItems()) {
                if (e.getName().equalsIgnoreCase(fName))
                    return e;
            }
        }
        return null;
    }
}
