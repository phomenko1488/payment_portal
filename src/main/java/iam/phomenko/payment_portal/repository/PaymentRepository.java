package iam.phomenko.payment_portal.repository;

import iam.phomenko.payment_portal.entity.Account;
import iam.phomenko.payment_portal.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment getPaymentById(Long id);

    List<Payment> getPaymentsBySourceAccountAndDestinationAccount(Account source, Account destination);
}