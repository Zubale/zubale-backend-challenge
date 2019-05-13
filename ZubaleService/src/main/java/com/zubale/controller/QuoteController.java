package com.zubale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zubale.model.Quote;
import com.zubale.repo.QuoteRepo;


/**
 * 
 * This class support the rest API to support the quote and vote
 * @author rbhalla
 *
 */
@RestController
@RequestMapping("api/v1/")
public class QuoteController {
	
	@Autowired
	private QuoteRepo quoteRepository;

	/*
	 * the list endpoint lists all the quotes sorted in descending order
	 * based on the vote count
	 * http://<host>:port/api/v1/quotes
	 * Type of endpoint: GET
	 * 
	 * Sample response:
	 * 
	 * 	[
		    {
		        "id": 4,
		        "quoteString": "You can get everything in life you want if you will just help enough other people get what they want. – Zig Ziglar",
		        "votes": 3
		    },
		    {
		        "id": 3,
		        "quoteString": "Desire is the starting point of all achievement, not a hope, not a wish, but a keen pulsating desire which transcends everything. – Napoleon Hill",
		        "votes": 2
		    },
		    {
		        "id": 2,
		        "quoteString": "Failure is the condiment that gives success its flavor. – Truman Capote",
		        "votes": 1
		    },
		    {
		        "id": 5,
		        "quoteString": "I’ve missed more than 9000 shots in my career. I’ve lost almost 300 games. 26 times, I’ve been trusted to take the game winning shot and missed. I’ve failed over and over and over again in my life. And that is why I succeed. – Michael Jordan",
		        "votes": 0
		    },
		    {
		        "id": 6,
		        "quoteString": "You must be the change you want to see in the world. – Mahatma Gandhi",
		        "votes": 0
		    }
		]
	 */
	@RequestMapping(value = "quotes", method = RequestMethod.GET)
	public List<Quote> list() {
		return quoteRepository.findAll(Sort.by(Sort.Direction.DESC, "votes"));
	}

	/*
	 * the create method supports the  endpoint to add a quote
	 * 
	 * @param quote The new user quote
	 * http://<host>:port/api/v1/quotes
	 * Type of endpoint: POST
	 * 
	 * Sample quote:
	 * 
	 * headers:
	 * 	Content-Type: application/json
	 * 	Request JSON:
	 * 		{
	 * 			"quoteString": "This is a sample quote"
	 * 		}
	 */
	@RequestMapping(value = "quotes", method = RequestMethod.POST)
	public Quote create(@RequestBody Quote quote) {
		return quoteRepository.save(quote);
	}

	/*
	 * the update method supports the endpoint to update vote count for a quote
	 * 
	 * http://<host>:port/api/v1/vote/{1}
	 * Type of endpoint: PUT
	 * 
	 * @param id The id of the quote to vote on
	 * 
	 * 		http://localhost:8080/api/v1/vote/3
	 * 
	 * 	Sample response:
	 * 	{
	 * 	    "id": 3,
	 * 	    "quoteString": "Desire is the starting point of all achievement, not a hope, not a wish, but a keen pulsating desire which transcends everything. – Napoleon Hill",
	 * 	    "votes": 2
	 * 	}
	 */
	@RequestMapping(value = "vote/{id}", method = RequestMethod.PUT)
	public Quote update(@PathVariable Long id) {
		Quote quote = quoteRepository.getOne(id);
		quote.setVotes(quote.getVotes()+1);
		return quoteRepository.save(quote);
	}
}
