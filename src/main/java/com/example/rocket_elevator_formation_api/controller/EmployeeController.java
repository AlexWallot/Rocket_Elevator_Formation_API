package com.example.rocket_elevator_formation_api.controller;

import com.example.rocket_elevator_formation_api.model.Employee;
import com.example.rocket_elevator_formation_api.model.User;
import com.example.rocket_elevator_formation_api.repository.DataService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final DataService repository;

    public EmployeeController(DataService repository) {
        this.repository = repository;
    }

    // GET http://localhost:8080/employees
    @GetMapping
    public List<Employee> findAll() {
        return repository.getInstance().getEmployeeDAO().findAll();
    }

    // GET http://localhost:8080/employees/1
    @GetMapping("/{id}")
    public Employee findById(@PathVariable int id) {
        return repository.getInstance().getEmployeeDAO().findById(id);
    }

    // GET http://localhost:8080/employees/checkIfUserIsEmployee/1
    @GetMapping("/checkIfUserIsEmployee/{id}")
    public Boolean checkIfUserIsEmployee(@PathVariable int id) {
        return repository.getInstance().getEmployeeDAO().checkIfUserIsEmployee(id);
    }

    // POST http://localhost:8080/employees/create
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public boolean insertQuote(@RequestBody Employee employee) {
        return repository.getInstance().getEmployeeDAO().insertSQL(employee);
    }

    // PUT http://localhost:8080/employees/1
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public boolean updateQuote(@RequestBody Employee employee,@PathVariable int id) {
        return repository.getInstance().getEmployeeDAO().updateQuote(id, employee);
    }

    // DELETE http://localhost:8080/employees/delete/1
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public boolean deleteQuote(@PathVariable int id) {
        return repository.getInstance().getEmployeeDAO().deleteQuote(id);
    }
}
