package com.example.rocket_elevator_formation_api.repository;

import com.example.rocket_elevator_formation_api.model.Employee;
import com.example.rocket_elevator_formation_api.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeDAO {
    private static Connection connection;

    public EmployeeDAO() {
        connection = connect();
    }

    public Connection connect(){
        Connection connection = null;
        try {
            String connectionString;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-GT1NLSB\\SQLEXPRESS:1434;database=Rocket_Elevator_Formation;user=alexwallot;password=allo1392");
            return connection;
        } catch (SQLException e) {
            Logger.getLogger(QuoteDAO.class.getName()).log(Level.SEVERE, "Can't connect", e);
        } catch (ClassNotFoundException e){
            Logger.getLogger(QuoteDAO.class.getName()).log(Level.SEVERE, "No found driver", e);
        }
        return connection;
    }

    public List<Employee> findAll(){
        PreparedStatement getData;
        ResultSet result;
        List<Employee> employeeList = new ArrayList<>();
        try {
            Connection connection;
            connection  = connect();
            getData = connection.prepareStatement("SELECT * FROM Employee;");
            result = getData.executeQuery();
            while(result.next()) {
                employeeList.add(new Employee(Integer.parseInt(result.getString("ID")),result.getString("first_name"),result.getString("last_name"),Integer.parseInt(result.getString("userID"))));
            }
            return employeeList;
        } catch (Exception e) {
            Logger.getLogger(QuoteDAO.class.getName()).log(Level.SEVERE, "fetch sql error", e);
        }
        return null;
    }

    public Employee findById(int id){
        PreparedStatement getData;
        ResultSet result;
        try {
            Connection connection;
            connection  = connect();
            getData = connection.prepareStatement("SELECT * FROM Employee WHERE Employee.ID=" + id +";");
            result = getData.executeQuery();
            if (result.next() == false){
                return null;
            }else {
                return new Employee(Integer.parseInt(result.getString("ID")),result.getString("first_name"),result.getString("last_name"),Integer.parseInt(result.getString("userID")));
            }
        } catch (Exception e) {
            Logger.getLogger(QuoteDAO.class.getName()).log(Level.SEVERE, "fetch sql error", e);
        }
        return null;
    }

    public Boolean checkIfUserIsEmployee(int id){
        PreparedStatement getData;
        ResultSet result;
        try {
            Connection connection;
            connection  = connect();
            getData = connection.prepareStatement("SELECT * FROM Employee WHERE Employee.userID=" + id +";");
            result = getData.executeQuery();
            if (result.next() == false){
                return false;
            }else{
                if (Integer.parseInt(result.getString("userID")) == id) return true;
            }
        } catch (Exception e) {
            Logger.getLogger(QuoteDAO.class.getName()).log(Level.SEVERE, "fetch sql error", e);
        }
        return false;
    }

    public boolean insertSQL(Employee employee){
        PreparedStatement getData;
        try {
            Connection connection;
            connection  = connect();
            getData = connection.prepareStatement("INSERT INTO Employee VALUES (" +"'" +employee.getFirstname() + "'" +"," + "'" +employee.getLastname() + "'" + ", "+ employee.getUserID() +")");
            getData.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(QuoteDAO.class.getName()).log(Level.SEVERE,"error insert", e);
        }
        return false;
    }

    public boolean updateQuote(int id, Employee employee){
        PreparedStatement getData;
        try {
            Connection connection;
            connection  = connect();
            getData = connection.prepareStatement("UPDATE Employee " +
                    "SET first_name = "+"'" + employee.getFirstname() +"'" +", last_name = "+"'" + employee.getLastname() + "'"+ ", userID = " + employee.getUserID() + " " +
                    "WHERE Employee.ID = "+id+";");
            getData.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(QuoteDAO.class.getName()).log(Level.SEVERE,"error delete", e);
        }
        return false;
    }

    public boolean deleteQuote(int id){
        PreparedStatement getData;
        try {
            Connection connection;
            connection  = connect();
            getData = connection.prepareStatement("DELETE FROM Employee WHERE Employee.ID=" + id + ";");
            getData.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(QuoteDAO.class.getName()).log(Level.SEVERE,"error delete", e);
        }
        return false;
    }
}
