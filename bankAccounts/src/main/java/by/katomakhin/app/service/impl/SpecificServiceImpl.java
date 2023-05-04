package by.katomakhin.app.service.impl;

import by.katomakhin.app.dto.*;
import by.katomakhin.app.entity.AccountEntity;
import by.katomakhin.app.entity.TransactionEntity;
import by.katomakhin.app.repository.AccountRepository;
import by.katomakhin.app.repository.TransactionRepository;
import by.katomakhin.app.service.SpecificService;
import by.katomakhin.app.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SpecificServiceImpl implements SpecificService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final Validator validator;

    @Override
    public SpecificUserAccountsResponseDto fetchAllUserAccounts(SpecificUserAccountsRequestDto requestDto) {
        validator.validatePin(requestDto.getUserName(), requestDto.getUserPin());

        final List<UserAccInfoDto> userAccInfoDto = new ArrayList<>();
        List<AccountEntity> userAccounts = accountRepository.findAccountsByUser(requestDto.getUserName());
        if (userAccounts != null) {
            userAccounts.forEach(acc -> userAccInfoDto.add(UserAccInfoDto.builder()
                    .number(acc.getAccnumber())
                    .value(acc.getAccvalue())
                    .build()));
        }

        return SpecificUserAccountsResponseDto.builder()
                .userName(requestDto.getUserName())
                .userAccInfos(userAccInfoDto)
                .build();
    }

    @Override
    public SpecificAccountTransactionsResponseDto fetchAccountTransactions(SpecificAccountTransactionsRequestDto requestDto) {
        validator.validatePin(requestDto.getUserName(), requestDto.getUserPin());
        validator.validateAccBelong(requestDto.getUserName(), requestDto.getAccNumber());

        final ArrayList<AccTransactionsInfoDto> accTransactionsInfo = new ArrayList<>();
        List<TransactionEntity> transactions = transactionRepository.findTransactionsByAccount(requestDto.getAccNumber());
        if (transactions != null) {
            transactions.forEach(tr -> accTransactionsInfo.add(AccTransactionsInfoDto.builder()
                    .time(tr.getTime())
                    .value(tr.getTrvalue())
                    .build()));
        }

        return SpecificAccountTransactionsResponseDto.builder()
                .userName(requestDto.getUserName())
                .accNumber(requestDto.getAccNumber())
                .accTransactionsInfo(accTransactionsInfo)
                .build();
    }
}

