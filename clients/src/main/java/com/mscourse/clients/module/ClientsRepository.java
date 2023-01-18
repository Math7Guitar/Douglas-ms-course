package com.mscourse.clients.module;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mscourse.clients.module.model.entities.Client;

public interface ClientsRepository extends JpaRepository<Client, String>{

    Optional<Client> findByCpf(String cpf);
    
}
