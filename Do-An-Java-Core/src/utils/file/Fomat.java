package utils.file;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Fomat {
    public static String formatPrice(double price) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        DecimalFormat formatter = new DecimalFormat("#,###.00", symbols);
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);

        return formatter.format(price);

    }
}
