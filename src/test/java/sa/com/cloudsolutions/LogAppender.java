package sa.com.cloudsolutions;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogAppender extends AppenderBase<ILoggingEvent> {

    public static List<ILoggingEvent> events = new ArrayList<>();

    @Override
    protected void append(ILoggingEvent eventObject) {
        events.add(eventObject);
    }

    public void clearLogs() {
        events.clear();
    }

    public static boolean hasMessage(Level level, String loggerName, String messageContent) {
        return events.stream()
                .anyMatch(event ->
                        (level == null || event.getLevel() == level) &&
                                (loggerName == null || event.getLoggerName().equals(loggerName)) &&
                                (messageContent == null || event.getFormattedMessage().contains(messageContent))
                );
    }

    public static List<ILoggingEvent> getMatchingLogs(Level level, String loggerName, String messageContent) {
        return events.stream()
                .filter(event ->
                        (level == null || event.getLevel() == level) &&
                                (loggerName == null || event.getLoggerName().equals(loggerName)) &&
                                (messageContent == null || event.getFormattedMessage().contains(messageContent))
                )
                .collect(Collectors.toList());
    }
}
