package qwer.entity;

import qwer.dao.SellerDAO;

public class Product {
    String productName;
    String productId;
    String size;
    String description;
    String price;
    String fk_seller;
    Seller seller;

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productId='" + productId + '\'' +
                ", size='" + size + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", fk_seller='" + fk_seller + '\'' +
                ", seller=" + seller +
                '}';
    }

    public Product(){
    }

    public Product(String productName,String productId, String size, String description,
                   String price, String fk_seller) {
        this.productName = productName;
        this.productId = productId;
        this.size = size;
        this.description = description;
        this.price = price;
        this.fk_seller = fk_seller;
        this.seller = SellerDAO.getSeller(this.fk_seller);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
        public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getFk_seller() {
        return fk_seller;
    }

    public void setFk_seller(String fk_seller) {
        this.fk_seller = fk_seller;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
