package by.katomakhin.app.controller;

import by.katomakhin.app.dto.CreateAccountDto;
import by.katomakhin.app.dto.DepositDto;
import by.katomakhin.app.dto.TransferDto;
import by.katomakhin.app.dto.WithdrawDto;
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
    public void createAccount(@RequestBody CreateAccountDto createAccountDto) {
        accountService.createAccount(createAccountDto);
    }

    @PostMapping("/deposit")
    public void deposit(@RequestBody DepositDto depositDto) {
        accountService.deposit(depositDto);
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestBody WithdrawDto withdrawDto) {
        accountService.withdraw(withdrawDto);
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferDto transferDto) {
        accountService.transfer(transferDto);
    }
}
