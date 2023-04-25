package ru.job4j.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auto_post")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Setter
    @Getter
    private int id;
    @Setter
    @Getter
    private String description;
    @Setter
    @Getter
    private LocalDateTime created;
    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "auto_user_id ")
    private User user;
}