package by.katomakhin.app.service;

import by.katomakhin.app.model.SpecificAccountTransactionsOutModel;
import by.katomakhin.app.model.SpecificUserAccountsOutModel;
import org.springframework.stereotype.Component;

public interface SpecificService {
    SpecificUserAccountsOutModel fetchAllUserAccounts();

    SpecificAccountTransactionsOutModel fetchAccountTransactions();
}
