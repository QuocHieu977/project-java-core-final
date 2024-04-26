package service;

import view.MenuAdmin;
import view.MenuCustomer;
import entities.*;
import statics.TypeProduct;
import utils.IOUtil;
import utils.file.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductService {
    public final String PRODUCTS_DATA_FILE = "src/data/product.dat";
    private List<Product> products = new ArrayList<>();
    private final ReviewService reviewService = new ReviewService();

    public List<Product> getProducts() {
        getProductFormFile();
        return products;
    }

    private void inputProduct() {
        System.out.print("Mời bạn nhập số lượng sản phẩm cần thêm vào: ");
        int numberProduct = IOUtil.intNumberInteger(1, "Vui lòng hãy nhập lại: ");

        for (int i = 0; i < numberProduct; i++) {
            System.out.println("Nhập thông tin sản phẩm thứ " + (i + 1));
            System.out.println("1. Điện Thoại");
            System.out.println("2. Laptop");
            System.out.print("Chọn loại sản phẩm: ");
            int choose = IOUtil.intNumberInteger(1, 2, "Vui lòng hãy nhập lại: ");
            switch (choose) {
                case 1:
                    Product phone = new Phone();
                    phone.inputNewProduct();
                    phone.setId(nextId());
                    products.add(phone);
                    break;
                case 2:
                    Product laptop = new Laptop();
                    laptop.inputNewProduct();
                    laptop.setId(nextId());
                    products.add(laptop);
                    break;
            }
        }
    }

    public void showProductCustomer() {
        getProductFormFile();
        try {
            if (products.isEmpty())
                System.out.println("Chưa có sản phẩm nào trong shop.");
            else {
                System.out.println();
                Product product = chooseProduct(products);
                System.out.println();
                MenuCustomer menuCustomer = new MenuCustomer();
                if (product == null) {
                    menuCustomer.display();
                } else {
                    System.out.println("Bạn đang chọn vào sản phẩm: " + product.getName());
                    menuCustomer.productMenu(product);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void showProductAdmin() {
        getProductFormFile();
        try {
            if (products.isEmpty()) {
                System.out.println("Hiện tại chưa chưa có sản phẩm.");
            } else {
                for (Product product : products) {
                    System.out.println();
                    System.out.println("Tên: " + product.getName());
                    System.out.println("Loại sản phẩm: ");
                    System.out.printf("%-20s%-10s%-15s%-20s%-20s\n", "Màu", "Bộ nhớ", "Giá", "Số lượng", "Số lượng bán");
                    for (ProductSample ps : product.getProductSamples()) {
                        System.out.printf("%-20s%-10s%-15s%-20s%-20s\n", ps.getColor(), ps.getMemory(), IOUtil.formatPriceVND(ps.getPrice()), ps.getQuantity(), ps.getQuantitySold());
                    }
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private Product chooseProduct(List<Product> listProduct) {
        for (int i = 0; i < listProduct.size(); i++) {
            System.out.println((i + 1) + ". " + listProduct.get(i).getName());
        }
        System.out.println((listProduct.size() + 1) + ". Quay lại");
        System.out.print("Chọn: ");
        int chooseNumberProduct = IOUtil.intNumberInteger(1, listProduct.size() + 1, "Vui lòng hãy nhập lại: ");
        if (chooseNumberProduct == listProduct.size() + 1) {
            return null;
        }
        return listProduct.get(chooseNumberProduct - 1);
    }

    public ProductSample chooseProductSample(Product product) {
        System.out.println();
        System.out.printf("%-10s%-20s%-10s%-15s\n", "Loại", "Màu", "Bộ nhớ", "Giá");
        for (int i = 0; i < product.getProductSamples().size(); i++) {
            System.out.printf("%-10s%-20s%-10s%-15s\n", (i + 1), product.getProductSamples().get(i).getColor(),
                    product.getProductSamples().get(i).getMemory(), IOUtil.formatPriceVND(product.getProductSamples().get(i).getPrice()));
        }
        System.out.print("Chọn loại sản phẩm: ");
        int chooseNumberProductSample = IOUtil.intNumberInteger(1, product.getProductSamples().size(), "Bạn nhập không đúng. Vui lòng hãy nhập lại");
        return product.getProductSamples().get(chooseNumberProductSample - 1);
    }

    public void addProduct() {
        getProductFormFile();
        inputProduct();
        FileUtil.writeDataFile(products, PRODUCTS_DATA_FILE);
        System.out.println("Bạn đã tạo sản phẩm thành công!!!");
    }

    private List<Product> searchProduct(String fName) {
        getProductFormFile();
        List<Product> searchProducts = new ArrayList<>();
        try {
            for (Product e : products) {
                if (e.getName().toLowerCase().contains(fName.toLowerCase())) {
                    searchProducts.add(e);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return searchProducts;
    }

    public void showProductSearchCustomer(String fName) {
        List<Product> searchProducts = searchProduct(fName);

        if (searchProducts.isEmpty()) {
            System.out.println();
            System.out.println("Không tìm thấy sản phẩm");
            System.out.println();
        } else {
            System.out.println();
            System.out.println("Kết quả tìm kiếm: ");
            Product product = chooseProduct(searchProducts);
            System.out.println();
            MenuCustomer menuCustomer = new MenuCustomer();
            if (product == null) {
                menuCustomer.display();
            } else {
                System.out.println("Bạn đang chọn sản phẩm: " + product.getName());
                menuCustomer.productMenu(product);
            }
        }
    }

    public void showProductSearchAmin(String fName) {
        List<Product> searchProducts = searchProduct(fName);

        if (searchProducts.isEmpty()) {
            System.out.println();
            System.out.println("Không tìm thấy sản phẩm");
            System.out.println();
        } else {
            System.out.println("===================================");
            System.out.println("Kết quả tìm kiếm: ");
            Product product = chooseProduct(searchProducts);
            System.out.println();
            MenuAdmin menuAdmin = new MenuAdmin();
            if (product == null) {
                menuAdmin.menuProductManager();
            } else {
                System.out.println("Bạn đang chọn sản phẩm: " + product.getName());
                System.out.println();
                menuAdmin.modifyProduct(product);
            }
        }
        FileUtil.writeDataFile(products, PRODUCTS_DATA_FILE);
    }

    public void showDetailProduct(Product product) {
        product.displayProduct();
    }

    public void categoryProduct(TypeProduct type) {
        getProducts();
        List<Product> typeProducts = new ArrayList<>();
        int count = 0;
        for (Product e : products) {
            if (e.getTypeProduct().equals(type)) {
                typeProducts.add(e);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Không sản phẩm nào trong danh mục này.");
        } else {
            System.out.println();
            Product product = chooseProduct(typeProducts);
            System.out.println();
            MenuCustomer menuCustomer = new MenuCustomer();
            if (product == null) {
                menuCustomer.categoryMenu();
            } else {
                System.out.println("Bạn đang chọn sản phẩm: " + product.getName());
                menuCustomer.productMenu(product);
            }
        }
    }


    public void updateQuantityProduct(OrderItem orderItem, int check) {
        getProductFormFile();
        for (Product product : products) {
            for (ProductSample productSample : product.getProductSamples()) {
                if (product.getId() == orderItem.getProductID()) {
                    if (productSample.getId() == orderItem.getId()) {
                        // 0 - if order canceled --> add back the quantity of product
                        if (check == 0) {
                            productSample.setQuantity(productSample.getQuantity() + orderItem.getQuantity());
                            productSample.setQuantitySold(productSample.getQuantitySold() - orderItem.getQuantity());
                        }
                        // 1 - if order placed  --> subtract the quantity of product
                        if (check == 1) {
                            productSample.setQuantitySold(productSample.getQuantitySold() + orderItem.getQuantity());
                            productSample.setQuantity(productSample.getQuantity() - orderItem.getQuantity());
                        }
                    }
                }
            }
        }
        FileUtil.writeDataFile(products, PRODUCTS_DATA_FILE);
    }

    public void changeNameProduct(Product product, String newName) {
        getProductFormFile();
        product.setName(newName);
        FileUtil.writeDataFile(products, PRODUCTS_DATA_FILE);
        System.out.println("Bạn đã thay đổi tên sản phẩm thành công...");
    }

    public void changePriceProduct(Product product) {
        getProductFormFile();
        System.out.println();
        for (int i = 0; i < product.getProductSamples().size(); i++) {
            System.out.println((i + 1) + ": ");
            System.out.println("Màu: " + product.getProductSamples().get(i).getColor());
            System.out.println("Bộ nhớ: " + product.getProductSamples().get(i).getMemory());
            System.out.println("Giá: " + IOUtil.formatPriceVND(product.getProductSamples().get(i).getPrice()));
            System.out.println("----------------");
        }
        System.out.print("Bạn muốn thay đổi giá cho loại sản phẩm nào: ");

        int choose = IOUtil.intNumberInteger(1, product.getProductSamples().size(), "Vui lòng nhập lại: ");
        System.out.print("Mời bạn nhập giá mới cho sản phẩm: ");
        double newPrice = IOUtil.intNumberInteger(1, "Vui lòng hãy nhập lại: ");
        product.getProductSamples().get(choose - 1).setPrice(newPrice);
        FileUtil.writeDataFile(products, PRODUCTS_DATA_FILE);
        System.out.println("Bạn đã thay đổi giá sản phẩm thành công...");
    }

    public void changeQuantityProduct(Product product) {
        getProductFormFile();
        System.out.println();
        for (int i = 0; i < product.getProductSamples().size(); i++) {
            System.out.println((i + 1) + ": ");
            System.out.println("Màu: " + product.getProductSamples().get(i).getColor());
            System.out.println("Bộ nhớ: " + product.getProductSamples().get(i).getMemory());
            System.out.println("Giá: " + IOUtil.formatPriceVND(product.getProductSamples().get(i).getPrice()));
            System.out.println("Số lượng: " + product.getProductSamples().get(i).getQuantity());
            System.out.println("----------------");
        }
        System.out.print("Bạn muốn thêm số lượng cho loại sản phẩm nào: ");

        int choose = IOUtil.intNumberInteger(1, product.getProductSamples().size(), "Vui lòng nhập lại: ");
        System.out.print("Mời bạn nhập số lượng cần thêm cho sản phẩm: ");
        int newQuantity = IOUtil.intNumberInteger(1, "Vui lòng nhập lại: ");
        product.getProductSamples().get(choose - 1).setQuantity(product.getProductSamples().get(choose - 1).getQuantity() + newQuantity);
        FileUtil.writeDataFile(products, PRODUCTS_DATA_FILE);
        System.out.println("Bạn đã cập nhật số lượng sản phẩm thành công...");
    }

    public void setReviewProduct(Order order) {
        getProductFormFile();
        if (order != null) {
            List<Review> reviews;
            double avgRating;
            for (Product product : products) {
                for (OrderItem orderItem : order.getOrderItems()) {
                    reviews = reviewService.getReviewByProductId(orderItem.getProductID());
                    avgRating = reviewService.getAvgRatingByProductID(orderItem.getProductID());

                    if (product.getId() == orderItem.getProductID()) {
                        product.setReviews(reviews);
                        product.setAvgRating(avgRating);
                    }
                }
            }
        }
        FileUtil.writeDataFile(products, PRODUCTS_DATA_FILE);
    }

    public List<Product> getHotProduct() {
        List<Product> productList = new ArrayList<>(getProducts());
        List<Product> hotProducts = new ArrayList<>();

        productList.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                int totalQuantity1 = 0;
                for (ProductSample productSample : o1.getProductSamples()) {
                    totalQuantity1 += productSample.getQuantitySold();
                }

                int totalQuantity2 = 0;
                for (ProductSample productSample : o2.getProductSamples()) {
                    totalQuantity2 += productSample.getQuantitySold();
                }
                return totalQuantity2 - totalQuantity1;
            }
        });

        for (Product product : productList) {
            if (product.getAvgRating() >= 4.5) {
                hotProducts.add(product);
            }
        }

        return hotProducts.subList(0, Math.min(3, hotProducts.size()));
    }

    public void showProductHot() {
        List<Product> productHot = getHotProduct();
        try {
            if (productHot.isEmpty())
                System.out.println("Hiện tại chưa có sản phẩm hot");
            else {
                System.out.println();
                //choose product in list products
                Product product = chooseProduct(productHot);
                System.out.println();
                MenuCustomer menuCustomer = new MenuCustomer();
                if (product == null) {
                    menuCustomer.display();
                } else {
                    System.out.println("Bạn đang chọn vào sản phẩm: " + product.getName());
                }
                menuCustomer.productMenu(product);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private int nextId() {
        getProductFormFile();
        int a = 100;
        for (int i = 100; i < 999; i++) {
            int count = 0;
            for (Product product : products) {
                if (i == product.getId()) {
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


    public void getProductFormFile() {
        if (new File(PRODUCTS_DATA_FILE).exists()) {
            products = (List<Product>) FileUtil.readDataFile(PRODUCTS_DATA_FILE);
        }
    }
}
