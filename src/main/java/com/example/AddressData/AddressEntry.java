package com.example.addressdata;

import de.vandermeer.asciitable.AsciiTable;

/***
 * AddressEntry es una clase que maneja todas las entradas por seperado
 */
public class AddressEntry {
    /**
     * name from AddressEntry
     */
    private String name;
    /**
     * lastName from AddressEntry
     */
    private String lastName;
    /**
     * street from AddressEntry
     */
    private String street;
    /**
     * state from AddressEntry
     */
    private String state;
    /**
     * postalCode from AddressEntry
     */
    private String postalCode;
    /**
     * email from AddressEntry
     */
    private String email;
    /**
     * phone from AddressEntry
     */
    private String phone;

    /**
     * default constructor
     * 
     * @param name       from AddressEntry
     * @param lastName   from AddressEntry
     * @param street     from AddressEntry
     * @param state      from AddressEntry
     * @param postalCode from AddressEntry
     * @param email      from AddressEntry
     * @param phone      from AddressEntry
     */
    public AddressEntry(String name, String lastName, String street, String state, String postalCode, String email,
            String phone) {
        this.name = name;
        this.lastName = lastName;
        this.street = street;
        this.state = state;
        this.postalCode = postalCode;
        this.email = email;
        this.phone = phone;
    }

    /**
     * toString de la clase
     * 
     * @return String
     */
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
        AddressEntry compare = (AddressEntry) object;
        return compare.name.strip().toLowerCase().equals(name.strip().toLowerCase()) &&
                compare.lastName.strip().toLowerCase().equals(lastName.strip().toLowerCase()) &&
                compare.street.strip().toLowerCase().equals(street.strip().toLowerCase()) &&
                compare.state.strip().toLowerCase().equals(state.strip().toLowerCase()) &&
                compare.postalCode.strip().toLowerCase().equals(postalCode.strip().toLowerCase()) &&
                compare.email.strip().toLowerCase().equals(email.strip().toLowerCase()) &&
                compare.phone.strip().toLowerCase().equals(phone.strip().toLowerCase());
    }

    /**
     * get name
     * 
     * @return name from AddressEntry
     */
    public String getName() {
        return name;
    }

    /**
     * set name
     * 
     * @param name name from AddressEntry
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     * get lastName
     * 
     * @return lasName from AddressEntry
     */
    public String getLastName() {
        return lastName;
    }

    /***
     * setLastname
     * 
     * @param lastName lastname from AddressEntry
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * get Street
     * 
     * @return street from AddressEntry
     */
    public String getStreet() {
        return street;
    }

    /**
     * set street
     * 
     * @param street street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * get state
     * 
     * @return state from AddressEntry
     */
    public String getState() {
        return state;
    }

    /**
     * set state
     * 
     * @param state state from AddressEntry
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * get postal code
     * 
     * @return postal code from AddressEntry
     */
    public String getPostalCode() {
        return postalCode;
    }

    /***
     * set postal code
     * 
     * @param postalCode postal code from AddressEntry
     */

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * get email
     * 
     * @return email from AddressEntry
     */
    public String getEmail() {
        return email;
    }

    /**
     * set email
     * 
     * @param email email from AddressEntry
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * get phone
     * 
     * @return phone from AddressEntry
     */
    public String getPhone() {
        return phone;
    }

    /**
     * set phone
     * 
     * @param phone phone from AddressEntry
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * genera una table de una entrada
     * 
     * @return tabla con toda la informacion de un AddressEntry
     */
    public String generateInfoTable() {

        AsciiTable dataText = new AsciiTable();
        dataText.addRule();
        dataText.addRow("Nombre", "Apellido", "Calle", "Estado", "Codigo Postal", "Correo Electrónico", "Telefono");
        dataText.addRule();
        dataText.addRow(name, lastName, street, state,
                postalCode,
                email, phone);
        dataText.addRule();
        String rend = dataText.render(150);
        return rend;
    }

}