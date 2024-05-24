package com.example.Adress;

import java.io.FileNotFoundException;
import java.util.Scanner;

import com.example.utilities.HighlightText;

public class AdressBookAplication {

    public static void main(String[] args) throws FileNotFoundException {
        Menu menu = new Menu();
        Scanner scan = new Scanner(System.in);
        System.out.println(
                HighlightText.BLUE_BOLD + "==================Bienvenido=================" + HighlightText.BLACK + "\n");
        String option = "a";
        while (true) {
            menu.displayMenu();
            System.out.print(HighlightText.RED_BOLD + "$ " + HighlightText.RED);
            option = scan.nextLine();
            System.out.println(HighlightText.BLACK);
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
                    boolean isCorrect = false;
                    while (!isCorrect) {
                        System.out.print(
                                "estas seguro de salir? " + HighlightText.RED + " [si]" + HighlightText.BLACK
                                        + HighlightText.BLUE + " [no] : " + HighlightText.BLACK + HighlightText.RED);
                        String exit = scan.nextLine();
                        System.out.println(HighlightText.BLACK);
                        switch (exit.toLowerCase()) {
                            case "si":
                                System.out.println("Hasta luego");
                                System.exit(0);
                            case "no":
                                isCorrect = true;
                                break;

                            default:
                                System.out.println(
                                        HighlightText.PURPLE + "Selecciona una opción valida!" + HighlightText.BLACK);
                                break;

                        }
                    }
                    continue;
                default:
                    System.out.println(HighlightText.PURPLE + "Selecciona una opción valida!" +
                            HighlightText.BLACK);
                    break;
            }
        }
    }

}