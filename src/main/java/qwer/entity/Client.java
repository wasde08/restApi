package qwer.entity;

public class Client {
    String clientId;
    String name;
    String phoneNumber;
    String passuser;

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    int check;

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passuser='" + passuser + '\'' +
                '}';
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassuser() {
        return passuser;
    }

    public void setPassuser(String passuser) {
        this.passuser = passuser;
    }

    public Client(String clientId, String name, String phoneNumber, String passuser) {
        this.clientId = clientId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.passuser = passuser;
    }
}
