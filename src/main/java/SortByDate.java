import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SortByDate {


    public DateLogs sort(DateLogs dateLogs) throws ParseException {

        List<Date> formatDate = new ArrayList<>();

        DateLogs sortDateLogs = new DateLogs();
        ArrayList<String> list = new ArrayList<>();

        for (Map.Entry me : dateLogs.getDateLogs().entrySet()) {
            list.add((String) me.getKey());
            System.out.println(me.getKey());
        }

        List<String> dateToString = new ArrayList<>();

        stringToDate(list);

        for (Date date : formatDate) {
            dateToString.add(date.toString());
        }

//        for (String s : dateToString) {
//
//            String value = dateLogs.getDateLogs().get(s).toString();
//            sortDateLogs.getDateLogs().put(s, value);
//        }

        return sortDateLogs;
    }


    public List<Date> stringToDate(List<String> mylist) throws ParseException {

        List<Date> formatDate = new ArrayList<>();

        DateFormat formatter = new SimpleDateFormat("MMM  dd HH:mm:ss", Locale.ENGLISH);
        Date date;

        for (int i = 0; i < mylist.size(); i++) {
           // System.out.println(s);
           // date = formatter.parse(list.get(i));
           date = formatter.parse(mylist.get(i));
            formatDate.add(date);
        }
        Collections.sort(formatDate);

        return formatDate;
    }


}
