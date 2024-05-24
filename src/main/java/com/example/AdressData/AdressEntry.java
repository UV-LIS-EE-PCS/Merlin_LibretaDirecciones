package com.example.AdressData;

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
        return "nombre = " + name + "\n" + "apellido = " + lastName + "\n" + "calle = " + street
                + "\n"
                + "estado = "
                + state + "\n" + "codigo postal = " + postalCode + "\n" + "correo electrónico = " + email
                + "\n" + "telefono = " + phone;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object.getClass() != this.getClass()) {
            return false;
        }
        AdressEntry compare = (AdressEntry) object;
        return compare.name.strip().toLowerCase().equals(name.strip().toLowerCase()) &&
                compare.lastName.strip().toLowerCase().equals(lastName.strip().toLowerCase()) &&
                compare.street.strip().toLowerCase().equals(street.strip().toLowerCase()) &&
                compare.state.strip().toLowerCase().equals(state.strip().toLowerCase()) &&
                compare.postalCode.strip().toLowerCase().equals(postalCode.strip().toLowerCase()) &&
                compare.email.strip().toLowerCase().equals(email.strip().toLowerCase()) &&
                compare.phone.strip().toLowerCase().equals(phone.strip().toLowerCase());
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