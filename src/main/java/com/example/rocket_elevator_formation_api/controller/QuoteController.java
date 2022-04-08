package com.example.rocket_elevator_formation_api.controller;


import com.example.rocket_elevator_formation_api.model.Quote;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.rocket_elevator_formation_api.repository.DataService;

import java.util.List;

@RestController
@RequestMapping ("/quotes")
public class QuoteController {

    private final DataService repository;

    public QuoteController(DataService repo){
        repository = repo;
    }

    // GET http://localhost:8080/quotes
    @GetMapping
    public List<Quote> findAll() {
        return repository.getInstance().getQuoteDAO().findAll();
    }

    // GET http://localhost:8080/quotes/1
    @GetMapping("/{id}")
    public Quote findById(@PathVariable int id) {
        return repository.getInstance().getQuoteDAO().findById(id);
    }

    // POST http://localhost:8080/quotes/create
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public boolean insertQuote(@RequestBody Quote quote) {
        return repository.getInstance().getQuoteDAO().insertSQL(quote);
    }

    // PUT http://localhost:8080/quotes/1
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public boolean updateQuote(@RequestBody Quote quote,@PathVariable int id) {
        return repository.getInstance().getQuoteDAO().updateQuote(id, quote);
    }

    // DELETE http://localhost:8080/quotes/delete/1
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public boolean deleteQuote(@PathVariable int id) {
        return repository.getInstance().getQuoteDAO().deleteQuote(id);
    }
}
