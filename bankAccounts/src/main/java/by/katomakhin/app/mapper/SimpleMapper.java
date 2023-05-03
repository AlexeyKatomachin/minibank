package by.katomakhin.app.mapper;

import by.katomakhin.app.dto.Account;
import by.katomakhin.app.dto.Transaction;
import by.katomakhin.app.dto.User;
import by.katomakhin.app.entity.AccountEntity;
import by.katomakhin.app.entity.TransactionEntity;
import by.katomakhin.app.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SimpleMapper {
    SimpleMapper INSTANCE = Mappers.getMapper(SimpleMapper.class);

    User entityToDto(UserEntity userEntity);

    Account entityToDto(AccountEntity accountEntity);

    Transaction entityToDto(TransactionEntity transactionEntity);

    UserEntity dtoToEntity(User user);

    AccountEntity dtoToEntity(Account account);

    TransactionEntity dtoToEntity(Transaction transaction);

    List<User> listUserEntityToDto(List<UserEntity> userEntity);

    List<Account> listAccountEntityToDto(List<AccountEntity> accountEntity);

    List<Transaction> listTransactionEntityToDto(List<TransactionEntity> transactionEntity);

    List<UserEntity> listUserDtoToEntity(List<User> users);

    List<AccountEntity> listAccountDtoToEntity(List<Account> accounts);

    List<TransactionEntity> listTransactionDtoToEntity(List<Transaction> transactions);
}
