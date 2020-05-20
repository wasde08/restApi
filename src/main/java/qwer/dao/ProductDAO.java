package qwer.dao;


import qwer.entity.Product;
import qwer.entity.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDAO {

    public static Product getProduct(String id) {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        try {
            dbConnection =  DB.getInstance();
            String selectTableSQL = "SELECT * from product";
            statement = dbConnection.prepareStatement(selectTableSQL);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("id");
                if (productID.equals(id)) {
                    String productName = rs.getString("name");
                    String productSize = rs.getString("size");
                    String productDescription = rs.getString("description");
                    String productPrice = rs.getString("price");
                    String fk_id_seller = rs.getString("fk_id_seller");
                    return new Product(productName,productID, productSize, productDescription, productPrice,fk_id_seller);
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
    public List<Product> getProductAll() {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        List<Product> list = new ArrayList<>();
        try {
            dbConnection =  DB.getInstance();
            String selectTableSQL = "SELECT * from product";
            statement = dbConnection.prepareStatement(selectTableSQL);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString("name")
                        ,rs.getString("id"),rs.getString("size"),
                        rs.getString("description"),rs.getString("price"),
                        rs.getString("fk_id_seller")));
                }
            rs.close();
            dbConnection.close();
            statement.close();
            } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public Product addProduct(Product product) {
        Connection dbConnection = null;
        PreparedStatement statement = null;
        int productID = 0;
        try {
            dbConnection =  DB.getInstance();
            String selectTableSQL = "SELECT * from product";
            statement = dbConnection.prepareStatement(selectTableSQL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (productID<Integer.parseInt(rs.getString("id"))){
                    productID = Integer.parseInt(rs.getString("id"));
                    System.out.println();
                }
            }
            productID++;
            statement.close();
            rs.close();
            String sql = "INSERT INTO product (id, size, description, price, name,fk_id_seller)" +
                    " VALUES " + " ('" +  productID  + "','" + product.getSize()+ "','" + product.getDescription()
                    + "','" + product.getPrice()+"','"+product.getProductName()+"','"+product.getFk_seller()+ "');";
            Connection c =  DB.getInstance();
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
            rs.close();
            dbConnection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Product(product.getProductName(),String.valueOf(productID),product.getSize(),
                product.getDescription(),product.getPrice(),product.getFk_seller());
    }
    public Product updateProduct(Product product){
        Product product1 = getProduct(product.getProductId());
        product1.setProductId(product.getProductId());
        product1.setSize(product.getSize());
        product1.setDescription(product.getDescription());
        product1.setPrice(product.getPrice());
        product1.setProductName(product.getProductName());
        delete(product.getProductId());
       return addProduct(product1);
    }
    public void delete(String id){
        String sql = "DELETE from product where id = " + id +";";
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
