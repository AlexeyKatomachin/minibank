package by.katomakhin.app.service.impl;

import by.katomakhin.app.dto.CreateAccountDto;
import by.katomakhin.app.dto.DepositDto;
import by.katomakhin.app.dto.TransferDto;
import by.katomakhin.app.dto.WithdrawDto;
import by.katomakhin.app.entity.AccountEntity;
import by.katomakhin.app.entity.TransactionEntity;
import by.katomakhin.app.entity.UserEntity;
import by.katomakhin.app.repository.AccountRepository;
import by.katomakhin.app.repository.TransactionRepository;
import by.katomakhin.app.repository.UserRepository;
import by.katomakhin.app.service.AccountService;
import by.katomakhin.app.utils.AccountUtils;
import by.katomakhin.app.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final Validator validator;

    @Override
    @Transactional
    public void createAccount(CreateAccountDto createAccountDto) {
        UserEntity user = userRepository.findUserEntitiesByName(createAccountDto.getUserName());
        if (user == null) {
            user = new UserEntity();
            user.setPin(createAccountDto.getUserPin());
            user.setName(createAccountDto.getUserName());
            userRepository.save(user);
        }
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUsers(user);
        accountEntity.setAccvalue(createAccountDto.getAccValue());
        accountEntity.setAccnumber(AccountUtils.generateAccNumber(user.getName()));
        accountRepository.save(accountEntity);
    }

    @Override
    @Transactional
    public void deposit(DepositDto depositDto) {
        validator.validateAcc(depositDto.getAccNumber());
        AccountEntity account = accountRepository.findAccountEntityByAccnumber(depositDto.getAccNumber());
        account.setAccvalue(account.getAccvalue().add(depositDto.getValue()));

        TransactionEntity transaction = new TransactionEntity();
        transaction.setAccounts(account);
        transaction.setTime(new Date());
        transaction.setTrvalue(new BigDecimal(0).add(depositDto.getValue()));


        accountRepository.save(account);
        transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void withdraw(WithdrawDto withdrawDto) {
        validator.validatePin(withdrawDto.getUserName(), withdrawDto.getUserPin());
        validator.validateAcc(withdrawDto.getAccNumber());

        AccountEntity account = accountRepository.findAccountEntityByAccnumber(withdrawDto.getAccNumber());
        account.setAccvalue(account.getAccvalue().subtract(withdrawDto.getValue()));
        accountRepository.save(account);

        TransactionEntity transaction = new TransactionEntity();
        transaction.setAccounts(account);
        transaction.setTime(new Date());
        transaction.setTrvalue(new BigDecimal(0).subtract(withdrawDto.getValue()));
        transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void transfer(TransferDto transferDto) {
        validator.validatePin(transferDto.getUserName(), transferDto.getUserPin());
        validator.validateAcc(transferDto.getAccNumberFrom());
        validator.validateAcc(transferDto.getAccNumberTo());
        validator.validateAccBelong(transferDto.getUserName(), transferDto.getAccNumberFrom());

        AccountEntity accountFrom = accountRepository.findAccountEntityByAccnumber(transferDto.getAccNumberFrom());
        AccountEntity accountTo = accountRepository.findAccountEntityByAccnumber(transferDto.getAccNumberTo());

        accountFrom.setAccvalue(accountFrom.getAccvalue().subtract(transferDto.getValue()));
        accountTo.setAccvalue(accountTo.getAccvalue().add(transferDto.getValue()));


        Date date = new Date();
        TransactionEntity transactionFrom = new TransactionEntity();
        transactionFrom.setAccounts(accountFrom);
        transactionFrom.setTime(date);
        transactionFrom.setTrvalue(new BigDecimal(0).subtract(transferDto.getValue()));
        transactionRepository.save(transactionFrom);
        TransactionEntity transactionTo = new TransactionEntity();
        transactionTo.setAccounts(accountTo);
        transactionTo.setTime(date);
        transactionTo.setTrvalue(new BigDecimal(0).add(transferDto.getValue()));
        transactionRepository.save(transactionTo);
    }
}
