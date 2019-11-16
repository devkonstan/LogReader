import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static utils.Consts.*;
import static utils.DateUtils.getMaxAndMinDateDifferenceInDays;
import static utils.FileUtils.getLogFilesFromDirectory;
import static utils.FileUtils.pathExists;
import static utils.RegexUtils.getAllMatches;

public class App {

    public static void main(String[] args) {
        if (!pathExists(PATH)) {
            System.out.println("Specified path does not exist");
            System.exit(1);
        }

        File file = new File(PATH);
        if (!file.isDirectory()) {
            System.out.println("Path is not a directory");
            System.exit(1);
        }

        List<File> logFiles = getLogFilesFromDirectory(file);
        for (File f : logFiles) {
            LocalDateTime start = LocalDateTime.now();

            try {
                String fileContents = new String(Files.readAllBytes(Paths.get(f.getPath())));

                List<String> dates = getAllMatches(fileContents, DATE_REGEX);

                System.out.println("Difference between first and last log is: " + getMaxAndMinDateDifferenceInDays(dates));

                double allLogsCount = 0;
                double errorAndFatalLogsCount = 0;

                for (String severity : SEVERITY_TYPES) {
                    int occurances = fileContents.split(severity).length - 1;

                    allLogsCount += occurances;
                    if (severity.equals("ERROR") || severity.equals("FATAL")) {
                        errorAndFatalLogsCount++;
                    }

                    if (occurances > 0) {
                        System.out.println(f.getName() + ", " + severity + ": " + occurances);
                    }
                }

                System.out.println("Ratio between ERROR/FATAL and all: " + BigDecimal.valueOf(errorAndFatalLogsCount / allLogsCount)
                        .setScale(2, RoundingMode.HALF_UP).doubleValue());

                List<String> libs = getAllMatches(fileContents, LIB_REGEX);
                libs.removeIf(s -> !s.contains("."));

                System.out.println("Unique libs occurrences: " + libs.size());

            } catch (IOException e) {
                e.printStackTrace();

                System.out.println("File read error");
            }

            LocalDateTime finish = LocalDateTime.now();
            System.out.println("Log read elapsed time: " + start.until(finish, ChronoUnit.MILLIS) + " millis");
        }

    }

}
