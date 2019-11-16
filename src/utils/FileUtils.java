package utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static utils.Consts.PATH;

public class FileUtils {
    public static boolean pathExists(String path) {
        return Files.exists(Paths.get(PATH));
    }

    public static List<File> getLogFilesFromDirectory(File file) {
        File[] files = file.listFiles();
        return Arrays.stream(files)
                .filter(f -> f.getName().endsWith(".log"))
                .sorted((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()))
                .collect(Collectors.toList());
    }
}
