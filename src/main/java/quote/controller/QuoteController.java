package quote.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quote.model.Quote;
import quote.service.QuoteService;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class QuoteController {

    private static final AtomicLong counter = new AtomicLong();
    private QuoteService service ;

    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("quote.service");
        context.refresh();

        service = context.getBean(QuoteService.class);

        Quote quote3 = new Quote (counter.incrementAndGet(),
                "my quote 3");
        quote3.setNumberOfVotes(5);
        service.submit(quote3);

        Quote quote1 = new Quote (counter.incrementAndGet(),
        "my quote 1");
        quote1.setNumberOfVotes(3);
        service.submit(quote1);

        Quote quote4 = new Quote (counter.incrementAndGet(),
                "my quote 4");
        quote4.setNumberOfVotes(15);
        service.submit(quote4);


        Quote quote2 = new Quote (counter.incrementAndGet(),
                "my quote 2");
        quote2.setNumberOfVotes(4);
        service.submit(quote2);

        Quote quote5 = new Quote (counter.incrementAndGet(),
                "my quote 5");
        quote5.setNumberOfVotes(30);
        service.submit(quote5);


    }

    @RequestMapping(value =  "/quotes", method = RequestMethod.GET)
    public ResponseEntity<List<Quote>> retrieveAll() {
        return new ResponseEntity<>(service.retrieveAll(), HttpStatus.OK);
    }

    @RequestMapping(value =  "/quotes/{idQuote}", method = RequestMethod.PUT)
    public ResponseEntity<Quote> vote(@PathVariable("idQuote") int idQuote) {
        return new ResponseEntity<>(service.vote(idQuote), HttpStatus.OK);
    }

    @RequestMapping(value =  "/quotes", method = RequestMethod.POST)
    public ResponseEntity<Quote> submit(@RequestBody Quote quote) {
        quote.setIdQuote(counter.incrementAndGet());
        return new ResponseEntity<>(service.submit(quote), HttpStatus.CREATED);
    }

}
