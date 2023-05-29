package ru.job4j.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @Setter
    @Getter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_hist_id")
    private List<History> ownerHistories = new ArrayList<>();
    @Setter
    @Getter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_hist_id")
    private List<History> carHistories = new ArrayList<>();
}
