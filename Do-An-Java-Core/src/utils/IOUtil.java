package utils;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class IOUtil {
    public static int intNumberInteger(int min, int max, String error) {
        int result;
        while (true) {
            try {
                result = new Scanner(System.in).nextInt();
                if (result < min || result > max)
                    throw new InputMismatchException();
                return result;
            } catch (InputMismatchException e){
                System.out.print(error);
            }
        }
    }

    public static int intNumberInteger(int number, String error) {
        int result;
        while (true) {
            try {
                result = new Scanner(System.in).nextInt();
                if (result < number)
                    throw new InputMismatchException();
                return result;
            } catch (InputMismatchException e){
                System.out.print(error);
            }
        }
    }

    public static double doubleNumberInteger(double number, String error) {
        double result;
        while (true) {
            try {
                result = new Scanner(System.in).nextDouble();
                if (result < number)
                    throw new InputMismatchException();
                return result;
            } catch (InputMismatchException e){
                System.out.print(error);
            }
        }
    }

    public static String formatPriceVND(double price) {
        NumberFormat vndFormat = NumberFormat.getNumberInstance(new Locale("vi", "VN"));
        return vndFormat.format(price)+"₫";
    }
}
