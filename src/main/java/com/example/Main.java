package com.example;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Menu menu = new Menu();
        AdressBook adressList = new AdressBook();
        Scanner scan = new Scanner(System.in);
        FileManagement.openFileViaExplorer();
        String option = "a";
        while (true) {
            option = menu.displayMenu();
            switch (option) {
                case "a":
                    try {
                        System.out.println("Debug: Before calling uploadAdressFromFile()");

                        adressList.uploadAdressFromFile(FileManagement.openFileViaExplorer());
                        System.out.println("Debug: After calling uploadAdressFromFile()");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                case "b":

                    break;
                case "c":

                    break;
                case "d":
                    System.out.print(ConsoleColors.CYAN + "Ingresa el texto de busqueda: " + ConsoleColors.RED);
                    String search = scan.nextLine();
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