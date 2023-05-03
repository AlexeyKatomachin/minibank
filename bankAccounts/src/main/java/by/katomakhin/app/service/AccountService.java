package by.katomakhin.app.service;

import by.katomakhin.app.model.CreateAccountInModel;
import by.katomakhin.app.model.DepositInModel;
import by.katomakhin.app.model.TransferInModel;
import by.katomakhin.app.model.WithdrawInModel;
import org.springframework.stereotype.Component;

public interface AccountService {
    void createAccount(CreateAccountInModel accountIn);

    void deposit(DepositInModel fillInAccountInfo);

    void withdraw(WithdrawInModel withdrawInModel);

    void transfer(TransferInModel transferInModel);
}
