package iam.phomenko.payment_portal.controller;

import iam.phomenko.payment_portal.dto.Response;
import iam.phomenko.payment_portal.dto.clientcreate.ClientCreateRequest;
import iam.phomenko.payment_portal.dto.clientcreate.ClientCreateResponse;
import iam.phomenko.payment_portal.entity.Account;
import iam.phomenko.payment_portal.entity.Client;
import iam.phomenko.payment_portal.service.AccountService;
import iam.phomenko.payment_portal.service.ClientService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@ResponseBody
@RequestMapping("/api/v1/clients")
public class ClientController {
    private final ClientService clientService;
    private final AccountService accountService;

    @Autowired
    public ClientController(ClientService clientService, AccountService accountService) {
        this.clientService = clientService;
        this.accountService = accountService;
    }

    @GetMapping
    public List<Client> getAll() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAll(@PathVariable("id") String id) {
        if (!ControllersUtils.validID(id))
            return Response.validationError(String.format("Invalid id %s, try correct id", id));
        Client client = clientService.getOneById(Long.valueOf(id));
        if (client == null)
            return Response.validationError(String.format("Client with id %s does'nt exist", id));
        return Response.ok(client);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody ClientCreateRequest clientCreateRequest) {
        Client client = clientCreateRequest.toClient();
        clientService.save(client);
        List<Account> accounts = client.getAccounts();
        for (Account account : accounts) {
            accountService.save(account);
        }
        return Response.ok(ClientCreateResponse.fromClient(client), 201);
    }

    @GetMapping("/{id}/accounts")
    public ResponseEntity<Object> getClientAccounts(@PathVariable("id") String id) {
        if (!ControllersUtils.validID(id))
            return Response.validationError(String.format("Invalid id %s, try correct id", id));
        Client client = clientService.getOneById(Long.parseLong(id));
        if (client == null)
            return Response.error(String.format("Client with id %s doesn't exist", id));
        return Response.ok(client.getAccounts());

    }


}
