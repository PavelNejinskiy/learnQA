import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class DateLogs {

    public ConcurrentHashMap<String, List<String>> dateLogs = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String, List<String>> getDateLogs() {
        return dateLogs;
    }

}
