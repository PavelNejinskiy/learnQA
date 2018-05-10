import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SortByDate {


    public List<String> sort(ArrayList<String> list){

        List<String> result = new ArrayList<>();
        List<Date> format = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd HH:mm:ss");

        for (String s : list) {
            try {
                Date date = formatter.parse(s);
                format.add(date);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Collections.sort(format);

        for (Date date : format) {
            result.add(date.toString());
        }

        return result;
    }


}
