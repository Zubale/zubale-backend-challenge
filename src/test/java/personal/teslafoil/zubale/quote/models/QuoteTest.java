package personal.teslafoil.zubale.quote.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import personal.teslafoil.zubale.quote.models.Vote.VoteValue;

public class QuoteTest {

    @Test
    public void testConstructor() {
        String quoteText = "testConstructor";

        Quote quote = new Quote(quoteText);

        assertNotNull(quote);
        assertNotNull(quote.getId());
        assertEquals(quoteText, quote.getText());
        assertEquals(0, quote.getVoteCount());
        assertEquals(0, quote.getVoteScore());
    }

    @Test
    public void testVoteUp() {
        String quoteText = "testVoteUp";

        Quote quote = new Quote(quoteText);
        quote.vote(VoteValue.UP);
        assertEquals(1, quote.getVoteCount());
        assertEquals(1, quote.getVoteScore());
    }

    @Test
    public void testVoteDown() {
        String quoteText = "testVoteDown";

        Quote quote = new Quote(quoteText);
        quote.vote(VoteValue.DOWN);
        assertEquals(1, quote.getVoteCount());
        assertEquals(-1, quote.getVoteScore());
    }

    @Test
    public void testCompareTo() {
        String quoteText1 = "testCompareTo1";
        String quoteText2 = "testCompareTo2";

        Quote quote1 = new Quote(quoteText1);
        Quote quote2 = new Quote(quoteText2);

        quote1.vote(VoteValue.UP);

        assertEquals(1, quote1.compareTo(quote2));
        assertEquals(-1, quote2.compareTo(quote1));
    }

    @Test
    public void testCompareToSameScore() {
        String quoteText1 = "testCompareTo1";
        String quoteText2 = "testCompareTo2";

        Quote quote1 = new Quote(quoteText1);
        Quote quote2 = new Quote(quoteText2);

        quote1.vote(VoteValue.UP);
        quote1.vote(VoteValue.DOWN);

        assertEquals(-2, quote1.compareTo(quote2));
        assertEquals(2, quote2.compareTo(quote1));
    }
}
