package ru.job4j.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "auto_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Setter
    @Getter
    private int id;
    @Setter
    @Getter
    private String login;
    @Setter
    @Getter
    private String password;
}
