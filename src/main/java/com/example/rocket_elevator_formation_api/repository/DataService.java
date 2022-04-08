package com.example.rocket_elevator_formation_api.repository;

import org.springframework.stereotype.Repository;

@Repository
public class DataService {
    private DataService repo;
    private QuoteDAO quoteDAO;
    private UserDAO userDAO;
    private EmployeeDAO employeeDAO;

    private DataService() {
        this.quoteDAO = new QuoteDAO();
        this.userDAO = new UserDAO();
        this.employeeDAO = new EmployeeDAO();
    }

    public DataService getInstance(){
        if(repo == null)
            repo = new DataService();
        return repo;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public QuoteDAO getQuoteDAO() {
        return quoteDAO;
    }

    public EmployeeDAO getEmployeeDAO() {
        return employeeDAO;
    }
}
