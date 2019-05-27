package com.zubale.controllers;

import com.zubale.models.entity.Quote;
import com.zubale.models.entity.Vote;
import com.zubale.models.services.QuoteService;
import com.zubale.models.services.VoteService;
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
public class VoteRestController {

    @Autowired
    private VoteService voteService;

    private final Logger log = LoggerFactory.getLogger(QuoteRestController.class);

    @GetMapping("/votes")
    public List<Vote> index() {

        return voteService.findAll();
    }

    @Secured({"ROLE_USER"})
    @PostMapping("/votes")
    public ResponseEntity<?> create(@Valid @RequestBody Vote vote, BindingResult result, HttpServletRequest request) {
        Vote voteNew = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(e -> "Field '" + e.getField() + "' " + e.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            voteNew = voteService.save(vote);

        } catch (DataAccessException e) {
            response.put("message", "Error at insert of Vote");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Vote created");
        response.put("Vote", voteNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}
