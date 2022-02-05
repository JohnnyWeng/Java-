package model;

public class Player {
    int playerID;
    String name;
    String address;
    String city;
    String province;
    String postalCode;

    public Player(int playerID, String name, String address, String city, String province, String postalCode) {
        this.playerID = playerID;
        this.name = name;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }
    
}
