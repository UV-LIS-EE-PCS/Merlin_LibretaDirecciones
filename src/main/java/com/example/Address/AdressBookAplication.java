package com.example.address;

import java.io.FileNotFoundException;

/**
 * AdressBookAplication maneja toda la aplicacion
 */
public class AdressBookAplication {
    /**
     *  constructor por defecto
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