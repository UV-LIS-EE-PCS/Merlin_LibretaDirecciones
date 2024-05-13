package com.example;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        AdressBook prueba = new AdressBook();
        AdressEntry entrada = new AdressEntry("juan",
                "malvoro", "nicaragua", "veracruz", "024343", "rafit@gmail.com",
                "021221321232");
        prueba.addAddress(entrada);
        AdressEntry entrada2= new AdressEntry("juan2",
                "malvoro", "nicaragua", "veracruz", "024343", "rafit@gmail.com",
                "021221321232");
        prueba.addAddress(entrada2);
        prueba.fileUpload();
        prueba.searchAdress("a");
        prueba.showAdress();
        System.exit(0);
    }
}
