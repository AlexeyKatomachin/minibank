package by.katomakhin.app.controller;

import by.katomakhin.app.dto.SpecificAccountTransactionsRequestDto;
import by.katomakhin.app.dto.SpecificAccountTransactionsResponseDto;
import by.katomakhin.app.dto.SpecificUserAccountsRequestDto;
import by.katomakhin.app.dto.SpecificUserAccountsResponseDto;
import by.katomakhin.app.service.SpecificService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("specific")
public class SpecificController {
    private final SpecificService specificService;

    @GetMapping("/accounts")
    public SpecificUserAccountsResponseDto fetchAllUserAccounts(@RequestBody SpecificUserAccountsRequestDto requestDto) {
        return specificService.fetchAllUserAccounts(requestDto);
    }

    @GetMapping("/transactions")
    public SpecificAccountTransactionsResponseDto fetchAccountTransactions(@RequestBody SpecificAccountTransactionsRequestDto requestDto) {
        return specificService.fetchAccountTransactions(requestDto);
    }
}
