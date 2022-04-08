package com.example.rocket_elevator_formation_api.repository;

import com.example.rocket_elevator_formation_api.model.Quote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuoteDAO {
    private static Connection connection;

    public QuoteDAO() {
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

    public List<Quote> findAll(){
        PreparedStatement getData;
        ResultSet result;
        List<Quote> quoteList = new ArrayList<>();
        try {
            Connection connection;
            connection  = connect();
            getData = connection.prepareStatement("SELECT * FROM Quote;");
            result = getData.executeQuery();
            while(result.next()) {
                quoteList.add(new Quote(Integer.parseInt(result.getString("ID")),result.getString("buildingType"),result.getString("priceType"),Integer.parseInt(result.getString("numElevator")),Double.parseDouble(result.getString("totalPrice")),Double.parseDouble(result.getString("installationPrice")),Double.parseDouble(result.getString("finalPrice"))));
            }
            return quoteList;
        } catch (Exception e) {
            Logger.getLogger(QuoteDAO.class.getName()).log(Level.SEVERE, "fetch sql error", e);
        }
        return null;
    }

    public Quote findById(int id){
        PreparedStatement getData;
        ResultSet result;
        try {
            Connection connection;
            connection  = connect();
            getData = connection.prepareStatement("SELECT * FROM Quote WHERE Quote.ID=" + id +";");
            result = getData.executeQuery();
            result.next();
            return new Quote(Integer.parseInt(result.getString("ID")),result.getString("buildingType"),result.getString("priceType"),Integer.parseInt(result.getString("numElevator")),Double.parseDouble(result.getString("totalPrice")),Double.parseDouble(result.getString("installationPrice")),Double.parseDouble(result.getString("finalPrice")));
        } catch (Exception e) {
            Logger.getLogger(QuoteDAO.class.getName()).log(Level.SEVERE, "fetch sql error", e);
        }
        return null;
    }

    public boolean insertSQL(Quote quote){
        PreparedStatement getData;
        try {
            Connection connection;
            connection  = connect();
            getData = connection.prepareStatement("INSERT INTO Quote VALUES (" +"'" +quote.getBuildingType() + "'" +"," + "'" +quote.getPriceType() + "'" + "," + quote.getNumElevator() + "," + quote.getTotalPrice() + "," + quote.getInstallationPrice() + "," + quote.getFinalPrice() + ")");
            getData.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(QuoteDAO.class.getName()).log(Level.SEVERE,"error insert", e);
        }
        return false;
    }

    public boolean updateQuote(int id, Quote quote){
        PreparedStatement getData;
        try {
            Connection connection;
            connection  = connect();
            getData = connection.prepareStatement("UPDATE Quote " +
                                                      "SET buildingType = "+"'" +quote.getBuildingType() +"'" +", priceType= "+"'" +quote.getPriceType() + "'"+ ", numElevator= " +quote.getNumElevator() +", totalPrice = "+quote.getTotalPrice() + ", installationPrice = "+quote.getInstallationPrice() + ", finalPrice = "+quote.getFinalPrice() +" " +
                                                      "WHERE Quote.ID = "+id+";");
            getData.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(QuoteDAO.class.getName()).log(Level.SEVERE,"error update", e);
        }
        return false;
    }

    public boolean deleteQuote(int id){
        PreparedStatement getData;
        try {
            Connection connection;
            connection  = connect();
            getData = connection.prepareStatement("DELETE FROM Quote WHERE Quote.ID=" + id + ";");
            getData.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(QuoteDAO.class.getName()).log(Level.SEVERE,"error delete", e);
        }
        return false;
    }
}
