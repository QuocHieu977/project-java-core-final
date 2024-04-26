package View;

import entities.*;
import service.*;
import statics.TypeProduct;
import statics.Contant;
import utils.IOUtil;

import java.util.List;
import java.util.Scanner;

public class MenuCustomer {
    private static List<OrderItem> orderItems;
    final ProductService productService = new ProductService();
    final CartService cartService = new CartService();
    final OrderService orderService = new OrderService();
    final CustomerService customerService = new CustomerService();
    private final   MenuMain menuMain = new MenuMain();
    Cart cart = new Cart(orderItems);
    private static Customer customer;
    public MenuCustomer() {
    }

    public MenuCustomer(List<OrderItem> orderItems, Customer customer) {
        MenuCustomer.orderItems = orderItems;
        MenuCustomer.customer = customer;
    }

    public void display(){
        int choose;
        do {
            showMenu();
            choose = IOUtil.intNumberInteger(1, 7, "Vui lòng hãy nhập lại: ");
            switch (choose) {
                case Contant.SEARCH_PRODUCT:
                    System.out.print("Mời bạn nhập tên sản phẩm: ");
                    String fName = new Scanner(System.in).nextLine();
                    productService.showProductSearchCustomer(fName);
                    break;
                case Contant.SHOW_PRODUCT:
                    productService.showProductCustomer();
                    break;
                case Contant.HOT_PRODUCT:
                    productService.showProductHot();
                    break;
                case Contant.CATEGORY_PRODUCT:
                    categoryMenu();
                    break;
                case Contant.CART:
                    cartMenu();
                    break;
                case Contant.PERSONAL_INFORMATION:
                    infoMenuCustomer();
                    break;
                case Contant.BACK_4:
                    menuMain.display();
                    break;
            }
        } while (choose !=7);
    }

    public void productMenu(Product product) {
        int choose;
        do {
            System.out.println("*****************************************");
            System.out.println("*            THÔNG TIN SẢN PHẨM         *");
            System.out.println("*****************************************");
            System.out.println("1. Thông tin sản phẩm");
            System.out.println("2. Mua sản phẩm");
            System.out.println("3. Quay lai");
            System.out.print("Mời bạn chọn chức năng: ");

            choose = IOUtil.intNumberInteger(1, 3, "Vui lòng hãy nhập lại: ");
            switch (choose) {
                case Contant.INFORMATION_PRODUCT:
                    productService.showDetailProduct(product);
                    break;
                case Contant.BUY_PRODUCT:
                    cartService.addCartItem(product);
                    break;
                case Contant.BACK_2:
                    break;
            }
        } while (choose !=3);
    }

    public void categoryMenu() {
        int choose;
        do {
            System.out.println("*****************************************");
            System.out.println("*            DANH MỤC SẢN PHẨM          *");
            System.out.println("*****************************************");
            System.out.println("1. Điện thoại");
            System.out.println("2. Latop");
            System.out.println("3. Quay lại");
            System.out.print("Mời bạn chọn chức năng: ");
            choose = IOUtil.intNumberInteger(1, 3, "Vui lòng hãy nhập lại: ");

            switch (choose) {
                case Contant.PHONE:
                    productService.categoryProduct(TypeProduct.IPHONE);
                    break;
                case Contant.LAPTOP:
                    productService.categoryProduct(TypeProduct.LAPTOP);
                    break;
                case Contant.BACK_2:
                    display();
                    break;
            }
        } while (choose !=3);
    }

    private void cartMenu(){
        int choose;
        do {
            System.out.println("*****************************************");
            System.out.println("*                GIỎ HÀNG               *");
            System.out.println("*****************************************");
            System.out.println("1. Xem giỏ hàng");
            System.out.println("2. Xoá sản phẩm trong giỏ hàng");
            System.out.println("3. Cập nhật số lượng sản phẩm");
            System.out.println("4. Đặt hàng");
            System.out.println("5. Quay lại");
            System.out.print("Mời bạn chọn chức năng: ");
            choose = IOUtil.intNumberInteger(1, 5, "Vui lòng nhập lại: ");
            switch (choose) {
                case Contant.SHOW_CART:
                    cartService.showCartItem();
                    break;
                case Contant.DELETE_PRODUCT_IN_CART:
                    System.out.print("Nhập tên sản phẩm: ");
                    String dName = new Scanner(System.in).nextLine();
                    cartService.removeItemInCart(dName);
                    break;
                case Contant.UPDATE_QUANTITY_PRODUCT:
                    updateQuantityCartMenu();
                    break;
                case Contant.ORDER:
                    orderService.createBills(customer);
                    break;
                case Contant.BACK_5:
                    break;
            }
        } while (choose !=5);
    }

    private void updateQuantityCartMenu(){
        int choose;
       do {
           System.out.println("*****************************************");
           System.out.println("*                GIỎ HÀNG               *");
           System.out.println("*****************************************");
           System.out.println("1. Thêm sản phẩm");
           System.out.println("2. Giảm sản phẩm");
           System.out.println("3. Quay lại");
           System.out.print("Mời bạn chọn chức năng: ");

           choose = IOUtil.intNumberInteger(1, 3, "Vui lòng nhập lại: ");
           switch (choose) {
               case Contant.ADD_PRODUCT_CART:
                   // 0 - add product in cart
                   cartService.updateQuantityCart(0);
                   break;
               case Contant.SUBTRACT_PRODUCT_CART:
                   // less product in cart
                   cartService.updateQuantityCart(1);
                   break;
               case Contant.BACK_2:
                   break;
           }
       } while (choose !=3);
    }

    private void infoMenuCustomer(){
        int choose;
        do {
            System.out.println("*****************************************");
            System.out.println("*            THÔNG TIN CÁ NHÂN          *");
            System.out.println("*****************************************");
            System.out.println("1. Đổi tên đăng nhập");
            System.out.println("2. Đổi mật khẩu");
            System.out.println("3. Đổi email");
            System.out.println("4. Đơn hàng của bạn: ");
            System.out.println("5. Quay lại");
            System.out.print("Mời bạn chọn chức năng: ");
            choose = IOUtil.intNumberInteger(1, 5, "Vui lòng hãy nhập lại: ");

            switch (choose) {
                case Contant.CHANGE_NAME_CUSTOMER:
                    System.out.print("Nhập tên đăng nhập mới: ");
                    String newName = new Scanner(System.in).nextLine();
                    customerService.changeNameCustomer(customer, newName);
                    break;
                case Contant.CHANGE_PASSWORD:
                    System.out.print("Nhập password mới: ");
                    String newPassword = new Scanner(System.in).nextLine();
                    customerService.changePasswordCustomer(customer, newPassword);
                    break;
                case Contant.CHANGE_EMAIL:
                    System.out.print("Nhập email mới: ");
                    String newEmail = new Scanner(System.in).nextLine();
                    customerService.changeEmailCustomer(customer, newEmail);
                    break;
                case Contant.ORDER_CUSTOMER:
                    menuOrder();
                    break;
                case Contant.BACK_5:
                    break;
            }
        } while (choose !=5);
    }

    private void menuOrder(){
        int choose;
        do {
            System.out.println("*****************************************");
            System.out.println("*                 ĐƠN HÀNG              *");
            System.out.println("*****************************************");
            System.out.println("1. Xem đơn hàng của bạn");
            System.out.println("2. Đánh giá");
            System.out.println("3. Huỷ đơn hàng");
            System.out.println("4. Quay lại");
            System.out.print("Mời bạn chọn chức năng: ");
            choose = IOUtil.intNumberInteger(1, 4, "Vui lòng hãy nhập lại: ");
            switch (choose) {
                case Contant.SHOW_ORDER_CUSTOMER:
                    orderService.getOrderCustomerFormFile(customer);
                    customer.showOrderHistoryOfCustomer();
                    break;
                case Contant.REVIEW_ORDER:
                    orderService.getOrderReceivedFormFile(customer);
                    orderService.showReceivedOrder();
                    Order order = orderService.chooseOrder(orderService.getOrdersReceived());
                    orderService.orderReview(customer, order);
                    productService.setReviewProduct(order);
                    break;
                case Contant.CANCEL_ORDER:
                    orderService.cancelOrder();
                    break;
                case Contant.BACK:
                    break;
            }
        } while (choose !=4);
    }

    private void showMenu() {
        System.out.println("*****************************************");
        System.out.println("*       CHÀO MỪNG BẠN ĐẾN VỚI FSHOP     *");
        System.out.println("*****************************************");
        System.out.println("1. Tìm kiếm sản phẩm theo tên.");
        System.out.println("2. Xem tất cả sản phẩm");
        System.out.println("3. Sản phẩm hot");
        System.out.println("4. Danh mục sản phẩm");
        System.out.println("5. Giỏ hàng");
        System.out.println("6. Thông tin cá nhân");
        System.out.println("7. Quay lại");
        System.out.print("Mời bạn chọn chức năng: ");
    }
}
