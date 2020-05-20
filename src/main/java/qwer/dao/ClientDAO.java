package qwer.dao;

import qwer.entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClientDAO {

    public static Client getClient(String id) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        try {
            dbConnection =  DB.getInstance();
            String selectTableSQL = "SELECT * from client";
            statement = dbConnection.prepareStatement(selectTableSQL);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String clienttID = rs.getString("id");
                if (clienttID.equals(id)) {
                    String name = rs.getString("name");
                    String phoneNumber = rs.getString("phonenumber");
                    return new Client(id, name, phoneNumber, "");
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
    public List<Client> getClientAll() {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        List<Client> list = new ArrayList<>();
        try {
            dbConnection = DB.getInstance();
            String selectTableSQL = "SELECT * from client";
            statement = dbConnection.prepareStatement(selectTableSQL);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new Client(rs.getString("id"),rs.getString("name"),
                        rs.getString("phonenumber"),""));
            }
            rs.close();
            dbConnection.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public Client check(Client client){
        Connection dbConnection = null;
        PreparedStatement statement = null;
        try {
            dbConnection = DB.getInstance();
            String selectTableSQL = "SELECT * from client";
            statement = dbConnection.prepareStatement(selectTableSQL);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String clientIdBD = rs.getString("id");
                String nameBD = rs.getString("name");
                String passBD = rs.getString("passuser");
                System.out.println(nameBD.equals(client.getName())  + " " +passBD.equals(client.getPassuser()) );
                if (nameBD.equals(client.getName())&&passBD.equals(client.getPassuser())) {
                    String phoneNumber = rs.getString("phonenumber");
                    return new Client(clientIdBD, client.getName(), phoneNumber, "");
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

    public Client addClient(Client client) {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        int ClientID = 0;
        try {
            dbConnection =  DB.getInstance();
            String selectTableSQL = "SELECT * from client";
            statement = dbConnection.prepareStatement(selectTableSQL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (ClientID<Integer.parseInt(rs.getString("id"))){
                    ClientID = Integer.parseInt(rs.getString("id"));
                }
            }
            ClientID = ClientID +1;
            statement.close();
            rs.close();
            String sql = "INSERT INTO client VALUES " + " ('" +  ClientID  + "','" + client.getName()
                    + "','" + client.getPhoneNumber() + "','" + client.getPassuser()  + "');";
            Connection c = (Connection) DB.getInstance();
            Statement stmt = null;
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            rs.close();
            dbConnection.close();
            statement.close();
        } catch (SQLException e) {
            return null;
        }
        return new Client(String.valueOf(ClientID),client.getName(),client.getPhoneNumber(),null);
    }
    public Client updateClient(Client client) throws SQLException {
        Client client1 = getClient(client.getClientId());
        client1.setClientId(client.getClientId());
        client1.setName(client.getName());
        client1.setPhoneNumber(client.getPhoneNumber());
        client1.setPassuser(client.getPassuser());
        delete(client.getClientId());
        return addClient(client1);
    }
    public void delete(String id) throws SQLException {
        String sql = "DELETE from client where id = " + id +";";
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
