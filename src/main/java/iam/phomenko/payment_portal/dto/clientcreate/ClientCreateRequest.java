package iam.phomenko.payment_portal.dto.clientcreate;

import iam.phomenko.payment_portal.dto.accountcreate.AccountCreateRequest;
import iam.phomenko.payment_portal.entity.Account;
import iam.phomenko.payment_portal.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ClientCreateRequest {
    private String first_name;
    private String last_name;
    private List<AccountCreateRequest> accounts;

    private List<Account> requestsToAccountsList(List<AccountCreateRequest> accounts, Client owner) {
        return accounts.stream().map(AccountCreateRequest::toAccount).filter(account -> account.getBalance().doubleValue() >= 0).peek(account -> account.setOwner(owner)).collect(Collectors.toList());
    }

    public Client toClient() {
        Client client = new Client();
        client.setLastName(this.getLast_name());
        client.setFirstName(this.getFirst_name());
        client.setAccounts(requestsToAccountsList(this.getAccounts(), client));
        return client;
    }
}
