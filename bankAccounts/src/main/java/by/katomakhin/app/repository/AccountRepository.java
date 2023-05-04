package by.katomakhin.app.repository;

import by.katomakhin.app.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findAccountEntityByAccnumber(String accNumber);

    @Query(value = "SELECT * FROM ACCOUNTS a WHERE a.USERS_ID in (SELECT ID FROM USERS u WHERE u.NAME = :name)",
            nativeQuery = true)
    List<AccountEntity> findAccountsByUser(@Param("name") String userName);
}
