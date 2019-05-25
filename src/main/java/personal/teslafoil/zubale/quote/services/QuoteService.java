package personal.teslafoil.zubale.quote.services;

import personal.teslafoil.zubale.quote.models.Quote;
import personal.teslafoil.zubale.quote.models.Vote;

public interface QuoteService {

    /**
     * Add a quote to data source.<br>
     * If the quote text is not unique, return null.
     * 
     * @param quoteText
     *            String text of the quote.
     * @return Newly created quote, or existing quote of same text.
     */
    Quote addQuote(String quoteText);

    /**
     * Vote on a quote by quote ID.
     * 
     * @param vote
     *            Data object describe the vote, including quoteId and
     *            voteValue.
     * @return The quote object voted on with updated result.
     */
    Quote voteQuote(Vote vote);

    /**
     * Return all saved quote, ranked by quote score.<br>
     * If the score is tied the one with fewer vote is ranked higher.
     * 
     * @return An array of quotes.
     */
    Quote[] retrieveOrderedQuotes();

}
