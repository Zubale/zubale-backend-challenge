package com.zubale.controllers;
import com.zubale.models.entity.Quote;
import com.zubale.models.services.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class QuoteRestController {

    @Autowired
    private QuoteService quoteService;

    private final Logger log = LoggerFactory.getLogger(QuoteRestController.class);

    @GetMapping("/quotes")
    public List<Quote> index(){

        return quoteService.findAll();
    }

    @GetMapping("/quotes/most-voted")
    public List<Quote> findAllOrderByMostVoted(){

        return quoteService.findAllOrderByMostVoted();
    }

    @Secured({"ROLE_USER"})
    @PostMapping("/quotes")
    public ResponseEntity<?> create(@Valid @RequestBody Quote quote, BindingResult result, HttpServletRequest request){
        log.info(quote.toString());
        Quote quoteNew = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(e -> "Field '"+ e.getField()+ "' "+e.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            quoteNew = quoteService.save(quote);

        }catch (DataAccessException e){
            response.put("message","Error at insert for Quotes");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","Quote created successfully");
        response.put("Quote",quoteNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}
