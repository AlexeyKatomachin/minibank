package by.katomakhin.app.service;

import by.katomakhin.app.dto.SpecificAccountTransactionsRequestDto;
import by.katomakhin.app.dto.SpecificAccountTransactionsResponseDto;
import by.katomakhin.app.dto.SpecificUserAccountsRequestDto;
import by.katomakhin.app.dto.SpecificUserAccountsResponseDto;

public interface SpecificService {
    SpecificUserAccountsResponseDto fetchAllUserAccounts(SpecificUserAccountsRequestDto requestDto);

    SpecificAccountTransactionsResponseDto fetchAccountTransactions(SpecificAccountTransactionsRequestDto requestDto);
}
