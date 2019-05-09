package quote.repository;

import org.springframework.stereotype.Repository;
import quote.model.Quote;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class QuoteRepository implements ObjectRepository<Quote> {

    private Map<Long, Quote> repository;

    public QuoteRepository() {
        this.repository = new HashMap<>();
    }


    @Override
    public void submit(Quote quote) {
        repository.put(quote.getIdQuote(), quote);
    }

    @Override
    public Collection<Quote> retrieveAll() {
        return repository.values();
    }

    @Override
    public Quote get(long idQuote) {
        return  repository.get(idQuote);
    }
}
