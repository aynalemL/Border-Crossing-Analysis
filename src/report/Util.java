package report;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static boolean isEmpty(String arg) {
        return arg== null || arg.trim().equals("");
    }

    public static String formatDate(Date date) {
        return new SimpleDateFormat("MM/dd/yyyy").format(date);
    }
}
