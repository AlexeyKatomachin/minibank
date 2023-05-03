package by.katomakhin.app.controller;

import by.katomakhin.app.model.CreateAccountInModel;
import by.katomakhin.app.model.DepositInModel;
import by.katomakhin.app.model.TransferInModel;
import by.katomakhin.app.model.WithdrawInModel;
import by.katomakhin.app.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("account")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/create")
    public void createAccount (@RequestBody CreateAccountInModel accountIn) {
        accountService.createAccount(accountIn);
    }

    @PostMapping("/deposit")
    public void deposit (@RequestBody DepositInModel depositInModel) {
        accountService.deposit(depositInModel);
    }

    @PostMapping("/withdraw")
    public void withdraw (@RequestBody WithdrawInModel withdrawInModel) {
        accountService.withdraw(withdrawInModel);
    }

    @PostMapping("/transfer")
    public void transfer (@RequestBody TransferInModel transferInModel) {
        accountService.transfer(transferInModel);
    }
}
