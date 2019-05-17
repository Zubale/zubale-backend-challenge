package com.zubale.challenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.zubale.challenge.entities.Quote;
import com.zubale.challenge.repositories.QuoteRepository;

/**
 * 
 * @author maagapi
 *
 */
@Service
public class QuoteService {
	@Autowired
	private QuoteRepository quoteRepository;

	public Page<Quote> findAll(int page, int size) {
		return quoteRepository.findAll(PageRequest.of(page, size));
	}

	public Quote create(Quote quote) {
		return quoteRepository.save(quote);
	}

}
