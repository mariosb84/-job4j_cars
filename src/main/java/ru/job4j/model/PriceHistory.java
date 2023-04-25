package ru.job4j.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRICE_HISTORY")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Setter
    @Getter
    private int id;
    @Setter
    @Getter
    private int before;
    @Setter
    @Getter
    private int after;
    @Setter
    @Getter
    private LocalDateTime created;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private List<Post> posts = new ArrayList<>();
}
