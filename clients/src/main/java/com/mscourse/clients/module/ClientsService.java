package com.mscourse.clients.module;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mscourse.clients.module.model.entities.Client;

@Service
public class ClientsService {
    
    @Autowired
    private ClientsRepository repository;

    public Client save(Client client) {
        return this.repository.save(client);
    }

    public Client getClient(String cpf) {
        return this.repository.findByCpf(cpf).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found!"));
    }

    public List<Client> getClients() {
        return this.repository.findAll();
    }

    public Client update(String cpf, Client updatedClient) {
        return this.repository.findByCpf(cpf).map( client -> {
            updatedClient.setId(client.getId());
            return this.repository.save(updatedClient);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void delete(String cpf) {
        this.repository.findByCpf(cpf).map( client -> {
            this.repository.delete(client);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
