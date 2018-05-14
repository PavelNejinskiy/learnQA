import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SortByDate {


    public ArrayList<String> sort(DateLogs dateLogs) throws ParseException {

        ArrayList<String> list = new ArrayList<>();

        for (Map.Entry me : dateLogs.getDateLogs().entrySet()) {
            list.add((String) me.getKey());
        }
        sortDate(list);

       return list;

    }

    public static void sortDate(List<String> dateList)
    {
        Collections.sort(dateList, new Comparator<String>() {
            DateFormat dateFormat = new SimpleDateFormat("MMM  dd HH:mm:ss", Locale.ENGLISH);
            @Override
            public int compare(String date1, String date2) {
                try{
                    return  dateFormat.parse(date1).compareTo(dateFormat.parse(date2));
                }
                catch (ParseException e)
                {
                    throw new IllegalArgumentException(e);
                }
            }
        });
    }


}
