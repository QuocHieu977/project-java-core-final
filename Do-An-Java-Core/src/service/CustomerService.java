package service;

import entities.Customer;
import entities.Order;
import entities.User;
import statics.TypeRole;
import utils.file.FileUtil;

import java.io.File;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CustomerService {
    private final String CUSTOMER_DATA_FILE = "src/data/customer.dat";
    private List<Customer> customers = new ArrayList<>();

    public void inputAccount() {
        Customer customer = new Customer();
        while (true) {
            System.out.print("Mời bạn nhập tên đăng nhập: ");
            String name = new Scanner(System.in).nextLine().trim().replace("\\s+", " ");
            if (findByName(name) == null) {
                System.out.print("Mời bạn nhập email: ");
                String email = new Scanner(System.in).nextLine().trim().replace("\\s+", " ");
                if (findByEmail(email) == null) {
                    System.out.print("Mời bạn nhập mật khẩu: ");
                    String password = new Scanner(System.in).nextLine().trim().replace("\\s+", " ");
                    if (emailValidator(email) && passwordValidator(password)) {
                        customer.setId(nextId());
                        customer.setName(name);
                        customer.setMail(email);
                        customer.setPassword(password);
                        customer.setTypeRole(TypeRole.CUSTOMER);
                        customer.inputNewCustomer();
                        customers.add(customer);
                        break;
                    } else {
                        if (!emailValidator(email))
                            System.out.println("Email lỗi dạng");
                        if (!passwordValidator(password))
                            System.out.println("Mật khẩu phải từ 7 đến 15 ký tự, chứa ít nhất 1 ký tự in hoa, 1 ký tự đặc biệt (. , - _ ;)");
                    }
                } else {
                    System.out.println("Email đã tồn tại!");
                }
            } else {
                System.out.println("Tên đăng nhập đã tồn tại");
            }
        }
    }

    public void createAccount() {
        getAccountsFormFile();
        inputAccount();
        FileUtil.writeDataFile(customers, CUSTOMER_DATA_FILE);
        System.out.println("Bạn đã tạo tài thành công...");
        System.out.println(customers);
    }

    private boolean emailValidator(String email) {
        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);

        return pattern.matcher(email).matches();
    }

    private boolean passwordValidator(String password) {
        if (password.length() >= 7 && password.length() <= 15) {
            int countChar = 0, countCharSpecical = 0, countCharLowerCase = 0;
            for (int i = 0; i < password.length(); i++) {
                if (Character.isLowerCase(password.charAt(i)))
                    countCharLowerCase++;
                if (Character.isUpperCase(password.charAt(i)))
                    countChar++;
                if (!Character.isLetterOrDigit(password.charAt(i)) && !Character.isWhitespace(password.charAt(i)))
                    countCharSpecical++;
            }
            return countChar > 0 && countCharSpecical > 0 && countCharLowerCase > 0;
        }
        return false;
    }

    public Customer findByName(String userName) {
        getAccountsFormFile();
        for (Customer e : customers) {
            try {
                if (e.getName().equals(userName))
                    return e;
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public Customer findByEmail(String email) {
        getAccountsFormFile();
        for (Customer e : customers) {
            try {
                if (e.getMail().equalsIgnoreCase(email))
                    return e;
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }

        }
        return null;
    }

    public void forgotPassword(Customer e) {
        getAccountsFormFile();
        System.out.print("Mời bạn nhập email: ");
        String email = new Scanner(System.in).nextLine().trim().replace("\\s+", " ");

        Customer customer = findByEmail(email);
        if (customer != null && customer.getId() == e.getId()) {
            System.out.print("Mời bạn nhập mật khẩu mới: ");
            String newPassword = new Scanner(System.in).nextLine().trim().replace("\\s+", " ");
            if (passwordValidator(newPassword)) {
                customer.setPassword(newPassword);
                System.out.println("Bạn đã thay đổi mật khẩu thành công");
            } else
                System.out.println("Mật khẩu phải từ 7 đến 15 ký tự, chứa ít nhất 1 ký tự in hoa, 1 ký tự đặc biệt (. , - _ ;)");
        } else {
            System.out.println("Email không tồn tại. Mời bạn đăng ký tài khoản mới");
        }
        System.out.println(customers);
        FileUtil.writeDataFile(customers, CUSTOMER_DATA_FILE);
    }

    public int nextId() {
        getAccountsFormFile();
        int a = 10000;
        for (int i = 10000; i < 99999; i++) {
            int count = 0;
            for (Customer customer : customers) {
                if (i == customer.getId()) {
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

    public void getAccountsFormFile() {
        if (new File(CUSTOMER_DATA_FILE).exists()) {
            customers = (List<Customer>) FileUtil.readDataFile(CUSTOMER_DATA_FILE);
        }
    }

    public void changeNameCustomer(Customer customer, String newName){
        if (findByName(newName) == null) {
            for (Customer e : customers) {
                if (e.getId() == customer.getId()) {
                    e.setName(newName);
                    System.out.println("Bạn đã thay tên đăng nhập thành công");
                    break;
                }
            }
        } else {
            System.out.println("Tên đăng nhập đã tồn tại.");
        }
        FileUtil.writeDataFile(customers, CUSTOMER_DATA_FILE);
    }

    public void changeEmailCustomer(Customer customer, String newEmail)  {
        if (emailValidator(newEmail)) {
            if (findByEmail(newEmail) == null) {
                for (Customer e: customers) {
                    if (e.getId() == customer.getId()) {
                        e.setMail(newEmail);
                        System.out.println("Bạn đã thay đổi email thành công");
                        break;
                    }
                }
            } else {
                System.out.println("Email đã tồn tại. Hãy nhập email khác");
            }

        } else {
            System.out.println("Email không đúng định dạng.");
        }
        FileUtil.writeDataFile(customers, CUSTOMER_DATA_FILE);
    }

    public void changePasswordCustomer(Customer customer, String newPassword) {
        getAccountsFormFile();
        if (passwordValidator(newPassword)) {
            for (Customer e: customers) {
                if (e.getId() ==  customer.getId()) {
                    if (e.getPassword().equals(newPassword)) {
                        System.out.println("Mời bạn nhập mật khẩu khác");
                    } else {
                        e.setPassword(newPassword);
                        System.out.println("Bạn đã thay mật khẩu thành công");
                    }
                    break;
                }
            }
        } else {
            System.out.println("Mật khẩu phải từ 7 đến 15 ký tự, chứa ít nhất 1 ký tự in hoa, 1 ký tự đặc biệt (. , - _ ;)");
        }
        FileUtil.writeDataFile(customers, CUSTOMER_DATA_FILE);
    }
}
