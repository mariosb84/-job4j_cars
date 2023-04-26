package ru.job4j.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
}
