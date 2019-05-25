package personal.teslafoil.zubale.quote.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import personal.teslafoil.zubale.quote.models.Quote;
import personal.teslafoil.zubale.quote.models.Vote;
import personal.teslafoil.zubale.quote.services.QuoteService;

@RunWith(MockitoJUnitRunner.class)
public class QuoteControllerTest {

    @InjectMocks
    QuoteController quoteController;

    @Mock
    QuoteService quoteService;

    @Test
    public void addQuoteSuccessfulTest() {
        String quoteText = "addQuoteSuccessfulTest";
        Quote quoteMock = mock(Quote.class);

        doReturn(quoteMock).when(quoteService).addQuote(quoteText);

        ResponseEntity<Quote> response = quoteController.addQuote(quoteText);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertSame(quoteMock, response.getBody());
    }

    @Test
    public void addQuoteFailTest() {
        String quoteText = "addQuoteFailTest";

        doReturn(null).when(quoteService).addQuote(quoteText);

        ResponseEntity<Quote> response = quoteController.addQuote(quoteText);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void voteQuoteSuccessfulTest() {
        Quote quoteMock = mock(Quote.class);
        Vote voteMock = mock(Vote.class);

        doReturn(quoteMock).when(quoteService).voteQuote(voteMock);

        ResponseEntity<Quote> response = quoteController.voteQuote(voteMock);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(quoteMock, response.getBody());
    }

    @Test
    public void voteQuoteFailTest() {
        Vote voteMock = mock(Vote.class);

        doReturn(null).when(quoteService).voteQuote(voteMock);

        ResponseEntity<Quote> response = quoteController.voteQuote(voteMock);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void retrieveOrderedQuotesTest() {
        Quote[] quotes = new Quote[0];
        doReturn(quotes).when(quoteService).retrieveOrderedQuotes();

        ResponseEntity<Quote[]> response = quoteController.retrieveOrderedQuotes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(quotes, response.getBody());
    }
}
