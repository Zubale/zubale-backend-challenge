package com.zubale.challenge.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.zubale.challenge.entities.Quote;
import com.zubale.challenge.error.ResourceNotFoundException;
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

	public Quote findById(int id) {
		return quoteRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("The requested quote %d doesn't exists ", id)));
	}

	public Quote create(Quote quote) {
		return quoteRepository.save(quote);
	}

	public Quote update(int id, Quote quote) {
		Optional<Quote> optional = quoteRepository.findById(id);
		if (optional.isPresent()) {
			return quoteRepository.save(quote);
		} else {
			throw new ResourceNotFoundException(
					String.format("Quote %d cannot be updated because it doesn't exists ", id));
		}
	}

	public Quote vote(int id) {
		Optional<Quote> optional = quoteRepository.findById(id);
		if (optional.isPresent()) {
			Quote quote = optional.get();
			quote.setLikes(quote.getLikes() + 1);
			return quoteRepository.save(quote);
		} else {
			throw new ResourceNotFoundException(
					String.format("Quote %d cannot be voted because it doesn't exists ", id));
		}
	}

}
