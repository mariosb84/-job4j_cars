package ru.job4j.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Setter
    @Getter
    private int id;
    @Setter
    @Getter
    private LocalDateTime startAt;
    @Setter
    @Getter
    private LocalDateTime endAt;

}
