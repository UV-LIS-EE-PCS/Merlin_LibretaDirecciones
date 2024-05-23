package com.example.Adress;
import com.example.AdressData.*;
import com.example.utilities.*;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Menu {
    private Scanner scan = new Scanner(System.in);
    private AdressBook book;

    public Menu() {
        try {
            this.book = new AdressBook(); 
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo contactos.json");
        }
    }
    
    public Menu(AdressBook book) {
        try {
            this.book = book; 
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo contactos.json");
        }
    }

    @FunctionalInterface
    interface Function {
        void execute();
    }

    public void toSearch() {

        System.out.print(ConsoleColors.CYAN + "Ingresa el texto de busqueda: " + ConsoleColors.RED);
        String search = scan.nextLine();
        book.searchAdress(search);

    }

    public void toAdd() {

        boolean isCorrectEntry = false;
        String name = "no data", lastName = "no data", street = "no data", state = "no data", postalCode = "no data",
                email = "no data",
                phone = "no data";

        System.out.print(ConsoleColors.BLACK + "Nombre: " + ConsoleColors.RED);
        name = scan.nextLine();

        System.out.print(ConsoleColors.BLACK + "Apellido: " + ConsoleColors.RED);
        lastName = scan.nextLine();

        System.out.print(ConsoleColors.BLACK + "Calle: " + ConsoleColors.RED);
        street = scan.nextLine();

        System.out.print(ConsoleColors.BLACK + "Estado: " + ConsoleColors.RED);
        state = scan.nextLine();

        isCorrectEntry = false;
        while (!isCorrectEntry) {
            System.out.print(ConsoleColors.BLACK + "Código postal: " + ConsoleColors.RED);
            postalCode = scan.nextLine();
            if (postalCode.length() == 5 && regexComparation("\\d+", postalCode)) {
                isCorrectEntry = true;
            } else {
                System.out.println("codigo postal invalido, debe de ser un numero y contener 5 caracteres");
            }
        }

        isCorrectEntry = false;
        while (!isCorrectEntry) {
            System.out.print(ConsoleColors.BLACK + "Correo Electrónico: " + ConsoleColors.RED);
            email = scan.nextLine();
            if (regexComparation("^(.+)@(.+)$", email)) {
                isCorrectEntry = true;
            } else {
                System.out.println("inserta un correo valido Ejemplo : rafael@gmail.com");
            }
        }

        isCorrectEntry = false;
        while (!isCorrectEntry) {
            System.out.print(ConsoleColors.BLACK + "Número de teléfono: " + ConsoleColors.RED);
            phone = scan.nextLine();
            if (phone.length() == 10 && regexComparation("\\d+", phone)) {
                isCorrectEntry = true;
            } else {
                System.out.println("Numero invalido , el número debe de contener 10 caracteres");
            }
        }

        AdressEntry newEntry = new AdressEntry(name, lastName, street, state, postalCode, email, phone);
        book.addAddress(newEntry);
        System.out.println(
                "\n~" + ConsoleColors.PURPLE_BOLD + "+Dirección agregada con exito!" + ConsoleColors.BLACK);

    }

    public void toFileUpload() throws FileNotFoundException {

        System.out.println(ConsoleColors.CYAN + "Selecciona la ruta del archivo por la ventana");
        String path = FileManagement.openFileViaExplorer();

        if ("CANCELLED".equals(path)) {
            System.out.println(ConsoleColors.RED + "Operación cancelada. Volviendo al menú." + ConsoleColors.BLACK);
            return;
        }

        try {
            this.book.uploadAdressFromFile(path);
        } catch (FileNotFoundException e) {
            System.out.println(
                    ConsoleColors.RED + "Archivo no encontrado. Inténtalo de nuevo." + ConsoleColors.BLACK);
            return;
        }

    }

    public void toExit() {
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

    public void toDelete() {

        System.out.print(ConsoleColors.CYAN_BOLD + "¿Qué registro deseas eliminar?: " + ConsoleColors.RED);
        String search = scan.nextLine();

        // Filtrar direcciones que coinciden con la búsqueda
        ArrayList<AdressEntry> adressToDelete = book.filterAdress(search);
        if(adressToDelete.size()==1){
            AdressEntry entry = adressToDelete.get(0);
            System.out.print(ConsoleColors.BLACK+"Deseas eliminar este registro?\n"+entry.toString()+" \nescribe "+ConsoleColors.BLUE+"si para aceptar y "+ConsoleColors.BLACK+ConsoleColors.RED+"cualquier otra cadena para cancelar"+ConsoleColors.BLACK+": ");
            String cancel = scan.nextLine();
            if(cancel.equalsIgnoreCase("si")){
                System.out.println();
                book.deleteAdress(entry);
                System.out.println(ConsoleColors.YELLOW_BOLD + entry.getName()+" "+entry.getLastName()
                + ConsoleColors.RED_BOLD + " eliminado correctamente"+ConsoleColors.BLACK);
            }else{
                System.out.println(ConsoleColors.BLUE+"operacion cancelada"+ConsoleColors.BLACK);
            }
        }
        else if (!adressToDelete.isEmpty()) {
            System.out.println(ConsoleColors.CYAN_BOLD + "Coincidencias:");
            for (AdressEntry entry : adressToDelete) {
                System.out.println(ConsoleColors.BLUE + adressToDelete.indexOf(entry) + ConsoleColors.BLACK + "."
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
                    System.out.println(ConsoleColors.PURPLE + "Registros eliminados:" + ConsoleColors.BLACK);
                    for (AdressEntry entry : adressToDelete) {
                        book.deleteAdress(entry);
                        System.out.println(ConsoleColors.YELLOW_BOLD+entry.getName()+" " +entry.getLastName()+ConsoleColors.BLACK);
                    }
                    break;
                case "c":
                    // Cancelar
                    System.out.println(ConsoleColors.CYAN + "Operación cancelada." + ConsoleColors.BLACK);
                    break;
                default:
                    if (regexComparation("\\b\\d+(,\\d+)*\\b", deleteOption)) {
                        String[] adressToRemove = deleteOption.split(",");
                        for (String indexRemove : adressToRemove) {
                            int index = Integer.parseInt(indexRemove);
                            if (index >= 0 && index < adressToDelete.size()) {
                                AdressEntry entry = adressToDelete.get(index);
                                book.deleteAdress(entry);
                                System.out.println(ConsoleColors.YELLOW_BOLD + index + "." + entry.getName()+" "+entry.getLastName()
                                        + ConsoleColors.RED_BOLD + " eliminado correctamente"+ConsoleColors.BLACK);
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
            System.out.println(ConsoleColors.BLACK+"No se encontraron coincidencias.");
        }

    }

    public void toShow() {

        book.showAdress();

    }

    public void exitToMenu(Function function, String message) {
        boolean isCorrect = false;
        while (!isCorrect) {
            function.execute();
            System.out.print(
                    message + ConsoleColors.RED + " [si]" + ConsoleColors.BLACK
                            + ConsoleColors.BLUE + " [no] : " + ConsoleColors.BLACK + ConsoleColors.RED);
            String exit = scan.nextLine();
            System.out.println(ConsoleColors.BLACK);
            switch (exit) {
                case "no":
                    isCorrect = true;
                case "si":
                    break;
                default:
                    System.out.println(
                            ConsoleColors.PURPLE + "Selecciona una opción valida!" + ConsoleColors.BLACK);
                    break;

            }
        }
    }

    public void toExport() {

        System.out.print("Coloca el nombre del archivo sin la extension: " + ConsoleColors.RED);
        String name = scan.nextLine();
        System.out.println(ConsoleColors.CYAN + "Selecciona la ruta del archivo por la ventana");
        String directoryPath = FileManagement.openDirectoryViaExplorer();

        if ("CANCELLED".equals(directoryPath)) {
            System.out.println(ConsoleColors.RED + "Operación cancelada. Volviendo al menú." + ConsoleColors.BLACK);
            return;
        }

        System.out.println("selecciona el directorio del archivo desde la ventana...");
        book.exportAdressBook(directoryPath + "\\" + name + ".txt");

    }

    private boolean regexComparation(String regexExpresion, String string) {
        return Pattern.matches(regexExpresion, string);

    }

    public void interrupt() {
        if (Thread.currentThread().isInterrupted()) {
            Thread.interrupted();
        }
    }

    public void displayMenu() {
        System.out.println("=============================================");
        System.out.println(
                "|           " + ConsoleColors.GREEN_BOLD + "Selecciona una opcion" + ConsoleColors.BLACK
                        + "           |");
        System.out.println("=============================================");
        System.out.println("|           " + ConsoleColors.GREEN_BRIGHT + "Opciones:" + ConsoleColors.BLACK
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

    // public void s() throws FileNotFoundException {
    //     System.out.println(
    //             ConsoleColors.BLUE_BOLD + "==================Bienvenido=================" + ConsoleColors.BLACK + "\n");
    //     String option = "a";
    //     while (true) {
    //         options();
    //         System.out.print(ConsoleColors.RED_BOLD + "$ " + ConsoleColors.RED);
    //         option = scan.nextLine();
    //         System.out.println(ConsoleColors.BLACK);
    //         String backToMenu = "¿Repetir accion?";
    //         switch (option.toLowerCase()) {
    //             case "a":
    //                 exitToMenu(() -> {
    //                     try {
    //                         toFileUpload();
    //                     } catch (FileNotFoundException e) {
    //                         e.printStackTrace();
    //                     }
    //                 }, backToMenu);
    //                 interrupt();
    //                 break;
    //             case "b":
    //                 exitToMenu(() -> {
    //                     toAdd();
    //                 }, backToMenu);
    //                 break;
    //             case "c":
    //                 exitToMenu(() -> {
    //                     toDelete();
    //                 }, backToMenu);
    //                 break;
    //             case "d":
    //                 exitToMenu(() -> {
    //                     toSearch();
    //                 }, backToMenu);
    //                 break;
    //             case "e":
    //                 exitToMenu(() -> {
    //                     toShow(book);
    //                 }, backToMenu);
    //                 break;
    //             case "f":
    //                 exitToMenu(() -> {
    //                     toExport();
    //                 }, backToMenu);
    //                 interrupt();
    //                 break;
    //             case "g":
    //                 toExit();
    //                 continue;
    //             default:
    //                 System.out.println(ConsoleColors.PURPLE + "Selecciona una opción valida!" +
    //                         ConsoleColors.BLACK);
    //                 break;
    //         }
    //     }
    

}
