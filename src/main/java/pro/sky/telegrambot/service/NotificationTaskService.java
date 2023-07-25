package pro.sky.telegrambot.service;

import pro.sky.telegrambot.entity.NotificationTask;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис по работе с напоминаниями
 */
public interface NotificationTaskService {
    /**
     * Сохранить напоминание
     * @param task напоминание
     */
    void save(NotificationTask task);

    /**
     * Удалить напоминание
     * @param task напоминание
     */
    void delete(NotificationTask task);

    /**
     * Получить все напоминание на определенное время
     * @param localDateTime время поиска
     * @return список напоминаний
     */
    List<NotificationTask> findAllByNotificationDateTime(LocalDateTime localDateTime);
}
