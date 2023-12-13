package com.blackadm.investment.aggregator.service;

import com.blackadm.investment.aggregator.dto.CreateClientDto;
import com.blackadm.investment.aggregator.entity.Client;
import com.blackadm.investment.aggregator.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public UUID createClient(CreateClientDto createClientDto) {
        var entity = new Client(
                UUID.randomUUID(),
                createClientDto.nickname(),
                createClientDto.email(),
                createClientDto.password(),
                Instant.now(),
                null
                );

        var data = clientRepository.save(entity);
        return data.getClientId();
    }

    public Optional<Client> getClientById(String clientId) {
        return clientRepository.findById(UUID.fromString(clientId));
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
