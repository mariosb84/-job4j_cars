package ru.job4j.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "owners")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Setter
    @Getter
    private int id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "auto_user_id")
    private User user;

}
