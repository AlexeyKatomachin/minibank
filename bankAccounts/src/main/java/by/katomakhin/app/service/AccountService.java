package by.katomakhin.app.service;

import by.katomakhin.app.dto.CreateAccountDto;
import by.katomakhin.app.dto.DepositDto;
import by.katomakhin.app.dto.TransferDto;
import by.katomakhin.app.dto.WithdrawDto;

public interface AccountService {
    void createAccount(CreateAccountDto createAccountDto);

    void deposit(DepositDto depositDto);

    void withdraw(WithdrawDto withdrawDto);

    void transfer(TransferDto transferDto);
}
