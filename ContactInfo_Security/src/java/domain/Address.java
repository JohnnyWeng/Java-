package domain;
import java.io.Serializable;

public class Address implements Serializable{
    
    private String address1;
    private String address2;
    private String city;
    private String country;
    private String postcode;

    public Address() {
    }
    public Address(String address1, String address2, String city, String country, String postcode) {
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.country = country;
        this.postcode = postcode;
    }

    public String getAddress1() {
        return address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    
    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
}
