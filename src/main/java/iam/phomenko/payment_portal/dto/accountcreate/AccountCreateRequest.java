package iam.phomenko.payment_portal.dto.accountcreate;

import iam.phomenko.payment_portal.entity.Account;
import iam.phomenko.payment_portal.entity.AccountType;
import iam.phomenko.payment_portal.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AccountCreateRequest {
    private Long account_num;
    private String account_type;
    private BigDecimal balance;
    private Client owner;

    public Account toAccount() {
        Account account = new Account();
        account.setNumber(this.getAccount_num());
        account.setBalance(this.getBalance());
        account.setOwner(this.getOwner());
        account.setAccountType(AccountType.fromString(account_type));
        return account;
    }
}
