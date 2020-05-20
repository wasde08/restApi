package qwer.dao;

import qwer.entity.Client;
import qwer.entity.Deal;
import qwer.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

//import static qwer.dao.DB.getDBConnection;

public class DealDAO {
    public static void main(String[] args) throws SQLException {
        DealDAO sellerDAO = new DealDAO();

        sellerDAO.delete("2");
        System.out.println(sellerDAO.getDealAll());
    }
    public static Deal getDeal(String id) {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        try {
            dbConnection =  DB.getInstance();
            String selectTableSQL = "SELECT * from deal";
            statement = dbConnection.prepareStatement(selectTableSQL);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String dealtID = rs.getString("id");
                if (dealtID.equals(id)) {
                    String date = rs.getString("date");
                    String fk_product = rs.getString("fk_id_product");
                    String fk_client = rs.getString("fk_id_client");
                    String price = rs.getString("priceclient");
                    String res = rs.getString("result");
                    return new Deal(id, date, fk_client, fk_product,price,res);
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
    public List<Deal> getDealAll() {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        List<Deal> list = new ArrayList<>();
        try {
            dbConnection =  DB.getInstance();
            String selectTableSQL = "SELECT * from deal";
            statement = dbConnection.prepareStatement(selectTableSQL);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new Deal(rs.getString("id"),rs.getString("date"),
                        rs.getString("fk_id_client"),rs.getString("fk_id_product"),
                        rs.getString("priceclient"),rs.getString("result")));
            }
            rs.close();
            dbConnection.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public Deal addDeal(Deal deal) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        int DealID = 0;
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        try (Connection c =  DB.getInstance()){
            dbConnection =  DB.getInstance();
            String selectTableSQL = "SELECT * from deal";
            statement = dbConnection.prepareStatement(selectTableSQL);
            ResultSet rs = statement.executeQuery();
            if (deal.getRes().equals("1")) {
                String sql = "INSERT INTO deal VALUES " + " ('" + deal.getDealId() + "','" + stamp
                        + "','" + deal.getFk_id_product() + "','" + deal.getFk_id_client() + "','" + deal.getPriceClient() +
                        "','" + deal.getRes() + "');";
                System.out.println(sql);
                Statement stmt = null;
                stmt = c.createStatement();
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
                rs.close();
                dbConnection.close();
                statement.close();
            } else {
                while (rs.next()) {
                    if (DealID < Integer.parseInt(rs.getString("id"))) {
                        DealID = Integer.parseInt(rs.getString("id"));
                    }
                }
                DealID++;
                statement.close();
                rs.close();

                String sql = "INSERT INTO deal VALUES " + " ('" + DealID + "','" + stamp
                        + "','" + deal.getFk_id_client() + "','" + deal.getFk_id_product() + "','" + deal.getPriceClient() +
                        "','" + deal.getRes() + "');";
                Statement stmt = null;
                stmt = c.createStatement();
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
                rs.close();
                dbConnection.close();
                statement.close();
            }
        }

           catch(SQLException e){
                e.printStackTrace();
            }
        return new Deal(String.valueOf(DealID), stamp.toString(), deal.getFk_id_client(),
                deal.getFk_id_product(), deal.getPriceClient(),deal.getRes());
    }
    public Deal updateDeal(Deal deal) throws SQLException {
        Deal deal1 = getDeal(deal.getDealId());
        deal1.setDealId(deal.getDealId());
        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        deal1.setDate(date.toString());
        deal1.setFk_id_client(deal.getFk_id_client());
        deal1.setFk_id_product(deal.getFk_id_product());
        deal1.setPriceClient(deal.getPriceClient());
        deal1.setRes(deal.getRes());
        delete(deal.getDealId());
        return addDeal(deal1);
    }
    public void delete(String id){
        String sql = "DELETE from deal where id = " + id +";";
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
