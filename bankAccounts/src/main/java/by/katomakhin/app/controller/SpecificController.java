package by.katomakhin.app.controller;

import by.katomakhin.app.model.SpecificAccountTransactionsOutModel;
import by.katomakhin.app.model.SpecificUserAccountsOutModel;
import by.katomakhin.app.service.SpecificService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("specific")
public class SpecificController {
    private final SpecificService specificService;

    @GetMapping("/accounts")
    public SpecificUserAccountsOutModel fetchAllUserAccounts() {
        return specificService.fetchAllUserAccounts();
    }

    @GetMapping("/transactions")
    public SpecificAccountTransactionsOutModel fetchAccountTransactions(String accountNumber) {
        return specificService.fetchAccountTransactions();
    }
}
