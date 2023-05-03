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
        return SpecificUserAccountsOutModel.builder().users(simpleMapper.listUserEntityToDto(users)).build();
    }

    @Override
    public SpecificAccountTransactionsOutModel fetchAccountTransactions() {
        List<UserEntity> users = userRepository.findAll();
        return SpecificAccountTransactionsOutModel.builder().users(simpleMapper.listUserEntityToDto(users)).build();
    }
}

