package by.katomakhin.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int pin;

    @OneToMany(targetEntity = AccountEntity.class, fetch = FetchType.EAGER, mappedBy = "users")
    private List<AccountEntity> accounts;
}

