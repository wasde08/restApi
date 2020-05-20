package qwer.dao;

import qwer.entity.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import static qwer.dao.DB.getDBConnection;

public class SellerDAO {

    public static Seller getSeller(String id) {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        try {
            dbConnection =  DB.getInstance();
            String selectTableSQL = "SELECT * from seller";
            statement = dbConnection.prepareStatement(selectTableSQL);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String sellerID = rs.getString("id");
                if (sellerID.equals(id)) {
                    String sellerName = rs.getString("name");
                    String sellerExperience = rs.getString("experience");
                    return new Seller(sellerID,sellerName, sellerExperience,null);
                }
            }
            rs.close();
            dbConnection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Seller> getSellerAll() {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        List<Seller> list = new ArrayList<>();
        try {
            dbConnection =  DB.getInstance();
            String selectTableSQL = "SELECT * from seller";
            statement = dbConnection.prepareStatement(selectTableSQL);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new Seller(rs.getString("id"),
                        rs.getString("name")
                        ,rs.getString("experience"),null));
            }
            rs.close();
            dbConnection.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public Seller check(Seller seller){
        Connection dbConnection = null;
        PreparedStatement statement = null;
        try {
            dbConnection =  DB.getInstance();
            String selectTableSQL = "SELECT * from seller";
            statement = dbConnection.prepareStatement(selectTableSQL);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String sellerIdBD = rs.getString("id");
                String nameBD = rs.getString("name");
                String passBD = rs.getString("passuser");
                if (nameBD.equals(seller.getSellerName())&&passBD.equals(seller.getPassUser())) {
                    String experience = rs.getString("experience");
                    return new Seller(sellerIdBD, seller.getSellerName(), experience, "");
                }
            }
            rs.close();
            dbConnection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Seller addSeller(Seller seller) {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        int sellerID = 0;
        try {
            dbConnection =  DB.getInstance();
            String selectTableSQL = "SELECT * from seller";
            statement = dbConnection.prepareStatement(selectTableSQL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (sellerID<Integer.parseInt(rs.getString("id"))){
                    sellerID = Integer.parseInt(rs.getString("id"));
                }
            }
            sellerID++;
            statement.close();
            rs.close();
            String sql = "INSERT INTO seller VALUES " + " ('" +  sellerID  + "','" +
                    seller.getSellerName()+ "','" + seller.getExperience()
                    + "','" + seller.getPassUser()+"');";
            Connection c =  DB.getInstance();
            Statement stmt = null;
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            rs.close();
            dbConnection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Seller(String.valueOf(sellerID),seller.getSellerName(),
                seller.getExperience(),"");
    }
    public Seller updateSeller(Seller seller){
        Seller seller1 = getSeller(seller.getSellerId());
        seller1.setSellerId(seller.getSellerId());
        seller1.setSellerName(seller.getSellerName());
        seller1.setExperience(seller.getExperience());
        seller1.setPassUser(seller.getPassUser());
        delete(seller.getSellerId());
        return addSeller(seller1);
    }
    public void delete(String id){
        String sql = "DELETE from seller where id = " + id +";";
        Connection c =  DB.getInstance();
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
