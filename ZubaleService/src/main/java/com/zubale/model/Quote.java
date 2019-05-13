package com.zubale.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Quote {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String quoteString;
	Long votes = 0l;

	public Quote(Long id,
	String quoteString,
	Long votes) {
		this.id = id;
		this.quoteString = quoteString;
		this.votes = votes;
	}
	
	public Quote() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getVotes() {
		return votes;
	}
	public void setVotes(Long votes) {
		this.votes = votes;
	}

	public String getQuoteString() {
		return quoteString;
	}

	public void setQuoteString(String quoteString) {
		this.quoteString = quoteString;
	}
	
	
}
