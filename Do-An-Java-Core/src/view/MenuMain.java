package view;

import service.LoginService;
import statics.Contant;
import utils.IOUtil;

public class MenuMain {
    final LoginService loginService = new LoginService();

    public void display() {
        int choose;
        do {
            showMenu();
            choose = IOUtil.intNumberInteger(1, 3, "Vui lòng nhập lại: ");

            switch (choose) {
                case Contant.LOGIN:
                    loginService.loginAccount();
                    break;
                case Contant.REGISTER:
                    loginService.registerAccount();
                    break;
                case Contant.EXIT:
                    System.exit(0);
                    break;
            }
        } while (true);
    }

    private void showMenu() {
        System.out.println("*****************************************");
        System.out.println("*                 FSHOP                 *");
        System.out.println("*****************************************");
        System.out.println("1 - Đăng nhập");
        System.out.println("2 - Đăng ký");
        System.out.println("3 - Thoát");
        System.out.print("Mời bạn chọn chức năng của chương trình: ");
    }
}
