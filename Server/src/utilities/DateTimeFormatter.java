package utilities;

import java.time.LocalDateTime;

public class DateTimeFormatter {

    public static String getDateTime(){
        java.time.format.DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime date;
        date = LocalDateTime.now();
        return dtf.format(date);
    }
}
