package view;

import entities.Customer;
import service.CustomerService;
import statics.Contant;
import utils.IOUtil;

public class MenuAccount {
    final CustomerService accountService = new CustomerService();

    public void menuLoginFailure(Customer customer) {
        System.out.println("1. Đăng nhập lại");
        System.out.println("2. Quên mật khẩu");

        int choose = IOUtil.intNumberInteger(1,2, "Vui lòng nhập lại lựa chọn từ 1 - 2");
        switch (choose) {
            case Contant.RE_LOGIN:
                break;
            case Contant.FORGOT_PASSWORD:
                accountService.forgotPassword(customer);
                break;
        }
    }
}
