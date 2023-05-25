package ru.job4j.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "engine")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Setter
    @Getter
    private int id;
    @Setter
    @Getter
    private String name;
}
