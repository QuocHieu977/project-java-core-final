package service;

import View.MenuAccount;
import View.MenuAdmin;
import View.MenuCustomer;
import entities.*;
import statics.TypeRole;
import statics.TypeStatus;

import java.util.*;

public class LoginService {
    final List<OrderItem> orderItems = new ArrayList<>();
    final AdminService adminService = new AdminService();
    final CustomerService userService = new CustomerService();

    public void loginAccount() {

        String userName;
        Customer customer;
        Admin admin;
        do {
            System.out.print("Mời bạn nhập tên đăng nhập: "); //
            userName = new Scanner(System.in).nextLine().trim().replace("\\s+", " ");

            customer = userService.findByName(userName);
            admin = adminService.findByName(userName);

            if (customer == null && admin == null) {
                System.out.println("Tên đăng nhập sai. Mời bạn nhập tên đăng nhập khác");
            } else {
                System.out.print("Mời bạn nhập mật khẩu: ");
                String password = new Scanner(System.in).nextLine().trim().replace("\\s+", " ");

                if (customer !=null && customer.getPassword().equals(password) && customer.getTypeRole().equals(TypeRole.CUSTOMER)) {
                    MenuCustomer menuCustomer = new MenuCustomer(orderItems, customer);
                    menuCustomer.display();
                } else if (admin != null && admin.getPassword().equals(password) && admin.getTypeRole().equals(TypeRole.ADMIN)) {
                    MenuAdmin menuAdmin = new MenuAdmin();
                    menuAdmin.display();
                } else {
                    MenuAccount menu = new MenuAccount();
                    System.out.println("Mật khẩu sai");
                    menu.menuLoginFailure(customer);
                }
            }
        } while (customer == null && admin == null);
    }

    public void registerAccount() {
        userService.createAccount();
    }
}
