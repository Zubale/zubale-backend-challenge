package com.pulpos.quotes

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/quotes")
class QuotesController {
    @GetMapping("/")
    fun getAllQuotes() : String {
        return "Not implemented yet"
    }

    @PostMapping("/submit")
    fun submit(@RequestParam quoteText : String) : String {
        return "Not implemented yet"
    }

    @GetMapping("/voteup")
    fun voteUp(@RequestParam(name = "id") quoteId : Int) : String {
        return "Not implemented yet"
    }

    @GetMapping("/votedown")
    fun voteDown(@RequestParam(name = "id") quoteId : Int) : String {
        return "Not implemented yet"
    }

    @GetMapping("/list")
    fun getQuotePage(@RequestParam page : Int, @RequestParam pageSize : Int) : String {
        return "Not inplemented yet"
    }


}