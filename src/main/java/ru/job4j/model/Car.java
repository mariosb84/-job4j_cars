package ru.job4j.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "car")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Car {
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
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private Engine engine;
    @Setter
    @Getter
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner", joinColumns = {
            @JoinColumn(name = "car_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "owner_id", nullable = false, updatable = false)})
    private Set<Owner> owners = new HashSet<>();

}
