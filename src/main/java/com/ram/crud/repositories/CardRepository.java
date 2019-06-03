package com.ram.crud.repositories;

import com.ram.crud.entities.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

    Card findByCardNumber(String number);

    @Override <S extends Card> List<S> saveAll(Iterable<S> iterable);

}
