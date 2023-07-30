package pro.sky.telegrambot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.configuration.TelegramBotConfiguration;
import pro.sky.telegrambot.entity.NotificationTask;
import pro.sky.telegrambot.service.CommandHandlerService;
import pro.sky.telegrambot.service.NotificationTaskService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
public class CommandHandlerServiceImpl implements CommandHandlerService {

    private final NotificationTaskService notificationTaskService;
    private final TelegramBotConfiguration configuration;

    private static final String START = "/start";

    private final Pattern pattern = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)");
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    @Override
    public String handleCommand(Long chatId, String command) {
        switch (command) {
            case START:
                return configuration.getStartMsg();
            default:
                return handleCreateTaskCommand(chatId, command);
        }
    }

    private String handleCreateTaskCommand(Long chatId, String command) {
        if (command != null) {
            Matcher matcher = pattern.matcher(command);
            if (matcher.find()) {
                LocalDateTime dateTime = parseNotificationDate(matcher.group(1));
                if (dateTime == null) {
                    return configuration.getErrorMsg();
                } else {
                    String text = matcher.group(3);
                    notificationTaskService.save(new NotificationTask(chatId, text, dateTime));
                    return configuration.getSuccessMsg();
                }
            }
        }
        return configuration.getErrorMsg();
    }

    private LocalDateTime parseNotificationDate(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
