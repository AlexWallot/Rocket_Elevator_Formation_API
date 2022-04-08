package com.example.rocket_elevator_formation_api.repository;

import com.example.rocket_elevator_formation_api.model.Quote;
import com.example.rocket_elevator_formation_api.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    private static Connection connection;

    public UserDAO() {
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

    public List<User> findAll(){
        PreparedStatement getData;
        ResultSet result;
        List<User> userList = new ArrayList<>();
        try {
            Connection connection;
            connection  = connect();
            getData = connection.prepareStatement("SELECT * FROM Users;");
            result = getData.executeQuery();
            while(result.next()) {
                userList.add(new User(Integer.parseInt(result.getString("ID")),result.getString("username"),result.getString("password")));
            }
            return userList;
        } catch (Exception e) {
            Logger.getLogger(QuoteDAO.class.getName()).log(Level.SEVERE, "fetch sql error", e);
        }
        return null;
    }

    public User findById(int id){
        PreparedStatement getData;
        ResultSet result;
        try {
            Connection connection;
            connection  = connect();
            getData = connection.prepareStatement("SELECT * FROM Users WHERE Users.ID=" + id +";");
            result = getData.executeQuery();
            result.next();
            return new User(Integer.parseInt(result.getString("ID")),result.getString("username"),result.getString("password"));
        } catch (Exception e) {
            Logger.getLogger(QuoteDAO.class.getName()).log(Level.SEVERE, "fetch sql error", e);
        }
        return null;
    }

    public boolean findByUsernameAndPassword(String username,String password){
        PreparedStatement getData;
        ResultSet result;
        try {
            Connection connection;
            connection  = connect();
            getData = connection.prepareStatement("SELECT * FROM Users WHERE Users.username=" +"'" +username + "'" +" AND Users.password= "+ "'" +password + "'" +";");
            result = getData.executeQuery();
            result.next();
            if (result.getString("username").equals(username)) return true;
        } catch (Exception e) {
            Logger.getLogger(QuoteDAO.class.getName()).log(Level.SEVERE, "fetch sql error", e);
        }
        return false;
    }

    public int getUserIdByUsername(String username){
        PreparedStatement getData;
        ResultSet result;
        try {
            Connection connection;
            connection  = connect();
            getData = connection.prepareStatement("SELECT * FROM Users WHERE Users.username=" +"'" +username + "'"+";");
            result = getData.executeQuery();
            result.next();
            return Integer.parseInt(result.getString("ID"));
        } catch (Exception e) {
            Logger.getLogger(QuoteDAO.class.getName()).log(Level.SEVERE, "fetch sql error", e);
        }
        return 0;
    }

    public boolean insertSQL(User user){
        PreparedStatement getData;
        try {
            Connection connection;
            connection  = connect();
            getData = connection.prepareStatement("INSERT INTO Users VALUES (" +"'" +user.getUsername() + "'" +"," + "'" +user.getPassword() + "'" + ")");
            getData.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(QuoteDAO.class.getName()).log(Level.SEVERE,"error insert", e);
        }
        return false;
    }

    public boolean updateQuote(int id, User user){
        PreparedStatement getData;
        try {
            Connection connection;
            connection  = connect();
            getData = connection.prepareStatement("UPDATE Users " +
                                                      "SET username = "+"'" +user.getUsername() +"'" +", password = "+"'" +user.getPassword() + "'"+ " " +
                                                      "WHERE Users.ID = "+id+";");
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
            getData = connection.prepareStatement("DELETE FROM Users WHERE Users.ID=" + id + ";");
            getData.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(QuoteDAO.class.getName()).log(Level.SEVERE,"error delete", e);
        }
        return false;
    }
}
