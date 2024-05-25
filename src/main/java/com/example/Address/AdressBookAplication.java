package com.example.Address;

import java.io.FileNotFoundException;

/**
 * AdressBookAplication maneja toda la aplicacion
 */
public class AdressBookAplication {
    /**
     * default constructor
     */
    AdressBookAplication() {

    }

    /**
     * metodo main
     * 
     * @param args argumentos de ejecucion
     * @throws FileNotFoundException no encuentra el archivo
     */
    public static void main(String[] args) throws FileNotFoundException {
        Menu menu = new Menu();
        menu.displayMenu();
    }

}