package com.mscourse.cards.module.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mscourse.cards.module.model.entities.ClientCard;

public interface ClientCardRepository extends JpaRepository<ClientCard, Integer> {
    
}
