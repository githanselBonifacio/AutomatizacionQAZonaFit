package co.com.sofka.models;

public class Customer {
    private String cedula;
    private String email;
    private String name;
    private String lastName;
    private String region;
    private String city;
    private String address;
    private String noAddress;
    private String phone;

    public String getCedula() {return cedula;}

    public void setCedula(String cedula) {this.cedula = cedula;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getRegion() {return region;}

    public void setRegion(String region) {this.region = region;}

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getNoAddress() {return noAddress;}

    public void setNoAddress(String noAddress) {this.noAddress = noAddress;}

    public String getPhone() {return phone;}

    public void setPhone(String phone) {this.phone = phone;}

    @Override
    public String toString() {
        return "\nCustomer{" +
                "cedula='" + cedula + '\n' +
                ", email='" + email + '\n' +
                ", name='" + name + '\n' +
                ", lastName='" + lastName + '\n' +
                ", region='" + region + '\n' +
                ", city='" + city + '\n' +
                ", address='" + address + '\n' +
                ", noAddress='" + noAddress + '\n' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
