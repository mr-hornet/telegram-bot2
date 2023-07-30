package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.entity.NotificationTask;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationTaskRepository extends JpaRepository<NotificationTask, Long> {

    /**
     * Получить все напоминание на определенное время
     *
     * @param localDateTime время поиска
     * @return список напоминаний
     */
    List<NotificationTask> findAllByLocalDateTime(LocalDateTime localDateTime);
}
