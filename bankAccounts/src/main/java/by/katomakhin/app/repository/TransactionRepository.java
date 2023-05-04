package by.katomakhin.app.repository;

import by.katomakhin.app.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    @Query(value = "SELECT * FROM TRANSACTIONS t WHERE t.ACCOUNTS_ID in (SELECT ID FROM ACCOUNTS a WHERE a.ACCNUMBER = :number)",
            nativeQuery = true)
    List<TransactionEntity> findTransactionsByAccount(@Param("number") String accNumber);
}
