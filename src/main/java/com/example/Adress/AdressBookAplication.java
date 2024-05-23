package com.example.Adress;


import java.io.FileNotFoundException;
import java.util.Scanner;

import com.example.utilities.ConsoleColors;




public class AdressBookAplication {

        public static void main(String[] args) throws FileNotFoundException {
            Menu menu = new Menu();
                    System.out.println(
                ConsoleColors.BLUE_BOLD + "==================Bienvenido=================" + ConsoleColors.BLACK + "\n");
            String option = "a";
            while (true) {
                Scanner scan = new Scanner(System.in);
                menu.displayMenu();
                System.out.print(ConsoleColors.RED_BOLD + "$ " + ConsoleColors.RED);
                option = scan.nextLine();
                System.out.println(ConsoleColors.BLACK);
                String backToMenu = "¿Repetir accion?";
                switch (option.toLowerCase()) {
                    case "a":
                        menu.exitToMenu(() -> {
                            try {
                                menu.toFileUpload();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }, backToMenu);
                        menu.interrupt();
                        break;
                    case "b":
                        menu.exitToMenu(() -> {
                            menu.toAdd();
                        }, backToMenu);
                        break;
                    case "c":
                        menu.exitToMenu(() -> {
                            menu.toDelete();
                        }, backToMenu);
                        break;
                    case "d":
                        menu.exitToMenu(() -> {
                            menu.toSearch();
                        }, backToMenu);
                        break;
                    case "e":
                        menu.exitToMenu(() -> {
                            menu.toShow();
                        }, backToMenu);
                        break;
                    case "f":
                        menu.exitToMenu(() -> {
                            menu.toExport();
                        }, backToMenu);
                        menu.interrupt();
                        break;
                    case "g":
                        menu.toExit();
                        continue;
                    default:
                        System.out.println(ConsoleColors.PURPLE + "Selecciona una opción valida!" +
                                ConsoleColors.BLACK);
                        break;
                }
            }
        }

}