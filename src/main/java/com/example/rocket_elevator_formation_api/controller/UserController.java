package com.example.rocket_elevator_formation_api.controller;

import com.example.rocket_elevator_formation_api.model.Quote;
import com.example.rocket_elevator_formation_api.model.User;
import com.example.rocket_elevator_formation_api.repository.DataService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/users")
public class UserController {
    private final DataService repository;

    public UserController(DataService repo){
        repository = repo;
    }

    // GET http://localhost:8080/users
    @GetMapping
    public List<User> findAll() {
        return repository.getInstance().getUserDAO().findAll();
    }

    // GET http://localhost:8080/users/1
    @GetMapping("/findById/{id}")
    public User findById(@PathVariable int id) {
        return repository.getInstance().getUserDAO().findById(id);
    }

    // GET http://localhost:8080/users/{username}/{password}
    @GetMapping("/findByUsernameAndPassword/{username}/{password}")
    public boolean findByUsernameAndPassword(@PathVariable String username, @PathVariable String password) {
        return repository.getInstance().getUserDAO().findByUsernameAndPassword(username,password);
    }

    // GET http://localhost:8080/users/{username}
    @GetMapping("/getUserIdByUsername/{username}")
    public int getUserIdByUsername(@PathVariable String username) {
        return repository.getInstance().getUserDAO().getUserIdByUsername(username);
    }

    // POST http://localhost:8080/users/create
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public boolean insertQuote(@RequestBody User user) {
        return repository.getInstance().getUserDAO().insertSQL(user);
    }

    // PUT http://localhost:8080/users/1
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public boolean updateQuote(@RequestBody User user,@PathVariable int id) {
        return repository.getInstance().getUserDAO().updateQuote(id, user);
    }

    // DELETE http://localhost:8080/users/delete/1
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public boolean deleteQuote(@PathVariable int id) {
        return repository.getInstance().getUserDAO().deleteQuote(id);
    }
}
