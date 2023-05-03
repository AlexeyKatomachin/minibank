package by.katomakhin.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accnumber;
    private BigDecimal accvalue;

    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    private UserEntity users;
    @OneToMany(targetEntity = TransactionEntity.class, fetch = FetchType.EAGER, mappedBy = "accounts")
    private List<TransactionEntity> transactions;
}
