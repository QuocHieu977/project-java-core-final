package entities;

import service.OrderService;
import statics.TypeRole;
import statics.TypeStatus;
import utils.IOUtil;
import utils.file.FileUtil;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Customer extends User implements Serializable {
    private final String ORDER_CUSTOMER_DATA_FILE = "src/data/orderOfCustomer.dat";
    @Serial
    private final static long serialVersionUID = 6314293347342992295L;
    private int id;
    private String nameDisplay;
    private String address;
    private String phone;
    private List<Order> orders;


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNameDisplay() {
        return nameDisplay;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public void setMail(String mail) {
        super.setMail(mail);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public void setTypeRole(TypeRole typeRole) {
        super.setTypeRole(typeRole);
    }

    public void setOrders(List<Order> orders) {
        getOrderCustomerFormFile();
        this.orders = orders;
        FileUtil.writeDataFile(orders, ORDER_CUSTOMER_DATA_FILE);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void inputNewCustomer() {
        System.out.println("Mời bạn nhập thông tin chi tiết: ");
        System.out.print("Nhập tên của bạn: ");
        nameDisplay = new Scanner(System.in).nextLine().trim().replace("\\s+", " ");
        System.out.print("Nhập địa chỉ của bạn: ");
        address = new Scanner(System.in).nextLine().trim().replace("\\s+", " ");

        while (true) {
            System.out.print("Nhập số điện thoại của bạn: ");
            phone = new Scanner(System.in).nextLine().trim().replace("\\s", " ");
            if (regexPhoneNumber(phone))
                break;
            System.out.println("Số điện thoại không đúng. Vui lòng nhập lại");
        }

    }

    public boolean regexPhoneNumber(String phone) {
        String regex = "(84|0[3|5|7|8|9])+([0-9]{8})\\b";
        return phone.matches(regex);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + nameDisplay + '\'' +
                ", address='" + address + '\'' +
                ", email='" + getMail() + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + getName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", role='" + getTypeRole() + '\'' +
                '}' + "\n";
    }

    private void getOrderCustomerFormFile(){
        if (new File(ORDER_CUSTOMER_DATA_FILE).exists()) {
            orders = (List<Order>) FileUtil.readDataFile(ORDER_CUSTOMER_DATA_FILE);
        }
    }

    public void showOrderHistoryOfCustomer() {
        getOrderCustomerFormFile();
        OrderService orderService = new OrderService();
        if (orders == null) {
            orders = new ArrayList<>();
        }
        if (orders.isEmpty()) {
            System.out.println("Bạn chưa có đơn hàng nào.");
        }
        for (int i=0;  i<orders.size(); i++) {
            System.out.println("--------------------------------------------");
            System.out.println((i+1) + ". Đơn hàng thứ " + (i+1));
            orderService.showOder(orders.get(i));
        }
        System.out.println();
    }
}
