package by.katomakhin.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date time;
    private BigDecimal trvalue;

    @ManyToOne(targetEntity = AccountEntity.class, fetch = FetchType.EAGER)
    private AccountEntity accounts;
}
