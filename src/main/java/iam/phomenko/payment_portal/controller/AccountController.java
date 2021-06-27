package iam.phomenko.payment_portal.controller;

import iam.phomenko.payment_portal.dto.Response;
import iam.phomenko.payment_portal.entity.Account;
import iam.phomenko.payment_portal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return Response.ok(accountService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable("id") String id) {
        if (ControllersUtils.validID(id))
            return Response.validationError(String.format("Invalid id %s, try correct id", id));
        Account account = accountService.getOneById(Long.valueOf(id));
        if (account == null)
            return Response.error(String.format("Account with id %s doesn't exist", id));
        return Response.ok(account);
    }
}