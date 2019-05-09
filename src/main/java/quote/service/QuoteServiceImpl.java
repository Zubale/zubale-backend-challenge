package quote.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import quote.model.Quote;
import quote.repository.QuoteRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuoteServiceImpl  implements QuoteService{

    private QuoteRepository quoteRepository;
    private static final int ADD_ONE_VOTE = 1;


    public QuoteServiceImpl() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("quote.repository");
        context.refresh();
        quoteRepository = context.getBean(QuoteRepository.class);
    }


    @Override
    public Quote submit(Quote quote) {
        quoteRepository.submit(quote);
        return quoteRepository.get(quote.getIdQuote());
    }

    @Override
    public List<Quote> retrieveAll() {
        ArrayList mySortedList =new ArrayList(quoteRepository.retrieveAll());
        Collections.sort(mySortedList);
        return mySortedList;
    }

    @Override
    public Quote vote(long idQuote) {
        Quote quoteToAddVote =  quoteRepository.get(idQuote);
        if (quoteToAddVote != null){
            quoteToAddVote.setNumberOfVotes(quoteToAddVote.getNumberOfVotes()+ ADD_ONE_VOTE);
        }
        return  quoteToAddVote;
    }

}
