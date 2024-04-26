package view;

import entities.Order;
import entities.Product;
import service.OrderService;
import service.ProductService;
import service.ReportService;
import statics.Contant;
import utils.IOUtil;

import java.util.Scanner;

public class MenuAdmin {
    private final ProductService productService = new ProductService();
    private final OrderService orderService = new OrderService();
    private final ReportService reportService = new ReportService();
    private final MenuMain menuMain = new MenuMain();

    public void display(){
        int choose;
        do {
            showMenu();
            choose = IOUtil.intNumberInteger(1, 4, "Vui  lòng hãy nhập lại: ");
            switch (choose) {
                case Contant.PRODUCT_MANAGER:
                    menuProductManager();
                    break;
                case Contant.ORDER_MANAGER:
                    menuOrder();
                    break;
                case Contant.REVENUE_REPORT:
                    reportService.showRevenue();
                    break;
                case Contant.BACK:
                    menuMain.display();
                    break;
            }
        } while (choose !=4);
    }

    public void menuProductManager() {
        int choose;
        do {
            System.out.println("*****************************************");
            System.out.println("*             QUẢN LÝ SẢN PHẨM          *");
            System.out.println("*****************************************");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Xem tất cả sản phẩm");
            System.out.println("3. Chỉnh sửa sản phẩm");
            System.out.println("4. Quay lại");
            System.out.print("Mời bạn chọn chức năng: ");
            choose = IOUtil.intNumberInteger(1, 4, "Vui lòng hãy nhập lại: ");
            switch (choose) {
                case Contant.ADD_PRODUCT:
                    productService.addProduct();
                    break;
                case Contant.SHOW_PRODUCT:
                    productService.showProductAdmin();
                    break;
                case Contant.EDITING_PRODUCT:
                    System.out.print("Mời bạn nhập tên sản pẩm: ");
                    String fName = new Scanner(System.in).nextLine();
                    productService.showProductSearchAmin(fName);
                    break;
                case Contant.BACK:
                    display();
                    break;
            }
        } while (choose !=4);
    }

    public void modifyProduct(Product product) {
        int choose;
        do {
            System.out.println("*****************************************");
            System.out.println("*             QUẢN LÝ SẢN PHẨM          *");
            System.out.println("*****************************************");
            System.out.println("1. Thay đổi tên sản phẩm");
            System.out.println("2. Thay đổi giá sản phẩm");
            System.out.println("3. Cập nhật số lượng sản phẩm");
            System.out.println("4. Quay lại");
            System.out.print("Mời bạn chọn chức năng: ");
            choose = IOUtil.intNumberInteger(1, 4, "Vui lòng hãy nhập lại: ");

            switch (choose) {
                case Contant.CHANGE_NAME_PRODUCT:
                    System.out.print("Nhập tên sản phẩm mới: ");
                    String newName = new Scanner(System.in).nextLine();
                    productService.changeNameProduct(product, newName);
                    break;
                case Contant.CHANGE_PRICE_PRODUCT:
                    productService.changePriceProduct(product);
                    break;
                case Contant.UPDATE_QUANTITY_PRODUCT:
                    productService.changeQuantityProduct(product);
                    break;
                case Contant.BACK:
                    break;
            }
        } while (choose !=4);
    }

    private void menuOrder(){
        int choose;
        do {
            System.out.println("*****************************************");
            System.out.println("*            QUẢN LÝ ĐƠN HÀNG           *");
            System.out.println("*****************************************");
            System.out.println("1. Xem danh sách đơn hàng");
            System.out.println("2. Cập nhật trang thái đơn hàng");
            System.out.println("3. Quay lại");
            System.out.print("Mời bạn chọn chức năng: ");
            choose = IOUtil.intNumberInteger(1, 3, "Vui lòng hãy nhập lại: ");

            switch (choose) {
                case Contant.SHOW_ORDER:
                    orderService.showHistoryOrder();
                    break;
                case Contant.UPDATE_STATUS_ORDER:
                    orderService.showHistoryOrder();
                    Order order = orderService.chooseOrder(orderService.getOrdersAll());
                    menuOrderStatus(order);
                    break;
                case Contant.BACK_2:
                    break;
            }
        } while (choose != 3);
    }

    private void menuOrderStatus(Order order) {
        int choose;
        do {
            System.out.println("*****************************************");
            System.out.println("*             QUẢN LÝ ĐƠN HÀNG          *");
            System.out.println("*****************************************");
            System.out.println("1. Đã xác nhận");
            System.out.println("2. Đang chuẩn bị hàng");
            System.out.println("3. Đang giao");
            System.out.println("4. Đã nhận");
            System.out.println("5. Đã huỷ");
            System.out.println("6. Quay lại");
            System.out.print("Mời bạn chọn chức năng: ");
            choose = IOUtil.intNumberInteger(1, 6, "Vui lòng nhập lại: ");

            switch (choose) {
                case Contant.APPROVE_ORDER:
                    orderService.setOrderApproved(order);
                    break;
                case Contant.PREPARING_ORDER:
                    orderService.setOrderPreparing(order);
                    break;
                case Contant.DELIVERING_ORDER:
                    orderService.setOrderDelivering(order);
                    break;
                case Contant.RECEIVED_ORDER:
                    orderService.setOrderReceived(order);
                    break;
                case Contant.CANCELED_ORDER:
                    orderService.setOrderCancel(order);
                    break;
                case Contant.BACK_3:
                    break;
            }
        } while (choose != 6);
    }

    private void showMenu() {
        System.out.println("*****************************************");
        System.out.println("*             QUẢN TRỊ VIÊN             *");
        System.out.println("*****************************************");
        System.out.println("1. Quản lý sản phẩm");
        System.out.println("2. Quản lý đơn hàng");
        System.out.println("3. Báo cáo doanh thu");
        System.out.println("4. Quay lại");
        System.out.print("Mời bạn chọn chức năng: ");
    }
}
