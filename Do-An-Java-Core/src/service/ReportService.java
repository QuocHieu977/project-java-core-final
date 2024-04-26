package service;

import entities.Order;;
import entities.OrderItem;
import statics.TypeStatus;
import utils.IOUtil;
import java.util.*;

public class ReportService {
    private final List<Order> orders;
    private double totalRevenue;

    public ReportService() {
        OrderService orderService = new OrderService();
        orders = orderService.getOrdersAll();
    }

    public void getRevenueByDateRange() {
        for (Order order : orders) {
            if (order.getStatus().equals(TypeStatus.RECEIVED)) {
                totalRevenue += order.getTotalPrice();
            }
        }
    }

    public void showRevenue() {
        getRevenueByDateRange();
        System.out.println();
        System.out.printf("%-35s%-25s%-5s\n","Tên sản phẩm","Giá", "Số lượng");
        for (Order order : orders) {
            if (order.getStatus().equals(TypeStatus.RECEIVED)) {
                for (OrderItem orderItem: order.getOrderItems()) {
                    System.out.printf("%-35s%-25s%-5d\n",orderItem.getName(),IOUtil.formatPriceVND(orderItem.getPrice()), orderItem.getQuantity());
                }
            }

        }
        System.out.println("Tổng doanh thu: " + IOUtil.formatPriceVND(totalRevenue));
    }
}
