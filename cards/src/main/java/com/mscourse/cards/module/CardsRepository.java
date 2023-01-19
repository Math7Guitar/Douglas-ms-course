package com.mscourse.cards.module;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mscourse.cards.module.model.entities.Card;

public interface CardsRepository extends JpaRepository<Card, Integer> {

    Optional<Card> findByRentLessThanEqual(BigDecimal rent);
    
}
