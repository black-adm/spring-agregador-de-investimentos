package com.blackadm.investment.aggregator.controller;

import com.blackadm.investment.aggregator.dto.CreateClientDto;
import com.blackadm.investment.aggregator.dto.UpdateClientDto;
import com.blackadm.investment.aggregator.entity.Client;
import com.blackadm.investment.aggregator.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody CreateClientDto createClientDto) {
        var clientId = clientService.createClient(createClientDto);

        return ResponseEntity.created(URI.create("/v1/clients/" + clientId.toString())).build();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> findClientById(@PathVariable("clientId") String clientId) {
        var client = clientService.getClientById(clientId);

        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<Client>> listClients() {
        var clients = clientService.getAllClients();

        return ResponseEntity.ok(clients);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Void> editClient(@PathVariable("clientId") String clientId,
                                           @RequestBody UpdateClientDto updateClientDto) {
        clientService.updateClient(clientId, updateClientDto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClientById(@PathVariable("clientId") String clientId) {
        clientService.deleteClient(clientId);

        return ResponseEntity.noContent().build();
    }
}
