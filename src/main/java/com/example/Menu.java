package com.example;

import java.util.Scanner;
import java.io.FileNotFoundException;

public class Menu {
    private AdressBook adressList = new AdressBook();

    public Menu() throws FileNotFoundException {

    }

    public void options() {
        System.out.println("=============================================");
        System.out.println(
                "|           " + ConsoleColors.PURPLE_BOLD + "Selecciona una opcion" + ConsoleColors.BLACK
                        + "           |");
        System.out.println("=============================================");
        System.out.println("|           " + ConsoleColors.CYAN_BOLD_BRIGHT + "Opciones:" + ConsoleColors.BLACK
                + "                       |");
        System.out.println(
                "|                    " + ConsoleColors.YELLOW + "(a)" + ConsoleColors.BLACK + " Cargar archivo     |");
        System.out.println(
                "|                    " + ConsoleColors.YELLOW_BRIGHT + "(b)" + ConsoleColors.BLACK
                        + " Agregar            |");
        System.out.println(
                "|                    " + ConsoleColors.PURPLE + "(c)" + ConsoleColors.BLACK + " Eliminar           |");
        System.out.println(
                "|                    " + ConsoleColors.CYAN + "(d)" + ConsoleColors.BLACK + " Buscar             |");
        System.out.println(
                "|                    " + ConsoleColors.GREEN + "(e)" + ConsoleColors.BLACK + " Mostrar            |");
        System.out.println("|                    " + ConsoleColors.CYAN_BRIGHT + "(f)" + ConsoleColors.BLACK
                + " Exportar archivo   |");
        System.out.println(
                "|                    " + ConsoleColors.RED + "(g)" + ConsoleColors.BLACK + " Salir              |");
        System.out.println("=============================================");
    }

    public void displayMenu() throws FileNotFoundException {
        System.out.println(
                ConsoleColors.BLUE_BOLD + "==================Bienvenido=================" + ConsoleColors.BLACK + "\n");
        String option;
        Scanner scan = new Scanner(System.in);
        while (true) {
            options();
            System.out.print(ConsoleColors.RED_BOLD + "$ ");
            option = scan.nextLine();
            System.out.println(ConsoleColors.BLACK);
            switch (option) {
                case "a":

                    break;
                case "b":

                    break;
                case "c":

                    break;
                case "d":

                    break;
                case "e":

                    break;
                case "f":

                    break;
                case "g":

                    break;

                default:
                    System.out.println(ConsoleColors.PURPLE + "Selecciona una opción valida!" + ConsoleColors.BLACK);
                    break;
            }

        }

    }

}
