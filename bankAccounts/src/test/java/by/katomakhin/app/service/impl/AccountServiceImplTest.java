package by.katomakhin.app.service.impl;


import by.katomakhin.app.dto.CreateAccountDto;
import by.katomakhin.app.dto.DepositDto;
import by.katomakhin.app.dto.TransferDto;
import by.katomakhin.app.dto.WithdrawDto;
import by.katomakhin.app.entity.AccountEntity;
import by.katomakhin.app.entity.UserEntity;
import by.katomakhin.app.repository.AccountRepository;
import by.katomakhin.app.repository.TransactionRepository;
import by.katomakhin.app.repository.UserRepository;
import by.katomakhin.app.validator.Validator;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private Validator validator;
    private AccountServiceImpl accountService;

    @PostConstruct
    public void init() {
    }

    @Test
    void createAccount() {
        accountService = new AccountServiceImpl(accountRepository, userRepository, transactionRepository, validator);

        when(userRepository.findUserEntitiesByName(any())).thenReturn(getUserEntityMock());

        accountService.createAccount(getCreateAccountDtoMock());

        verify(userRepository).findUserEntitiesByName(any());
        verify(accountRepository).save(any());

        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(accountRepository);
        verifyNoMoreInteractions(transactionRepository);
        verifyNoMoreInteractions(validator);
    }

    @Test
    void createAccountAndUser() {
        accountService = new AccountServiceImpl(accountRepository, userRepository, transactionRepository, validator);

        when(userRepository.findUserEntitiesByName(any())).thenReturn(null);

        accountService.createAccount(getCreateAccountDtoMock());

        verify(userRepository).findUserEntitiesByName(any());
        verify(userRepository).save(any());
        verify(accountRepository).save(any());

        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(accountRepository);
        verifyNoMoreInteractions(transactionRepository);
        verifyNoMoreInteractions(validator);
    }

    @Test
    void deposit() {
        accountService = new AccountServiceImpl(accountRepository, userRepository, transactionRepository, validator);

        when(accountRepository.findAccountEntityByAccnumber(any())).thenReturn(getAccountEntityMock());

        accountService.deposit(getDepositDtoMock());

        verify(validator).validateAcc(any());
        verify(accountRepository).findAccountEntityByAccnumber(any());
        verify(accountRepository).save(any());
        verify(transactionRepository).save(any());

        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(accountRepository);
        verifyNoMoreInteractions(transactionRepository);
        verifyNoMoreInteractions(validator);
    }

    @Test
    void withdraw() {
        accountService = new AccountServiceImpl(accountRepository, userRepository, transactionRepository, validator);

        when(accountRepository.findAccountEntityByAccnumber(any())).thenReturn(getAccountEntityMock());

        accountService.withdraw(getWithdrawDtoMock());

        verify(validator).validatePin(any(), anyInt());
        verify(validator).validateAcc(any());
        verify(accountRepository).findAccountEntityByAccnumber(any());
        verify(accountRepository).save(any());
        verify(transactionRepository).save(any());

        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(accountRepository);
        verifyNoMoreInteractions(transactionRepository);
        verifyNoMoreInteractions(validator);
    }

    @Test
    void transfer() {
        accountService = new AccountServiceImpl(accountRepository, userRepository, transactionRepository, validator);

        when(accountRepository.findAccountEntityByAccnumber(any())).thenReturn(getAccountEntityMock());

        accountService.transfer(getTransferDtoMock());

        verify(validator).validatePin(any(), anyInt());
        verify(validator, atLeast(2)).validateAcc(any());
        verify(validator).validateAccBelong(any(), any());
        verify(accountRepository, atLeast(2)).findAccountEntityByAccnumber(any());
        verify(accountRepository, atLeast(2)).save(any());
        verify(transactionRepository, atLeast(2)).save(any());

        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(accountRepository);
        verifyNoMoreInteractions(transactionRepository);
        verifyNoMoreInteractions(validator);
    }

    private UserEntity getUserEntityMock() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Test");
        return userEntity;
    }

    private CreateAccountDto getCreateAccountDtoMock() {
        return CreateAccountDto.builder()
                .userName("Test")
                .build();
    }

    private AccountEntity getAccountEntityMock() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccvalue(new BigDecimal(0));
        return accountEntity;
    }

    private DepositDto getDepositDtoMock() {
        return DepositDto.builder()
                .value(new BigDecimal(0))
                .build();
    }

    private WithdrawDto getWithdrawDtoMock() {
        return WithdrawDto.builder()
                .value(new BigDecimal(0))
                .build();
    }

    private TransferDto getTransferDtoMock() {
        return TransferDto.builder()
                .value(new BigDecimal(0))
                .build();
    }
}