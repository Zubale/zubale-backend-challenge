package personal.teslafoil.zubale.quote.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import personal.teslafoil.zubale.quote.datasources.QuoteDataSource;
import personal.teslafoil.zubale.quote.models.Quote;
import personal.teslafoil.zubale.quote.models.Vote;
import personal.teslafoil.zubale.quote.models.Vote.VoteValue;

@RunWith(MockitoJUnitRunner.class)
public class QuoteServiceImplTest {

    @InjectMocks
    QuoteServiceImpl quoteServiceImpl;

    @Mock
    QuoteDataSource quoteDataSource;

    @Test
    public void testAddQuote() {
        String quoteText = "testAddQuote";

        doReturn(null).when(quoteDataSource).getQuoteByText(quoteText);

        assertSame(quoteText, quoteServiceImpl.addQuote(quoteText).getText());

        ArgumentCaptor<Quote> quoteCaptor = ArgumentCaptor.forClass(Quote.class);
        verify(quoteDataSource).updateQuote(quoteCaptor.capture());
        assertEquals(quoteText, quoteCaptor.getValue().getText());
    }

    @Test
    public void testAddQuoteExist() {
        String quoteText = "testAddQuoteExist";

        doReturn(mock(Quote.class)).when(quoteDataSource).getQuoteByText(quoteText);

        assertNull(quoteServiceImpl.addQuote(quoteText));
    }

    @Test
    public void testVoteQuote() {
        UUID quoteId = UUID.randomUUID();
        Vote vote = mock(Vote.class);
        Quote quote = mock(Quote.class);

        doReturn(quoteId).when(vote).getQuoteId();
        doReturn(VoteValue.UP).when(vote).getVoteValue();
        doReturn(quote).when(quoteDataSource).getQuoteById(quoteId);

        assertSame(quote, quoteServiceImpl.voteQuote(vote));

        ArgumentCaptor<VoteValue> voteValueCaptor = ArgumentCaptor.forClass(VoteValue.class);
        verify(quote).vote(voteValueCaptor.capture());
        assertEquals(VoteValue.UP, voteValueCaptor.getValue());

        ArgumentCaptor<Quote> quoteCaptor = ArgumentCaptor.forClass(Quote.class);
        verify(quoteDataSource).updateQuote(quoteCaptor.capture());
        assertEquals(quote, quoteCaptor.getValue());
    }

    @Test
    public void testVoteQuoteNotExist() {
        Vote vote = mock(Vote.class);

        assertNull(quoteServiceImpl.voteQuote(vote));
    }

    @Test
    public void testRetrieveOrderedQuotes() {
        Quote[] quotes = { mock(Quote.class) };

        doReturn(quotes).when(quoteDataSource).getQuotes();

        assertEquals(1, quoteServiceImpl.retrieveOrderedQuotes().length);
    }

    @Test
    public void testRetrieveOrderedQuotesEmpty() {
        Quote[] quotes = new Quote[0];

        doReturn(quotes).when(quoteDataSource).getQuotes();

        assertEquals(0, quoteServiceImpl.retrieveOrderedQuotes().length);
    }
}
