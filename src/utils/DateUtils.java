package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class DateUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");

    public static long getMaxAndMinDateDifferenceInDays(List<String> stringDates) {
        List<LocalDateTime> localDateTimes = stringDates.stream()
                .map(s -> LocalDateTime.parse(s, formatter))
                .sorted(LocalDateTime::compareTo)
                .collect(Collectors.toList());

        LocalDateTime first = localDateTimes.get(0);
        LocalDateTime last = localDateTimes.get(localDateTimes.size() - 1);
        return first.until(last, ChronoUnit.DAYS);


    }
}
