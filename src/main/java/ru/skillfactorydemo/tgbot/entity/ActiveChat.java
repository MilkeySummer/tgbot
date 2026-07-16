package ru.skillfactorydemo.tgbot.entity;

import lombok.Data;

import jakarta.persistence.*;

@Data  //Для того чтобы не писать геттеры/сеттеры
@Entity // Данный класс является JPA сущностью
@Table(name = "ACTIVE_CHAT") //И хранится в таблице ACTIVE_CHAT
public class ActiveChat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "active_chat_id_seq")
    @SequenceGenerator(name = "active_chat_id_seq", sequenceName = "active_chat_id_seq", allocationSize = 1)
    private Long id; //Уникальный идентификатор в системе нашего бота

    @Column(name = "chat_id", nullable = false, unique = true)
    private Long chatId; //Уникальный идентификатор в системе Telegram
}
