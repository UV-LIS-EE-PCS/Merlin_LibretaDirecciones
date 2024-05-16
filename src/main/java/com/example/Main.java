package com.example;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Menu menu = new Menu();
        AdressBook adressList = new AdressBook();
        Scanner scan = new Scanner(System.in);
        String option = "a";
        while (true) {
            option = menu.displayMenu();
            switch (option) {
                case "a":
                    System.out.println(ConsoleColors.CYAN + "Selecciona la ruta del archivo por la ventana");
                    adressList.uploadAdressFromFile(FileManagement.openFileViaExplorer());
                    break;
                case "b":
                    System.out.print(ConsoleColors.CYAN + "Nombre: " + ConsoleColors.RED);
                    String name = scan.nextLine();

                    System.out.print(ConsoleColors.CYAN + "Apellido: " + ConsoleColors.RED);
                    String lastName = scan.nextLine();

                    System.out.print(ConsoleColors.CYAN + "Calle: " + ConsoleColors.RED);
                    String street = scan.nextLine();

                    System.out.print(ConsoleColors.CYAN + "Estado: " + ConsoleColors.RED);
                    String state = scan.nextLine();

                    System.out.print(ConsoleColors.CYAN + "Codigo postal: " + ConsoleColors.RED);
                    String postalCode = scan.nextLine();

                    System.out.print(ConsoleColors.CYAN + "Correo electronico: " + ConsoleColors.RED);
                    String email = scan.nextLine();

                    boolean isCorrectPhone = false;
                    String phone = "";
                    while (!isCorrectPhone) {
                        System.out.print(ConsoleColors.CYAN + "Número de teléfono: " + ConsoleColors.RED);
                        phone = scan.nextLine();
                        if (phone.length() == 10) {
                            isCorrectPhone = true;
                        } else {
                            System.out.println("Numero invalido , el número debe de contener 10 caracteres");
                        }
                    }

                    AdressEntry newEntry = new AdressEntry(name, lastName, street, state, postalCode, email, phone);
                    adressList.addAddress(newEntry);
                    break;
                case "c":
                    System.out.print(ConsoleColors.CYAN + "¿Que registro deseas eliminar?: " + ConsoleColors.RED);
                    String search = scan.nextLine();
                    ArrayList<AdressEntry> adressToDelete = adressList.filterAdress(search);
                    if (!adressToDelete.isEmpty()) {
                        System.out.println(ConsoleColors.BLACK + "coincidencias:");
                        for (AdressEntry entry : adressToDelete) {
                            System.out.println(adressToDelete.indexOf(entry) + "."
                                    + AdressBook.highlightSearch(entry.getName(), search));
                        }

                    }
                    break;
                case "d":
                    System.out.print(ConsoleColors.CYAN + "Ingresa el texto de busqueda: " + ConsoleColors.RED);
                    search = scan.nextLine();
                    adressList.searchAdress(search);
                    break;
                case "e":
                    adressList.showAdress();
                    break;
                case "f":

                    break;
                case "g":
                    boolean isCorrect = false;
                    while (!isCorrect) {
                        System.out.print(
                                "estas seguro de salir? " + ConsoleColors.RED + " [si]" + ConsoleColors.BLACK
                                        + ConsoleColors.BLUE + " [no] : " + ConsoleColors.BLACK + ConsoleColors.RED);
                        String exit = scan.nextLine();
                        System.out.println(ConsoleColors.BLACK);
                        switch (exit) {
                            case "si":
                                System.out.println("Hasta luego");
                                System.exit(0);
                            case "no":
                                isCorrect = true;
                                break;

                            default:
                                System.out.println(
                                        ConsoleColors.PURPLE + "Selecciona una opción valida!" + ConsoleColors.BLACK);
                                break;

                        }
                    }

                default:
                    System.out.println(ConsoleColors.PURPLE + "Selecciona una opción valida!" + ConsoleColors.BLACK);
                    break;
            }
        }

    }
}