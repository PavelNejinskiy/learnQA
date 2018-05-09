import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FieScaner {

   static DateLogs dateLogs = new DateLogs();

    public void directory(File dir) throws IOException {

        for (File file : dir.listFiles()) {
//            if (file.isDirectory()) {
//                directory(file, word);
//            }
            if (!file.isDirectory()) {
                //   System.out.println("Берем в работу фаил: " + file.getAbsolutePath());

               // if (file.getAbsolutePath().endsWith(".log")) {
                       System.out.println( "Этот фаил будем обрабатывать: " + file.getAbsolutePath());
                    read(file.getAbsolutePath());
              //  }
            }
        }

    }

    public void read(String path) throws IOException {

        BufferedReader fileReader = new BufferedReader(new FileReader(path));
        String timeLogs;
        String idLogs;
        String keyWord = "Captured transactions";
        String keyWord2 = ":";
        List<String> list = new ArrayList<>();

        try {
            String line;
            while ((line = fileReader.readLine()) != null) {
                list.add(line);
            }
        } finally {
            fileReader.close();
        }

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).contains(keyWord)) {
                i++;
                Pattern p = Pattern.compile(list.get(i));
                Matcher m = p.matcher(" gw");
                timeLogs = "" + m.start() + m.end();
                idLogs = list.get(i).substring(list.get(i).indexOf(keyWord2), list.get(i).length());
                dateLogs.result.put(timeLogs, dateLogs.result.get(timeLogs) + idLogs);

            }
        }

    }

    public static void main(String[] args) throws IOException {

        File path = new File( "G:\\QA\\Logs\\");

        FieScaner scaner = new FieScaner();

        scaner.directory(path);

       // System.out.println(dateLogs.result.get(0));
    }
}
