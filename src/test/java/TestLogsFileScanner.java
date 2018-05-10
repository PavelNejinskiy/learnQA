import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestLogsFileScanner {

    public static LogsFileScanner logsFileScanner = new LogsFileScanner();
    DateLogs dateLogs = new DateLogs();
    SortByDate sortByDate = new SortByDate();


    @Test
    public void testGeneralFunctional() throws IOException {

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

    @Test
    public void testSort()
    {
       ArrayList<String> list = new ArrayList<>();

        for (Map.Entry me : dateLogs.getDateLogs().entrySet()) {
            list.add((String) me.getKey());
        }

//       List<String> newList =  sortByDate.sort(list);
//
//        for (String s : newList) {
//            System.out.println(s);
//        }
    }
}
