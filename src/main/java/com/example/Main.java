package com.example;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {
    private static Scanner scan = new Scanner(System.in);

    private static void toSearch(AdressBook adressList) {
        System.out.print(ConsoleColors.CYAN + "Ingresa el texto de busqueda: " + ConsoleColors.RED);
        String search = scan.nextLine();
        adressList.searchAdress(search);
    }

    private static void toAdd(AdressBook adressList) {
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
    }

    private static void toFileUpload(AdressBook adressList) throws FileNotFoundException {
        System.out.println(ConsoleColors.CYAN + "Selecciona la ruta del archivo por la ventana");
        adressList.uploadAdressFromFile(FileManagement.openFileViaExplorer());
    }

    private static void toExit() {
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
    }

    private static void toDelete(AdressBook adressList) {
        System.out.print(ConsoleColors.CYAN + "¿Qué registro deseas eliminar?: " + ConsoleColors.RED);
        String search = scan.nextLine();

        // Filtrar direcciones que coinciden con la búsqueda
        ArrayList<AdressEntry> adressToDelete = adressList.filterAdress(search);
        if (!adressToDelete.isEmpty()) {
            System.out.println(ConsoleColors.BLACK + "Coincidencias:");
            for (AdressEntry entry : adressToDelete) {
                System.out.println(adressToDelete.indexOf(entry) + "."
                        + AdressBook.highlightSearch(entry.getName(), search));
            }

            System.out.println("¿Qué opción deseas?:" +
                    ConsoleColors.CYAN
                    + "\nColoca en forma de lista las direcciones a eliminar:" + ConsoleColors.BLACK
                    + ConsoleColors.CYAN_BOLD
                    + " [1,2,3,4]" + ConsoleColors.BLUE
                    + "\nEliminar todas las coincidencias " + ConsoleColors.BLUE_BOLD + "[e]" + ConsoleColors.PURPLE
                    + "\nCancelar" + ConsoleColors.PURPLE_BOLD + " [c]" + ConsoleColors.RED_BOLD);
            System.out.print("$ " + ConsoleColors.RED);
            String deleteOption = scan.nextLine();

            switch (deleteOption) {
                case "e":
                    // Eliminar todas las coincidencias
                    for (AdressEntry entry : adressToDelete) {
                        adressList.deleteAdress(entry);
                    }
                    break;
                case "c":
                    // Cancelar
                    System.out.println("Operación cancelada.");
                    break;
                default:
                    if (regexComparation("\\b\\d+(,\\d+)*\\b", deleteOption)) {
                        String[] adressToRemove = deleteOption.split(",");
                        for (String indexRemove : adressToRemove) {
                            int index = Integer.parseInt(indexRemove);
                            // Asegúrate de que el índice esté dentro del rango de `adressToDelete`
                            if (index >= 0 && index < adressToDelete.size()) {
                                AdressEntry entry = adressToDelete.get(index);
                                adressList.deleteAdress(entry);
                            } else {
                                System.out.println("Índice fuera de los límites: " + index);
                            }
                        }
                    } else {
                        System.out.println("Formato de entrada inválido.");
                    }
                    break;
            }
        } else {
            System.out.println("No se encontraron coincidencias.");
        }
    }

    private static boolean regexComparation(String regexExpresion, String string) {
        return Pattern.matches(regexExpresion, string);

    }

    public static void main(String[] args) throws FileNotFoundException {
        Menu menu = new Menu();
        AdressBook adressList = new AdressBook();
        Scanner scan = new Scanner(System.in);
        String option = "a";
        while (true) {
            option = menu.displayMenu();
            switch (option) {
                case "a":
                    toFileUpload(adressList);
                    break;
                case "b":
                    toAdd(adressList);
                    break;
                case "c":
                    toDelete(adressList);
                    break;
                case "d":
                    toSearch(adressList);
                    break;
                case "e":
                    adressList.showAdress();
                    break;
                case "f":

                    break;
                case "g":
                    toExit();
                default:
                    System.out.println(ConsoleColors.PURPLE + "Selecciona una opción valida!" +
                            ConsoleColors.BLACK);
                    break;
            }
        }

    }
}