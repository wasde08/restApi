package qwer.dao;

import qwer.entity.Client;
import qwer.entity.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import static qwer.dao.DB.getDBConnection;

public class MessageDAO {
    public static Message getMessage(String id) {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        try {
            dbConnection =  DB.getInstance();
            String selectTableSQL = "SELECT * from message";
            statement = dbConnection.prepareStatement(selectTableSQL);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String messagetID = rs.getString("iddeal");
                if (messagetID.equals(id)) {
                    String text = rs.getString("text");
                    String idDeal = rs.getString("idDeal");
                    return new Message(id, text,idDeal);
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
    public List<Message> getMessageAll() {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        List<Message> list = new ArrayList<>();
        try {
            dbConnection =  DB.getInstance();
            String selectTableSQL = "SELECT * from message";
            statement = dbConnection.prepareStatement(selectTableSQL);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new Message(rs.getString("id"), rs.getString("text"),
                        rs.getString("idDeal")));
            }
            rs.close();
            dbConnection.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public Message addMessage(Message message) {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        int MessageID = 0;
        try {
            dbConnection =  DB.getInstance();
            String selectTableSQL = "SELECT * from message";
            statement = dbConnection.prepareStatement(selectTableSQL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (MessageID<Integer.parseInt(rs.getString("id"))){
                    MessageID = Integer.parseInt(rs.getString("id"));
                }
            }
            MessageID = MessageID +1;
            statement.close();
            rs.close();
            String sql = "INSERT INTO message VALUES " + " ('" +  MessageID  + "','"
                    + message.getText()  + "','" + message.getIdDeal()+ "');";
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
            return null;
        }
        return new Message(String.valueOf(MessageID),message.getText(),message.getIdDeal());
    }
    public void delete(String id){
        String sql = "DELETE from message where id = " + id +";";
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
