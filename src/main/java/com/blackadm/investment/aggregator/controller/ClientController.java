package com.blackadm.investment.aggregator.controller;

import com.blackadm.investment.aggregator.dto.CreateClientDto;
import com.blackadm.investment.aggregator.entity.Client;
import com.blackadm.investment.aggregator.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
}
