package utils;

public class Consts {
    public static final String PATH = "C://logs";
    public static final String[] SEVERITY_TYPES = {"DEBUG", "INFO", "WARN", "ERROR", "FATAL", "OFF", "TRACE"};

    public static final String DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}[,]\\d{3}";
    public static final String LIB_REGEX = "\\[[A-Za-z-0-9]+(?:\\.[A-Za-z-0-9]+)*\\]";
}
