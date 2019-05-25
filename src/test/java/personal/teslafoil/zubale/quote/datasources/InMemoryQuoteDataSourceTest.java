package personal.teslafoil.zubale.quote.datasources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import personal.teslafoil.zubale.quote.models.Quote;

public class InMemoryQuoteDataSourceTest {

    InMemoryQuoteDataSource inMemoryQuoteDataSource;

    @Before
    public void setUp() {
        inMemoryQuoteDataSource = new InMemoryQuoteDataSource();
    }

    @Test
    public void testGetQuoteByText() {
        Quote quote = mock(Quote.class);
        UUID quoteId = UUID.randomUUID();
        String text = "testGetQuoteByText";

        doReturn(quoteId).when(quote).getId();
        doReturn(text).when(quote).getText();

        inMemoryQuoteDataSource.updateQuote(quote);
        assertSame(quote, inMemoryQuoteDataSource.getQuoteByText(text));
    }

    @Test
    public void testGetQuoteByTextNotFound() {
        String text = "testGetQuoteByTextNotFound";

        assertNull(inMemoryQuoteDataSource.getQuoteByText(text));
    }

    @Test
    public void testGetQuoteById() {
        Quote quote = mock(Quote.class);
        UUID quoteId = UUID.randomUUID();
        String text = "testGetQuoteById";

        doReturn(quoteId).when(quote).getId();
        doReturn(text).when(quote).getText();

        inMemoryQuoteDataSource.updateQuote(quote);
        assertSame(quote, inMemoryQuoteDataSource.getQuoteById(quoteId));
    }

    @Test
    public void testGetQuoteByIdNotFound() {
        UUID quoteId = UUID.randomUUID();

        assertNull(inMemoryQuoteDataSource.getQuoteById(quoteId));
    }
    

    @Test
    public void testUpdateQuote() {
        Quote quote = mock(Quote.class);
        UUID quoteId = UUID.randomUUID();

        doReturn(quoteId).when(quote).getId();

        assertTrue(inMemoryQuoteDataSource.updateQuote(quote));
        assertEquals(1, inMemoryQuoteDataSource.getQuotes().length);
        assertSame(quote, inMemoryQuoteDataSource.getQuotes()[0]);
    }

    @Test
    public void testUpdateQuoteMultiple() {
        Quote quote1 = mock(Quote.class);
        Quote quote2 = mock(Quote.class);
        UUID quoteId1 = UUID.randomUUID();
        UUID quoteId2 = UUID.randomUUID();

        doReturn(quoteId1).when(quote1).getId();
        doReturn(quoteId2).when(quote2).getId();

        assertTrue(inMemoryQuoteDataSource.updateQuote(quote1));
        assertTrue(inMemoryQuoteDataSource.updateQuote(quote2));
        assertEquals(2, inMemoryQuoteDataSource.getQuotes().length);
    }

    @Test
    public void testUpdateQuoteSame() {
        Quote quote1 = mock(Quote.class);
        Quote quote2 = mock(Quote.class);
        UUID quoteId = UUID.randomUUID();

        doReturn(quoteId).when(quote1).getId();
        doReturn(quoteId).when(quote2).getId();

        assertTrue(inMemoryQuoteDataSource.updateQuote(quote1));
        assertTrue(inMemoryQuoteDataSource.updateQuote(quote2));
        assertEquals(1, inMemoryQuoteDataSource.getQuotes().length);
        assertSame(quote2, inMemoryQuoteDataSource.getQuotes()[0]);
    }
}
