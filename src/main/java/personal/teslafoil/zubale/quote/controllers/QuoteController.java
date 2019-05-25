package personal.teslafoil.zubale.quote.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import personal.teslafoil.zubale.quote.models.Quote;
import personal.teslafoil.zubale.quote.models.Vote;
import personal.teslafoil.zubale.quote.services.QuoteService;

@RestController
@RequestMapping("/api/quote")
@Api(value = "quote")
@Slf4j
public class QuoteController {

    String appName;
    QuoteService quoteService;

    @Autowired
    public QuoteController(
            @Value("${spring.application.name}") String appName,
            QuoteService quoteService
            ) {
        this.appName = appName;
        this.quoteService = quoteService;
    }

    /**
     * Add a quote to data source.<br>
     * If the quote text is not unique, return null and CONFLICT status.
     * 
     * @param quoteText
     *            String text of the quote.
     * @return Newly created quote, or existing quote of same text.
     */
    @ApiOperation(value = "Add a quote")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 409, message = "CONFLICT"),
            @ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR")
            })
    @PutMapping(
            path = "/addquote/{quotetext}",
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Quote> addQuote(
            @PathVariable(name = "quotetext", required = true) String quoteText
            ) {
        log.info("{} - addQuote() - quoteText: {}", appName, quoteText);
        Optional<Quote> optionalQuote = Optional.ofNullable(quoteService.addQuote(quoteText));
        return new ResponseEntity<>(
                optionalQuote.orElse(null),
                optionalQuote.isPresent() ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    /**
     * Vote on a quote by quote ID.<br>
     * If the quote ID is not found, return null and NOT_FOUND status.
     * 
     * @param vote
     *            Data object describe the vote, including quoteId and
     *            voteValue.
     * @return The quote object voted on with updated result.
     */
    @ApiOperation(value = "Vote on a quote")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "BAD_REQUEST"),
            @ApiResponse(code = 404, message = "NOT_FOUND"),
            @ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR")
            })
    @PostMapping(
            path = "/vote/",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Quote> voteQuote(
            @RequestBody(required = true) Vote vote
            ) {
        log.info("{} - voteQuote() - quoteID: {}, voteValue: {}", appName, vote.getQuoteId(), vote.getVoteValue());
        Optional<Quote> optionalQuote = Optional.ofNullable(quoteService.voteQuote(vote));
        return new ResponseEntity<>(
                optionalQuote.orElse(null),
                optionalQuote.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     * Return all saved quote, ranked by quote score.<br>
     * If the score is tied the one with fewer vote is ranked higher.
     * 
     * @return An array of quotes.
     */
    @ApiOperation(value = "Get all quotes ordered by votes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR")
            })
    @GetMapping(
            path = "/quotelist/",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Quote[]> retrieveOrderedQuotes() {
        log.info("{} - retrieveQuotes()", appName);
        return new ResponseEntity<>(
                quoteService.retrieveOrderedQuotes(),
                HttpStatus.OK);
    }
}
