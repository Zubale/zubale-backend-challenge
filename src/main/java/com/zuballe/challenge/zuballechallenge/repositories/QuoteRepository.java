package com.zuballe.challenge.zuballechallenge.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zuballe.challenge.zuballechallenge.model.Quote;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Long> {

	List<Quote> findAllByOrderByVotesDesc();
}
