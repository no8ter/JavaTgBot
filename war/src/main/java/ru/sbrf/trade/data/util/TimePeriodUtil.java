package ru.sbrf.trade.data.util;

import org.joda.time.DateTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TimePeriodUtil {

    public static List<String> generateDateRange(DateTime start, DateTime end) {

        List<String> ret = new ArrayList<String>();
        DateTime tmp = start;
        while(tmp.isBefore(end) || tmp.equals(end)) {
            Date date = tmp.toDate();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            ret.add(dateFormat.format(date));
            tmp = tmp.plusDays(1);
        }
        return ret;
    }
}
