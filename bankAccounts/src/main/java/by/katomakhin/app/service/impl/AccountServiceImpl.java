package by.katomakhin.app.service.impl;

import by.katomakhin.app.dto.Account;
import by.katomakhin.app.dto.User;
import by.katomakhin.app.entity.AccountEntity;
import by.katomakhin.app.entity.TransactionEntity;
import by.katomakhin.app.entity.UserEntity;
import by.katomakhin.app.mapper.SimpleMapper;
import by.katomakhin.app.model.CreateAccountInModel;
import by.katomakhin.app.model.DepositInModel;
import by.katomakhin.app.model.TransferInModel;
import by.katomakhin.app.model.WithdrawInModel;
import by.katomakhin.app.repository.AccountRepository;
import by.katomakhin.app.repository.TransactionRepository;
import by.katomakhin.app.repository.UserRepository;
import by.katomakhin.app.service.AccountService;
import by.katomakhin.app.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final SimpleMapper simpleMapper;
    private final TransactionRepository transactionRepository;
    private final Validator validator;

    @Override
    @Transactional
    public void createAccount(CreateAccountInModel accountIn) {
        // TODO validation user and acc not null
        UserEntity userInEntity = simpleMapper.dtoToEntity(accountIn.getUser());
        Optional<UserEntity> userOpt = userRepository.findAll().stream()
                .filter(x -> x.getName().equals(accountIn.getUser().getName()))
                .findFirst();
        UserEntity userEntity;
        if (userOpt.isPresent()) {
            userEntity = userOpt.get();
        } else {
            userEntity = simpleMapper.dtoToEntity(accountIn.getUser());
            userRepository.save(userEntity);
        }
        userInEntity.getAccounts().forEach(acc -> {
            acc.setUsers(userEntity);
            accountRepository.save(acc);
        });
    }

    @Override
    @Transactional
    public void deposit(DepositInModel fillInAccountInfo) {
        validator.validatePin(fillInAccountInfo.getUserTo());
        Optional<UserEntity> userOpt = userRepository.findAll().stream()
                .filter(x -> x.getName().equals(fillInAccountInfo.getUserTo().getName()))
                .findFirst();
        if (userOpt.isPresent()){
            Optional<AccountEntity> accToOpt = userOpt.get().getAccounts().stream()
                    .filter(x -> x.getAccnumber().equals(fillInAccountInfo.getUserTo().getAccounts().get(0).getAccnumber()))
                    .findFirst();
            if (accToOpt.isPresent()){
                AccountEntity accTo = accToOpt.get();
                accTo.setAccvalue(accTo.getAccvalue().add(fillInAccountInfo.getValue()));

                TransactionEntity transactionAdd = new TransactionEntity();
                Date transactionTime = new Date();
                transactionAdd.setAccounts(accTo);
                transactionAdd.setTime(transactionTime);
                transactionAdd.setTrvalue(new BigDecimal(0).add(fillInAccountInfo.getValue()));

                accountRepository.save(accTo);
                transactionRepository.save(transactionAdd);
            }
        }
    }

    @Override
    @Transactional
    public void withdraw(WithdrawInModel withdrawInModel) {
        validator.validatePin(withdrawInModel.getUserTo());
        Optional<UserEntity> userOpt = userRepository.findAll().stream()
                .filter(x -> x.getName().equals(withdrawInModel.getUserTo().getName()))
                .findFirst();
        if (userOpt.isPresent()){
            Optional<AccountEntity> accToOpt = userOpt.get().getAccounts().stream()
                    .filter(x -> x.getAccnumber().equals(withdrawInModel.getUserTo().getAccounts().get(0).getAccnumber()))
                    .findFirst();
            if (accToOpt.isPresent()){
                AccountEntity accTo = accToOpt.get();
                accTo.setAccvalue(accTo.getAccvalue().subtract(withdrawInModel.getValue()));

                TransactionEntity transactionAdd = new TransactionEntity();
                Date transactionTime = new Date();
                transactionAdd.setAccounts(accTo);
                transactionAdd.setTime(transactionTime);
                transactionAdd.setTrvalue(new BigDecimal(0).subtract(withdrawInModel.getValue()));

                accountRepository.save(accTo);
                transactionRepository.save(transactionAdd);
            }
        }
    }

    @Override
    @Transactional
    public void transfer(TransferInModel transferInModel) {
        User userFrom = transferInModel.getUserFrom();
        validator.validatePin(userFrom);
        Account accTo = transferInModel.getAccTo();
        Optional<UserEntity> userOpt = userRepository.findAll().stream()
                .filter(x -> x.getName().equals(userFrom.getName()))
                .findFirst();
        if (userOpt.isPresent()){
            Optional<AccountEntity> accOpt = userOpt.get().getAccounts().stream()
                    .filter(x -> x.getAccnumber().equals(userFrom.getAccounts().get(0).getAccnumber()))
                    .findFirst();
            if (accOpt.isPresent()){
                accOpt.get().setAccvalue(accOpt.get().getAccvalue().subtract(transferInModel.getValue()));
                Optional<AccountEntity> accToDB = accountRepository.findAll().stream()
                        .filter(acc -> acc.getAccnumber().equals(accTo.getAccnumber()))
                        .findFirst();
                if (accToDB.isPresent()){
                    accToDB.get().setAccvalue(accToDB.get().getAccvalue().add(transferInModel.getValue()));
                    accountRepository.save(accToDB.get());
                    accountRepository.save(accOpt.get());

                    TransactionEntity transactionSub = new TransactionEntity();
                    TransactionEntity transactionAdd = new TransactionEntity();
                    Date transactionTime = new Date();
                    transactionSub.setAccounts(accOpt.get());
                    transactionSub.setTime(transactionTime);
                    transactionSub.setTrvalue(new BigDecimal(0).subtract(transferInModel.getValue()));
                    transactionAdd.setAccounts(accToDB.get());
                    transactionAdd.setTime(transactionTime);
                    transactionAdd.setTrvalue(new BigDecimal(0).add(transferInModel.getValue()));

                    transactionRepository.save(transactionSub);
                    transactionRepository.save(transactionAdd);
                }
            }
        }
    }
}
