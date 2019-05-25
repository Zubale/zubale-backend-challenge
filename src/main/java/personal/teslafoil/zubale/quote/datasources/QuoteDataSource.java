package personal.teslafoil.zubale.quote.datasources;

import java.util.UUID;

import personal.teslafoil.zubale.quote.models.Quote;

public interface QuoteDataSource {

    /**
     * Get the quote by quote text.
     * 
     * @param quoteText
     *            String text of the quote.
     * @return The quote object if exist, null otherwise.
     */
    Quote getQuoteByText(String quoteText);

    /**
     * Get the quote by quote ID,
     * 
     * @param quoteId
     *            ID of the quote.
     * @return The quote object if exist, null otherwise.
     */
    Quote getQuoteById(UUID quoteId);

    /**
     * Update the quote to data source.<br>
     * Add the quote if it does not exist already.
     * 
     * @param quote
     *            The quote to be updated.
     * @return true if successful, false otherwise.
     */
    boolean updateQuote(Quote quote);

    /**
     * Return all saved quotes
     * 
     * @return An array of quotes.
     */
    Quote[] getQuotes();

}
