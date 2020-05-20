package qwer.entity;

import qwer.dao.ClientDAO;
import qwer.dao.ProductDAO;

import java.sql.SQLException;

public class Deal {
    String dealId;
    String date;
    String fk_id_client;
    String fk_id_product;
    String priceClient;
    Product product;
    Client client;
    String res;

    @Override
    public String toString() {
        return "Deal{" +
                "dealId='" + dealId + '\'' +
                ", date='" + date + '\'' +
                ", fk_id_client='" + fk_id_client + '\'' +
                ", fk_id_product='" + fk_id_product + '\'' +
                ", priceClient='" + priceClient + '\'' +
                ", product=" + product +
                ", client=" + client +
                ", res='" + res + '\'' +
                '}';
    }




    public Deal(String dealId, String data, String fk_id_client,
                String fk_id_product, String priceClient, String res) throws SQLException {
        this.dealId = dealId;
        this.date = data;
        this.fk_id_client = fk_id_client;
        this.fk_id_product = fk_id_product;
        this.priceClient = priceClient;
        this.res = res;
        this.product = ProductDAO.getProduct(this.fk_id_product);
        this.client = ClientDAO.getClient(this.fk_id_client);
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFk_id_client() {
        return fk_id_client;
    }

    public void setFk_id_client(String fk_id_client) {
        this.fk_id_client = fk_id_client;
    }

    public String getFk_id_product() {
        return fk_id_product;
    }

    public void setFk_id_product(String fk_id_product) {
        this.fk_id_product = fk_id_product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getPriceClient() {
        return priceClient;
    }

    public void setPriceClient(String priceClient) {
        this.priceClient = priceClient;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}
