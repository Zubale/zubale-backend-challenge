package personal.teslafoil.zubale.quote.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import personal.teslafoil.zubale.quote.models.Quote;
import personal.teslafoil.zubale.quote.models.Vote;

@RestController
@RequestMapping("/api/quote")
@Api(value = "quote")
public class QuoteController {

    @ApiOperation(value = "Add a quote")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR") 
            })
    @PutMapping(
            path = "/addquote/{quote}", 
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Quote> postQuote(
            @PathVariable(name = "quote", required = true) String quote
            ) {
            return new ResponseEntity<>(
                    new Quote(quote), 
                    HttpStatus.CREATED);
    }
    
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
        Optional<Quote> optionalQuote = Optional.ofNullable(null);
            return new ResponseEntity<>(
                    optionalQuote.orElse(null),
                    optionalQuote.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    
    @ApiOperation(value = "Get all quotes ordered by votes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR") 
            })
    @GetMapping(
            path = "/quotelist/", 
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Quote>> getQuotes() {
        List<Quote> quotes = new ArrayList<>();
            return new ResponseEntity<>(
                    quotes,
                    HttpStatus.OK);
    }
}
