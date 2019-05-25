package personal.teslafoil.zubale.quote.services;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import personal.teslafoil.zubale.quote.datasources.QuoteDataSource;
import personal.teslafoil.zubale.quote.models.Quote;
import personal.teslafoil.zubale.quote.models.Vote;

@Service
public class QuoteServiceImpl implements QuoteService {

    QuoteDataSource quoteDataSource;

    @Autowired
    public QuoteServiceImpl(QuoteDataSource quoteDataSource) {
        this.quoteDataSource = quoteDataSource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Quote addQuote(String quoteText) {
        if (null != quoteDataSource.getQuoteByText(quoteText)) {
            return null;
        } else {
            Quote quote = new Quote(quoteText);
            quoteDataSource.updateQuote(quote);
            return quote;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Quote voteQuote(Vote vote) {
        Quote quote = quoteDataSource.getQuoteById(vote.getQuoteId());
        if (null != quote) {
            quote.vote(vote.getVoteValue());
            // This is not needed since data source is in memory, but would be
            // necessary for other implementation.
            quoteDataSource.updateQuote(quote);
        }
        return quote;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Quote[] retrieveOrderedQuotes() {
        Quote[] quotes = quoteDataSource.getQuotes();
        Arrays.sort(quotes, Collections.reverseOrder());
        return quotes;
    }
}
