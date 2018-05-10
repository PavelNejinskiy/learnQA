import com.google.gson.Gson;

import java.io.*;
import java.util.*;
import java.util.Map;


public class LogsFileScanner {

    public static DateLogs dateLogs = new DateLogs();

    public void directory(File dir) throws IOException {

        for (File file : dir.listFiles()) {

            if (!file.isDirectory()) {

                read(file.getAbsolutePath());

            }
        }

    }

    public void read(String path) throws IOException {

        BufferedReader fileReader = new BufferedReader(new FileReader(path));
        String timeLogs;
        String idLogs;
        String keyWord = "Captured transactions";
        String keyWord1 = "Captured transactions: None";
        String keyWord2 = "transaction";
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

            if (list.get(i).contains(keyWord) && i + 1 < list.size() && !list.get(i).contains(keyWord1)) {
                i++;

                if (list.get(i).length() > 15) {

                    timeLogs = list.get(i).substring(0, 15);

                    idLogs = list.get(i).substring(list.get(i).indexOf(keyWord2), list.get(i).length());

                    if (dateLogs.getDateLogs().size() <= 0) {
                        List<String> value = new ArrayList<>();
                        value.add(idLogs);
                        dateLogs.getDateLogs().put(timeLogs, value);
                    }

                    for (Map.Entry<String, List<String>> item : dateLogs.getDateLogs().entrySet()) {
                        String key = item.getKey();
                        if (key.equals(timeLogs)) {
                            List<String> value2 = item.getValue();
                            value2.add(idLogs);
                            dateLogs.getDateLogs().put(key, value2);
                        } else {
                            List<String> value = new ArrayList<>();
                            value.add(idLogs);
                            dateLogs.getDateLogs().put(timeLogs, value);
                        }

                    }

                }
            }
        }

    }

    public static void toJSON(File path1, File path2) throws IOException {
        LogsFileScanner scanner = new LogsFileScanner();

        scanner.directory(path1);

        String jsonInString = new Gson().toJson(dateLogs);

        BufferedWriter writer = new BufferedWriter(new FileWriter(path2));

        writer.write(jsonInString);
        writer.close();
    }

    public static void main(String[] args) throws IOException {

        File path = new File("G:\\QA\\Logs\\");
        File path2 = new File("G:\\QA\\result\\res.txt");

        toJSON(path, path2);


    }
}
