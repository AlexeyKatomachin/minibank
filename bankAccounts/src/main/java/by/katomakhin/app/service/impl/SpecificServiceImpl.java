package by.katomakhin.app.service.impl;

import by.katomakhin.app.dto.User;
import by.katomakhin.app.entity.AccountEntity;
import by.katomakhin.app.entity.UserEntity;
import by.katomakhin.app.mapper.SimpleMapper;
import by.katomakhin.app.model.SpecificAccountTransactionsOutModel;
import by.katomakhin.app.model.SpecificUserAccountsOutModel;
import by.katomakhin.app.repository.AccountRepository;
import by.katomakhin.app.repository.UserRepository;
import by.katomakhin.app.service.SpecificService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SpecificServiceImpl implements SpecificService {
    public final UserRepository userRepository;
    public final AccountRepository accountRepository;
    public final SimpleMapper simpleMapper;

    @Override
    public SpecificUserAccountsOutModel fetchAllUserAccounts() {
        List<UserEntity> users = userRepository.findAll();
        List<AccountEntity> accounts = accountRepository.findAll();
        List<User> usersDto = simpleMapper.listUserEntityToDto(users);
        User newUser = new User();
        newUser.setName("kakui");
        newUser.setPin("1234");
        UserEntity userEntity = simpleMapper.dtoToEntity(newUser);
        userRepository.save(userEntity);
        users = userRepository.findAll();
        System.out.println(users.get(0).getAccounts());
        return SpecificUserAccountsOutModel.builder().users(simpleMapper.listUserEntityToDto(users)).build();
    }

    @Override
    public SpecificAccountTransactionsOutModel fetchAccountTransactions() {
        return null;
    }
}

