package entities;

import utils.IOUtil;

import java.io.Serial;
import java.io.Serializable;
import java.util.Scanner;

public class Phone extends Product implements Serializable {
    @Serial
    private final static long serialVersionUID = 6314293347342992295L;
    private String cameraFont;
    private String cameraRear;


    public void setCameraFont(String cameraFont) {
        this.cameraFont = cameraFont;
    }

    public void setCameraRear(String cameraRear) {
        this.cameraRear = cameraRear;
    }

    @Override
    public void inputNewProduct() {
        System.out.println("Mời bạn nhập thông số chi tiết của điện thoại: ");
        super.inputNewProduct();
        System.out.print("Camera sau: ");
        this.setCameraRear(new Scanner(System.in).nextLine().trim().replace("\\s+", " "));
        System.out.print("Camera Selfie: ");
        this.setCameraFont(new Scanner(System.in).nextLine().trim().replace("\\s+", " "));
        System.out.println("---------------------------");

        System.out.print("Có bao nhiêu mẫu sản phẩm điện thoại: ");
        int numberProductSample = IOUtil.intNumberInteger(1, "Vui lòng hãy nhập lại: ");
        for (int i = 0; i < numberProductSample; i++) {
            System.out.println();
            System.out.println("Nhập mẫu sản phẩm thứ " + (i + 1));
            ProductSample productSample = new ProductSample();
            productSample.inputProductSample();
            getProductSamples().add(productSample);
        }
    }

    @Override
    public void displayProduct() {
        System.out.println("*********************************");
        System.out.println("Thông tin chi tiết sản phẩm: ");
        System.out.println("Tên: " + getName());
        System.out.println("Camera sau: " + cameraRear);
        System.out.println("Camera trước: " + cameraFont);
        super.displayProduct();
    }
}
