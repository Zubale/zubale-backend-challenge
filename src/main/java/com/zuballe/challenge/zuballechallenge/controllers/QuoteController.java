package com.zuballe.challenge.zuballechallenge.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zuballe.challenge.zuballechallenge.exceptions.ResourceNotFoundException;
import com.zuballe.challenge.zuballechallenge.model.Quote;
import com.zuballe.challenge.zuballechallenge.repositories.QuoteRepository;

@RestController
@RequestMapping("/api")
public class QuoteController {

	@Autowired
	private QuoteRepository quoteRepository;
	
	@GetMapping("/quotes")
	public List<Quote> getAllQuotes() {
		
		List<Quote> quotes = quoteRepository.findAllByOrderByVotesDesc();
		return quotes;
	}
	
	@GetMapping("/quotes/{id}")
	public ResponseEntity<Quote> getQuoteById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		
		Quote quote = quoteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quote does not exist!"));
		return ResponseEntity.ok().body(quote);
	}
	
	@PostMapping("/quotes")
	public Quote createQuote(@Valid @RequestBody Quote quote)  {
		return quoteRepository.save(quote);
	}
	
	@PutMapping("/quotes/{id}")
	public ResponseEntity<Quote> updateQuoteVote(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		Quote quote = quoteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quote does not exist!"));
		quote.setVotes(quote.getVotes() + 1);
		Quote updatedQuote = quoteRepository.save(quote);
		return ResponseEntity.ok(updatedQuote);
	}
	
}
