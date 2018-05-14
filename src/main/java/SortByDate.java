import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SortByDate {

    HashMap<String, List<String>> forSort = new LinkedHashMap<>();

   static ArrayList<String> list = new ArrayList<>();

    public HashMap sort(DateLogs dateLogs) throws ParseException, InterruptedException {

        List<String> temp = new ArrayList<>();
        temp.add("Here could be your advertisement :)");

        for (Map.Entry me : dateLogs.getDateLogs().entrySet()) {
            list.add((String) me.getKey());
        }
        sortDate(list);
       enterSpaceDate(list);

        for (int i = 0; i < list.size(); i++) {
            for (Map.Entry me : dateLogs.getDateLogs().entrySet()) {

                if (list.get(i).equals(me.getKey())) {

                    forSort.put(list.get(i), dateLogs.getDateLogs().get(list.get(i)));
                }

                if(list.get(i).equals("Two week separator"))
                {
                    forSort.put(list.get(i), temp);
                }
            }
        }
       return forSort;

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

    public static void enterSpaceDate(List<String> dateList) throws ParseException {

            DateFormat dateFormat = new SimpleDateFormat("MMM  dd HH:mm:ss", Locale.ENGLISH);

        Date date1 = dateFormat.parse(dateList.get(0));
        Date date2;

        for (int i = 1; i < dateList.size(); i++) {

            date2 = dateFormat.parse(dateList.get(i));
            if((date2.getTime() - date1.getTime())/1000 > 1_209_600){
                list.add(i, "Two week separator");

                date1 = date2;
            }
        }
    }
}
