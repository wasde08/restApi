package qwer.entity;

public class Seller {
    String sellerId;
    String sellerName;
    String experience;
    String passUser;
     int check;



    public Seller(String sellerId, String sellerName, String phoneNumber, String passUser) {
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.experience = phoneNumber;
        this.passUser = passUser;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPassUser() {
        return passUser;
    }

    public void setPassUser(String passUser) {
        this.passUser = passUser;
    }
    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "sellerId='" + sellerId + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", experience='" + experience + '\'' +
                ", passUser='" + passUser + '\'' +
                '}';
    }
}
