package by.katomakhin.app.validator;

import by.katomakhin.app.entity.AccountEntity;
import by.katomakhin.app.entity.UserEntity;
import by.katomakhin.app.exceptions.AccountDoesNotBelongUserException;
import by.katomakhin.app.exceptions.AccountDoesNotExistException;
import by.katomakhin.app.exceptions.UserOrPinIncorrectException;
import by.katomakhin.app.repository.AccountRepository;
import by.katomakhin.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class Validator {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public void validatePin(String userName, int userPin) {
        UserEntity user = userRepository.findUserEntitiesByName(userName);
        if (user != null && user.getPin() == userPin) {
            return;
        }
        throw new UserOrPinIncorrectException(String.format("Incorrect user name (%s) or user pin.", userName));
    }

    public void validateAcc(String accNumber) {
        AccountEntity account = accountRepository.findAccountEntityByAccnumber(accNumber);
        if (account == null) {
            throw new AccountDoesNotExistException(String.format("Account %s doesn't exist", accNumber));
        }
    }

    public void validateAccBelong(String userName, String accNumber) {
        List<AccountEntity> accounts = accountRepository.findAccountsByUser(userName);
        if (accounts == null || accounts.stream().map(AccountEntity::getAccnumber).noneMatch(s -> s.equals(accNumber))) {
            throw new AccountDoesNotBelongUserException(String.format("Account %s doesn't belong to user %s", accNumber, userName));
        }
    }
}
