package com.example;
import java.util.Objects;

public class AdressEntry {
    private String name;
    private String lastName;
    private String street;
    private String state;
    private String postalCode;
    private String email;
    private String phone;

    public AdressEntry(String name, String lastName, String street, String state, String postalCode, String email,
            String phone) {
        this.name = name;
        this.lastName = lastName;
        this.street = street;
        this.state = state;
        this.postalCode = postalCode;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "direccion:" + "\n" + "nombre = " + name + "\n" + "apellido = " + lastName + "\n" + "calle = " + street
                + "\n"
                + "estado = "
                + state + "\n" + "codigo postal = " + postalCode + "\n" + "correo electrónico = " + email
                + "\n" + "telefono = " + phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
