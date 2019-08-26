package report;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static boolean isEmpty(String arg) {
        return arg== null || arg.trim().equals("");
    }

    public static String formatDate(Date date) {
        return  new SimpleDateFormat("M/d/yyyy hh:mm:ss a").format(date);
    }
}
