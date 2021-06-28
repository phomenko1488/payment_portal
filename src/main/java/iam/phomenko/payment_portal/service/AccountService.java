package iam.phomenko.payment_portal.service;

import iam.phomenko.payment_portal.entity.Account;
import iam.phomenko.payment_portal.entity.AccountType;
import iam.phomenko.payment_portal.entity.Client;
import iam.phomenko.payment_portal.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Account getOneById(Long id) {
        return Optional.of(accountRepository.getAccountById(id)).orElse(null);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }
}
