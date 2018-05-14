import org.testng.annotations.Test;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TestLogsFileScanner {

    public static LogsFileScanner logsFileScanner = new LogsFileScanner();
    DateLogs dateLogs = new DateLogs();
    SortByDate sortByDate = new SortByDate();


    @Test
    public void testGeneralFunctional() throws IOException, ParseException {

        File path = new File("G:\\QA\\TestLogs\\");
        File path2 = new File("G:\\QA\\result\\testres.txt");

       logsFileScanner.toJSON(path,path2);

        assert (path2.exists());
    }

    @Test
    public void testContainWords() throws IOException {
        String[] words = {"Mar  5 02:39:38", "SForce-1284", "Mar  5 16:09:41", "O365-1059"};

        BufferedReader fileReader= new BufferedReader(new FileReader("G:\\QA\\result\\res.txt"));

        List<String> list = new ArrayList<>();

        int count = 0;

            String line;
            while ((line = fileReader.readLine()) != null) {
                list.add(line);
                for (int i = 0; i < words.length; i++) {
                   if (line.contains(words[i])) {count++;}
                }
        }
        fileReader.close();
        assert (count==4);
    }

    @Test //(dependsOnMethods = "testGeneralFunctional")
    public void testStringToDate() throws FileNotFoundException, ParseException, InterruptedException {

       ArrayList<String> listForSort = new ArrayList<>();

       listForSort.add("Mar  4 04:16:27");
       listForSort.add("Mar  4 04:59:26");
       listForSort.add("Mar  4 04:01:17");
       listForSort.add("Mar  4 04:31:16");
       listForSort.add("Feb  4 01:07:28");
       listForSort.add("Feb  4 03:07:24");
       listForSort.add("Mar  4 04:38:28");

        sortByDate.sortDate(listForSort);

        for (String sortedDate : listForSort) {
            System.out.println(sortedDate);
        }

    }
}
