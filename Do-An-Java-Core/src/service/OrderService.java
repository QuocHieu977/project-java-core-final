package service;

import entities.*;
import statics.TypeStatus;
import utils.IOUtil;
import utils.file.FileUtil;

import java.io.File;
import java.util.*;

public class OrderService {
    private final String ORDER_DATA_FILE = "src/data/orderHistory.dat";
    private List<Order> ordersCustomer;
    private Map<String, List<Order>> ordersHistory;
    private List<Order> ordersAll;
    private List<Order> ordersReceived;
    private final ProductService productService = new ProductService();
    private final ReviewService reviewService = new ReviewService();

    public OrderService() {
        ordersHistory = new HashMap<>();
        ordersCustomer = new ArrayList<>();
    }

    public List<Order> getOrdersAll() {
        getOrdersAllFormFile();
        return ordersAll;
    }

    public List<Order> getOrdersReceived() {
        return ordersReceived;
    }

    public void createBills(Customer customer) {
        getOrderHistoryFormFile();
        Cart cart = new Cart();
        double totalPrice = 0;

        if (cart.getOrderItems() == null) {
            cart.setOrderItems(new ArrayList<>());
        }
        if (!cart.getOrderItems().isEmpty()) {
            for (OrderItem e : cart.getOrderItems()) {
                totalPrice += e.getQuantity() * e.getPrice();
            }
            if (ordersHistory == null) {
                ordersHistory = new HashMap<>();
            }
            List<Order> customerOrder = ordersHistory.getOrDefault(customer.getName(), new ArrayList<>());
            List<OrderItem> orderItems = new ArrayList<>(cart.getOrderItems());
            Order order = new Order(orderItems, TypeStatus.PENDING, totalPrice, customer);
            order.setId(nextId());
            System.out.println("--------------------------------------------");
            showOder(order);
            System.out.println();
            System.out.print("Bạn có muốn đặt đơn hàng này không?(Y/N): ");
            String choose = new Scanner(System.in).nextLine();

            if (!choose.equalsIgnoreCase("n")) {
                customerOrder.add(order);
                ordersHistory.put(customer.getName(), customerOrder);
                FileUtil.writeDataFile(ordersHistory, ORDER_DATA_FILE);
                System.out.println("Bạn đã đơn hàng thành công...");
                cart.getOrderItems().clear();
            }
        } else {
            System.out.println("Bạn chưa có sản phẩm nào trong giỏ hàng.");
        }
    }

    public void showOder(Order order) {
        System.out.println("Chi tiết đơn hàng: ");
        System.out.println("Tên: " + order.getCustomer().getNameDisplay());
        System.out.println("Số Điện Thoại: " + order.getCustomer().getPhone());
        System.out.println("Địa chỉ: " + order.getCustomer().getAddress());
        System.out.println("Ngày đặt hàng: " + order.getOrderTime());
        System.out.println("Tình trạng đơn hàng: " + order.getStatus().value);
        System.out.println("Danh sách sản phẩm: ");
        for (OrderItem e : order.getOrderItems()) {
            System.out.println("- " + e.getName() + " (" + e.getQuantity() + " x " + IOUtil.formatPriceVND(e.getPrice()) + ") " +
                    " - " + "Màu: " + e.getColor() + " - " + "Bộ nhớ: " + e.getMemory());
        }
        System.out.println("Tổng giá: " + IOUtil.formatPriceVND(order.getTotalPrice()));
    }

    public void showHistoryOrder() {
        getOrdersAllFormFile();
        try {
            if (ordersAll.isEmpty()) {
                System.out.println("không có đơn hàng được đặt.");
            }
            for (int i = 0; i < ordersAll.size(); i++) {
                System.out.println("--------------------------------------------");
                System.out.println((i + 1) + ". Đơn hàng thứ " + (i + 1));
                showOder(ordersAll.get(i));
            }
            System.out.println();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void showReceivedOrder() {
        try {
            if (ordersReceived.isEmpty()) {
                System.out.println("Chưa có đơn hàng nào đã nhận.");
            }
            for (int i = 0; i < ordersReceived.size(); i++) {
                System.out.println("--------------------------------------------");
                System.out.println((i + 1) + ". Đơn hàng thứ " + (i + 1));
                showOder(ordersReceived.get(i));
            }
            System.out.println();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void getOrdersAllFormFile() {
        getOrderHistoryFormFile();
        ordersAll = new ArrayList<>();
        for (Map.Entry<String, List<Order>> entry : ordersHistory.entrySet()) {
            List<Order> orders = entry.getValue();
            ordersAll.addAll(orders);
        }
    }

    public void getOrderCustomerFormFile(Customer customer) {
        if (new File(ORDER_DATA_FILE).exists()) {
            getOrderHistoryFormFile();
            ordersCustomer = ordersHistory.get(customer.getName());
            customer.setOrders(ordersCustomer);
        }

    }

    public void getOrderReceivedFormFile(Customer customer) {
        ordersReceived = new ArrayList<>();
        getOrderCustomerFormFile(customer);
        if (ordersCustomer != null) {
            for (Order order : ordersCustomer) {
                if (order.getStatus().equals(TypeStatus.RECEIVED)) {
                    ordersReceived.add(order);
                }
            }
        }
    }

    private void getOrderHistoryFormFile() {
        if (new File(ORDER_DATA_FILE).exists()) {
            ordersHistory = (Map<String, List<Order>>) FileUtil.readDataFile(ORDER_DATA_FILE);
        }
    }

    public Order chooseOrder(List<Order> orders) {
        if (!orders.isEmpty()) {
            System.out.print("Chọn đơn hàng cần xử lý: ");
            int chooseNumberOrder = IOUtil.intNumberInteger(1, orders.size(), "Bạn nhập không đúng. Vui lòng hãy nhập lại");
            return orders.get(chooseNumberOrder - 1);
        }
        return null;
    }

    private void updateStatusOrders(Order order, TypeStatus typeStatus) {
        getOrderHistoryFormFile();
        getOrdersAllFormFile();
        for (Order e : ordersAll) {
            if (e.getId() == order.getId()) {
                e.setStatus(typeStatus);
            }
        }
        FileUtil.writeDataFile(ordersHistory, ORDER_DATA_FILE);
    }

    public void cancelOrder() {
        Customer customer = new Customer();
        customer.showOrderHistoryOfCustomer();
        Order order = chooseOrder(customer.getOrders());
        if (order != null) {
            if (order.getStatus().equals(TypeStatus.DELIVERING) ||
                    order.getStatus().equals(TypeStatus.RECEIVED) ||
                    order.getStatus().equals(TypeStatus.PREPARING_ORDER)) {
                System.out.println("Bạn không thể huỷ đơn hàng này...");
            } else if (order.getStatus().equals(TypeStatus.CANCELED)) {
                System.out.println("Bạn đã huỷ đơn hàng này rồi...");
            } else {
                updateStatusOrders(order, TypeStatus.REQUEST_CANCEL);
                System.out.println("Bạn đã yêu cầu huỷ đơn hàng...");
            }
        }
    }

    public void orderReview(Customer customer, Order order) {
        getOrderHistoryFormFile();
        getOrdersAllFormFile();
        if (order != null) {
            if (order.getRated() == 1) {
                System.out.println("Đơn hàng đã được đánh giá");
            } else {
                Review review = new Review();
                review.inputReview();
                review.setCustomer(customer);
                for (OrderItem orderItem : order.getOrderItems()) {
                    review.setProductID(orderItem.getProductID());
                }
                System.out.println("Bạn đã đánh giá sản phẩm thành công...");
                reviewService.addReview(review);

                for (Order e : ordersAll) {
                    if (e.getId() == order.getId()) {
                        e.setRated(1);
                    }
                }
            }
        }
        FileUtil.writeDataFile(ordersHistory, ORDER_DATA_FILE);
    }

    public void setOrderApproved(Order order){
        if (!order.getStatus().equals(TypeStatus.PENDING)) {
            System.out.println("Không thể xác nhận đơn hàng này");
        } else {
            updateStatusOrders(order, TypeStatus.APPROVED);
            for(OrderItem orderItem: order.getOrderItems()) {
                productService.updateQuantityProduct(orderItem, 1);
            }
            System.out.println("Đã cập nhật trạng thái đơn hàng thành công...");
        }
    }

    public void setOrderPreparing(Order order) {
        if (!order.getStatus().equals(TypeStatus.APPROVED)) {
            System.out.println("Không thể chuẩn bị đơn hàng này");
        } else {
            updateStatusOrders(order, TypeStatus.PREPARING_ORDER);
            System.out.println("Đã cập nhật trạng thái đơn hàng thành công...");
        }
    }

    public void setOrderDelivering(Order order) {
        if (!order.getStatus().equals(TypeStatus.PREPARING_ORDER)) {
            System.out.println("Không thể giao đơn hàng này");
        } else {
            updateStatusOrders(order, TypeStatus.DELIVERING);
            System.out.println("Đã cập nhật trạng thái đơn hàng thành công...");
        }
    }

    public void setOrderReceived(Order order) {
        if (!order.getStatus().equals(TypeStatus.DELIVERING)) {
            System.out.println("Không thể nhận đơn hàng này");
        } else {
            updateStatusOrders(order, TypeStatus.RECEIVED);
            System.out.println("Đã cập nhật trạng thái đơn hàng thành công...");
        }
    }

    public void setOrderCancel(Order order) {
        if (!order.getStatus().equals(TypeStatus.REQUEST_CANCEL)) {
            System.out.println("Không thể huỷ đơn hàng này");
        } else {
            updateStatusOrders(order, TypeStatus.CANCELED);
            for(OrderItem orderItem: order.getOrderItems()) {
                productService.updateQuantityProduct(orderItem, 0);
            }
            System.out.println("Đã cập nhật trạng thái đơn hàng thành công...");
        }
    }

    private int nextId() {
        getOrdersAllFormFile();
        int a = 10;
        for (int i = 10; i < 999; i++) {
            int count = 0;
            for (Order order : ordersAll) {
                if (i == order.getId()) {
                    count++;
                    break;
                }
            }
            if (count == 0) {
                a = i;
                break;
            }
        }
        return a;
    }
}
