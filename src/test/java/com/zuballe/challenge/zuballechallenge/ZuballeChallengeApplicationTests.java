package com.zuballe.challenge.zuballechallenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.zuballe.challenge.zuballechallenge.model.Quote;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZuballeChallengeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ZuballeChallengeApplicationTests {

	@Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/api";
    }
    
	@Test
	public void contextLoads() {
	}

	@Test
    public void testGetAllQuotes() {
         HttpHeaders headers = new HttpHeaders();
         HttpEntity<String> entity = new HttpEntity<String>(null, headers);

         ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/quotes",
         HttpMethod.GET, entity, String.class);
         
         assertNotNull(response.getBody());
         assertTrue(response.getStatusCode().is2xxSuccessful());
    }
	
	@Test
    public void testGetQuoteById() {
		Quote quote = restTemplate.getForObject(getRootUrl() + "/quotes/1", Quote.class);
		System.out.println(quote);
		assertEquals("Plato", quote.getAuthor());
		assertEquals(new Integer(0), quote.getVotes());
    }
	
	@Test
    public void testSaveQuote() {
		Quote quote = new Quote();
		quote.setQuote("Testing Quote Application");
		quote.setAuthor("Me");
		quote.setVotes(10);
		
		ResponseEntity<Quote> responseEntity = restTemplate.postForEntity(getRootUrl() + "/quotes", quote, Quote.class);
		assertNotNull(responseEntity);
		assertNotNull(responseEntity.getBody());
		assertEquals("Me", responseEntity.getBody().getAuthor());
		
    }
}
