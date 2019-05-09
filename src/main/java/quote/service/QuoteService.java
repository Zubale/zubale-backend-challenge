package quote.service;

import quote.model.Quote;

import java.util.List;

public interface QuoteService {

    Quote submit(Quote t);

    List<Quote> retrieveAll();

    Quote vote(long idQuote);

}
