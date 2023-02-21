package com.mscourse.cards.module.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mscourse.cards.module.model.entities.Card;

public interface CardsRepository extends JpaRepository<Card, Integer> {
    
    Optional<List<Card>> findByRentLessThanEqual(BigDecimal rent);
    
    Optional<List<Card>> findByClient(String client);
    
}
