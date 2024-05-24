package com.example.Adress;

import java.io.FileNotFoundException;
import java.util.Scanner;

import com.example.utilities.ConsoleColors;

public class AdressBookAplication {

    public static void main(String[] args) throws FileNotFoundException {
        Menu menu = new Menu();
        Scanner scan = new Scanner(System.in);
        System.out.println(
                ConsoleColors.BLUE_BOLD + "==================Bienvenido=================" + ConsoleColors.BLACK + "\n");
        String option = "a";
        while (true) {
            menu.displayMenu();
            System.out.print(ConsoleColors.RED_BOLD + "$ " + ConsoleColors.RED);
            option = scan.nextLine();
            System.out.println(ConsoleColors.BLACK);
            switch (option.toLowerCase()) {
                case "a":
                    menu.exitToMenu(() -> {
                        try {
                            menu.toFileUpload();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }, "¿Importar otro archivo?", scan);
                    menu.interrupt();
                    break;
                case "b":
                    menu.exitToMenu(() -> {
                        menu.toAdd(scan);
                    }, "¿agregar otro registro?", scan);
                    break;
                case "c":
                    menu.exitToMenu(() -> {
                        menu.toDelete(scan);
                    }, "¿Eliminar otro registro?", scan);
                    break;
                case "d":
                    menu.exitToMenu(() -> {
                        menu.toSearch(scan);
                    }, "¿Volver a buscar?", scan);
                    break;
                case "e":
                    menu.exitToMenu(() -> {
                        menu.toShow();
                    }, "¿volver a mostrar?", scan);
                    break;
                case "f":
                    menu.exitToMenu(() -> {
                        menu.toExport(scan);
                    }, "¿exportar de nuevo?", scan);
                    menu.interrupt();
                    break;
                case "g":
                    menu.toExit(scan);
                    continue;
                default:
                    System.out.println(ConsoleColors.PURPLE + "Selecciona una opción valida!" +
                            ConsoleColors.BLACK);
                    break;
            }
        }
    }

}