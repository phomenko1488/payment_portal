package iam.phomenko.payment_portal.repository;

import iam.phomenko.payment_portal.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account getAccountById(Long id);
}
