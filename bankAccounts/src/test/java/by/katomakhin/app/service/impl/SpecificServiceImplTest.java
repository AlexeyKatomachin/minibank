package by.katomakhin.app.service.impl;

import by.katomakhin.app.dto.SpecificAccountTransactionsRequestDto;
import by.katomakhin.app.dto.SpecificUserAccountsRequestDto;
import by.katomakhin.app.repository.AccountRepository;
import by.katomakhin.app.repository.TransactionRepository;
import by.katomakhin.app.validator.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecificServiceImplTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private Validator validator;

    @Test
    void fetchAllUserAccounts() {
        SpecificServiceImpl specificService = new SpecificServiceImpl(accountRepository, transactionRepository, validator);

        when(accountRepository.findAccountsByUser(any())).thenReturn(new ArrayList<>());

        specificService.fetchAllUserAccounts(getSpecificUserAccountsRequestDtoMock());

        verify(validator).validatePin(any(), anyInt());
        verify(accountRepository).findAccountsByUser(any());

        verifyNoMoreInteractions(accountRepository);
        verifyNoMoreInteractions(transactionRepository);
        verifyNoMoreInteractions(validator);
    }

    @Test
    void fetchAccountTransactions() {
        SpecificServiceImpl specificService = new SpecificServiceImpl(accountRepository, transactionRepository, validator);

        when(transactionRepository.findTransactionsByAccount(any())).thenReturn(new ArrayList<>());

        specificService.fetchAccountTransactions(getSpecificAccountTransactionsRequestDtoMock());

        verify(validator).validatePin(any(), anyInt());
        verify(validator).validateAccBelong(any(), any());
        verify(transactionRepository).findTransactionsByAccount(any());

        verifyNoMoreInteractions(accountRepository);
        verifyNoMoreInteractions(transactionRepository);
        verifyNoMoreInteractions(validator);
    }

    private SpecificUserAccountsRequestDto getSpecificUserAccountsRequestDtoMock() {
        return SpecificUserAccountsRequestDto.builder().build();
    }

    private SpecificAccountTransactionsRequestDto getSpecificAccountTransactionsRequestDtoMock() {
        return SpecificAccountTransactionsRequestDto.builder().build();
    }
}