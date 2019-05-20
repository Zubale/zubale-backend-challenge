/**
 * 
 */
package com.zubale.challenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zubale.challenge.entities.Quote;
import com.zubale.challenge.services.QuoteService;

/**
 * @author maagapi
 *
 */
@RestController
@RequestMapping("/quotes")
public class QuoteResource {
	@Autowired
	private QuoteService quoteService;

	@GetMapping
	public ResponseEntity<Page<Quote>> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		return new ResponseEntity<Page<Quote>>(quoteService.findAll(page, size), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Quote> findById(@PathVariable("id") int id) {
		return new ResponseEntity<Quote>(quoteService.findById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Quote> create(@RequestBody Quote quote) {
		return new ResponseEntity<Quote>(quoteService.create(quote), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Quote> update(@PathVariable("id") int id, @RequestBody Quote quote) {
		return new ResponseEntity<Quote>(quoteService.update(id, quote), HttpStatus.OK);
	}

	@PostMapping("/{id}/votes")
	public ResponseEntity<Quote> vote(@PathVariable("id") int id) {
		return new ResponseEntity<Quote>(quoteService.vote(id), HttpStatus.OK);
	}

}
