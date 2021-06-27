package iam.phomenko.payment_portal.repository;

import iam.phomenko.payment_portal.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client getClientById(Long id);
}
