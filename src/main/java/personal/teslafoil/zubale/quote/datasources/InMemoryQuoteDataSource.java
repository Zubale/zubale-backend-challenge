package personal.teslafoil.zubale.quote.datasources;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import personal.teslafoil.zubale.quote.models.Quote;

@Component
public class InMemoryQuoteDataSource implements QuoteDataSource {
    
    private DataStore dataStore;

    public InMemoryQuoteDataSource() {
        dataStore = new DataStore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Quote getQuoteByText(String quoteText) {
        UUID quoteId = dataStore.textToIdMap.get(quoteText);
        if (null == quoteId) {
            return null;
        } else {
            return dataStore.idToQuoteMap.get(quoteId);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Quote getQuoteById(UUID quoteId) {
        return dataStore.idToQuoteMap.get(quoteId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized boolean updateQuote(Quote quote) {
        if (null == dataStore.idToQuoteMap.put(quote.getId(), quote)) {
            dataStore.textToIdMap.put(quote.getText(), quote.getId());
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Quote[] getQuotes() {
        return dataStore.idToQuoteMap.values().toArray(new Quote[0]);
    }

    class DataStore {

        Map<String, UUID> textToIdMap;
        Map<UUID, Quote> idToQuoteMap;

        DataStore() {
            textToIdMap = new HashMap<>();
            idToQuoteMap = new HashMap<>();
        }
    }
}
