package ru.job4j.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "photo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Setter
    @Getter
    private int id;
    @Setter
    @Getter
    private byte[] photo;
}
