package ru.job4j.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Setter
    @Getter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_hist_id")
    private List<History> ownerHistories = new ArrayList<>();

}
