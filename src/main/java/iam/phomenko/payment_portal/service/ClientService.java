package iam.phomenko.payment_portal.service;

import iam.phomenko.payment_portal.entity.Client;
import iam.phomenko.payment_portal.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client getOneById(Long id) {

        return clientRepository.getClientById(id);
    }

    public void save(Client client) {
        clientRepository.save(client);
    }
}