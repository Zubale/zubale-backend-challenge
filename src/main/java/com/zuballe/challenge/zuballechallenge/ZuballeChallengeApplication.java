package com.zuballe.challenge.zuballechallenge;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.zuballe.challenge.zuballechallenge.model.Quote;
import com.zuballe.challenge.zuballechallenge.repositories.QuoteRepository;

@SpringBootApplication
public class ZuballeChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuballeChallengeApplication.class, args);
	}

	@Bean
	InitializingBean createQuotes(QuoteRepository quoteRepository) {
		return () -> {
			Quote quote = new Quote();
			quote.setQuote("Love is a serious mental disease.");
			quote.setAuthor("Plato");
			quote.setVotes(0);
			quoteRepository.save(quote);
			
			quote = new Quote();
			quote.setQuote("Get busy living or get busy dying");
			quote.setAuthor("Stephen King");
			quote.setVotes(1);
			quoteRepository.save(quote);
			
			quote = new Quote();
			quote.setQuote("Those who dare to fail miserably can achieve greatly");
			quote.setAuthor("John F. Kennedy");
			quote.setVotes(3);
			quoteRepository.save(quote);
			
			quote = new Quote();
			quote.setQuote("It is hard to fail, but it is worse never to have tried to succeed.");
			quote.setAuthor("Theodore Roosevelt");
			quote.setVotes(5);
			quoteRepository.save(quote);
		};
	}
}
