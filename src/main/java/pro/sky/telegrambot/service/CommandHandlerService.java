package pro.sky.telegrambot.service;

/**
 * Сервис для обработки команд бота
 */
public interface CommandHandlerService {
    /**
     * Обработать команду
     * @param chatId Идентификатор чата
     * @param command текст команды
     * @return ответ на команду
     */
    String handleCommand(Long chatId, String command);
}
