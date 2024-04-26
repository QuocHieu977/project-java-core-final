package entities;

import statics.TypeProduct;
import utils.IOUtil;

import java.util.Scanner;

public class Laptop extends Product{
    private String cardGraphic;

    @Override
    public void inputNewProduct() {
        System.out.println("Mời bạn nhập thông số chi tiết của Laptop:");
        super.inputNewProduct();
        System.out.print("Card đồ hoạ: ");
        cardGraphic = new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");
        System.out.print("Có bao nhiêu mẫu sản phẩm Latop: ");
        int numberProductSample = IOUtil.intNumberInteger(1, "Vui lòng hãy nhập lại: ");
        for (int i = 0; i < numberProductSample; i++) {
            System.out.println();
            System.out.println("Nhập mẫu sản phẩm thứ " + (i + 1));
            ProductSample productSample = new ProductSample();
            productSample.inputProductSample();
            getProductSamples().add(productSample);
        }
        this.setTypeProduct(TypeProduct.LAPTOP);
    }

    @Override
    public void displayProduct() {
        System.out.println("*********************************");
        System.out.println("Thông tin chi tiết sản phẩm: ");
        System.out.println("Tên: " + getName());
        System.out.println("Card đồ hoạ: " + cardGraphic);
        super.displayProduct();
    }

}
