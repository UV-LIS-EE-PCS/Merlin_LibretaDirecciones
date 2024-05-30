package com.example.address;

import com.example.addressdata.*;
import com.example.utilities.*;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.Pattern;
/**
 * Menu es una clase que maneja un AdressBook.
 */
public class Menu {
    /**
     * libreta de direciones que usa el menu
     */
    private AddressBook book;
    /***
     * directorios de todos los AddressBook que maneja la aplicación
     */
    private ArrayList<String> booksDirectories;
    
    /**
     * directorio donde se guardan todos los addressbook en formato json
     */
    private final String ADDRESSBOOKDIRECTORY = "src/main/java/com/example/info/directorios/";
    /**
     * archivo que contiene las rutas de todos los archivos addressBook en formato .json
     */
    private final String PATHBOOKSDIRECTORIES = "src/main/java/com/example/info/books.json";  
    /**
     * constructor por defecto
     * 
     */
    public Menu() {
       this.booksDirectories = FileManagement.jsonFileToBooksList(PATHBOOKSDIRECTORIES);
    }

    /***
     * 
     */
    public Menu(ArrayList<String> booksDirectories){
    
       this.booksDirectories = booksDirectories;
       FileManagement.writeBookListOnJsonFile(PATHBOOKSDIRECTORIES, booksDirectories);
    }

    /**
     * interfaz funcional para remplazar por expresion lambda
     */
    
    @FunctionalInterface
    interface Function {
        void execute();
        
    }


    /**
     * método que agrega un nuevo addressBook al programa
     * @param scan scanner paraa la entrada del nombre dek addressBook
     * @throws FileNotFoundException errror al no encontrar el archivo
     */
    private void addAddressBook(Scanner scan) throws FileNotFoundException{
        System.out.println(HighlightText.BLACK+"ingresa el nombre de la lista a crear:"+HighlightText.RED);
        String nameList = scan.nextLine();
        String path =ADDRESSBOOKDIRECTORY+nameList.replace(" ", "_")+".json";
        AddressBook newbook = new AddressBook(path);
        book = newbook;  
        booksDirectories.add(path);
        FileManagement.writeBookListOnJsonFile(PATHBOOKSDIRECTORIES,booksDirectories);

    }

    /**
     * método para cambiar entre AdressBook
     * @param scan scanner paraa la entrada del nombre dek addressBook
     * @throws FileNotFoundException errror al no encontrar el archivo
     */
    private void selectAddressBook(Scanner scan) throws FileNotFoundException{
        boolean isCorret = false;
        while (!isCorret) {
            System.out.println(HighlightText.GREEN_BOLD_BRIGHT+"¿que lista deseas usar? selecciona una ");
        for(String bookDirectory:this.booksDirectories ){
            System.out.println(HighlightText.GREEN_BOLD+booksDirectories.indexOf(bookDirectory)+HighlightText.YELLOW+"."+getNameFromPath(bookDirectory) + HighlightText.RED);
        }
        System.out.print(HighlightText.RED_BOLD+"$ "+HighlightText.RED);
        String preIndex = scan.nextLine();
        System.out.println(HighlightText.BLACK);
        if (regexComparation("^\\d+$", preIndex)) {
            int index = Integer.parseInt(preIndex);
            if (index < booksDirectories.size()) {
                AddressBook newbook = new AddressBook(booksDirectories.get(index));
                book = newbook;
                System.out.println("se ha cambiado a la lista: "+HighlightText.BLUE+ getNameFromPath(booksDirectories.get(index))+HighlightText.BLACK);
                isCorret = true;
            } else {
                System.out.println("indice invalido");
            }
        }else {
            System.out.println("indice invalido");
        }
        }
    }
    /**
     * elimina una AddressBook seleccionada
     * @param scan scanner para seleccionar las opciones del metodo
     * @throws FileNotFoundException expecion en caso de no encontrar el archivo
     */
    private void deleteAddressBook(Scanner scan) throws FileNotFoundException{
        if(booksDirectories.size() ==1){
            booksDirectories.remove(booksDirectories.get(0));
            FileManagement.writeBookListOnJsonFile(PATHBOOKSDIRECTORIES,booksDirectories);
            addAddressBook(scan);
            return;
        }
        boolean isCorret = false;
        while (!isCorret) {
            System.out.println(HighlightText.GREEN_BOLD_BRIGHT+"¿que lista deseas eliminar? selecciona una ");
        for(String bookDirectory:this.booksDirectories ){
            System.out.println(HighlightText.GREEN_BOLD+booksDirectories.indexOf(bookDirectory)+HighlightText.YELLOW+"."+getNameFromPath(bookDirectory) + HighlightText.RED);
        }
        System.out.print(HighlightText.RED_BOLD+"$ "+HighlightText.RED);
        String preIndex = scan.nextLine();
        System.out.println(HighlightText.BLACK);
        if (regexComparation("^\\d+$", preIndex)) {
            int index = Integer.parseInt(preIndex);
            if (index < booksDirectories.size()) {
                String pathDirecory = booksDirectories.get(index);
                booksDirectories.remove(pathDirecory);
                FileManagement.writeBookListOnJsonFile(PATHBOOKSDIRECTORIES,booksDirectories);
                if(pathDirecory==book.getPath() && booksDirectories.size()>1){
                    selectAddressBook(scan);
                    System.out.println("lista de directorios eliminada");
                    return;
                }
                book = new AddressBook(booksDirectories.get(0));
                System.out.println("lista de directorios eliminada");
                isCorret = true;
            } else {
                System.out.println("indice invalido");
            }
        }else {
            System.out.println("indice invalido");
        }
        }
    }   
    


    /**
     * metodo para obtener el nombre de un addressBooks  a travez de su path
     * @param path path del addressBook
     * @return nombre deL addressBook
     */
    private String getNameFromPath(String path){
        ArrayList<int[]> hola = HighlightText.find(path,ADDRESSBOOKDIRECTORY);
        int indexExtension = path.indexOf(".json");
        return path.substring(hola.get(0)[1],indexExtension);
    }
    

    /**
     * metodo de busqueda de un elemento en un AddressBook
     * 
     * @param scan scanner para la entrada de datos por consola
     */
    private void toSearch(Scanner scan) {

        System.out.print(HighlightText.CYAN + "Ingresa el nombre de la direccion de busqueda: " + HighlightText.RED);
        String search = scan.nextLine();
        ArrayList<AddressEntry> addresslist = book.filterAddress(search);
        if (addresslist.isEmpty()) {
            System.out.println("sin resultados" + HighlightText.BLACK);
        } else if (addresslist.size() == 1) {
            AddressEntry entry = addresslist.get(0);
            System.out.println(HighlightText.PURPLE + "resultado:" + HighlightText.BLACK);
            System.out.println(HighlightText.highlightSearch(entry.getName().toLowerCase(),
                    search.toLowerCase().strip()));
            System.out.println(
                    HighlightText.GREEN_BOLD_BRIGHT + entry.generateInfoTable() + HighlightText.BLACK);
        } else {

            showCompleteInformation(addresslist, scan, search);
        }

    }

    /***
     * 
     * @param addressList lista de direcciones para mostrar su informacion
     * @param scan        scanner para la entrada de datos por consola
     * @param search      busqueda anteriormente usada en ToDelete()
     *                    para seleccionar el elemento a buscar
     */

    /***
     * añade una entrada en un book
     * 
     * @param scan scanner para la entrada de datos por consola
     */
    private void toAdd(Scanner scan) {

        boolean isCorrectEntry = false;
        String name = "no data", lastName = "no data", street = "no data", state = "no data", postalCode = "no data",
                email = "no data",
                phone = "no data";

        System.out.print(HighlightText.BLACK + "Nombre: " + HighlightText.RED);
        name = scan.nextLine();

        System.out.print(HighlightText.BLACK + "Apellido: " + HighlightText.RED);
        lastName = scan.nextLine();

        System.out.print(HighlightText.BLACK + "Calle: " + HighlightText.RED);
        street = scan.nextLine();

        System.out.print(HighlightText.BLACK + "Estado: " + HighlightText.RED);
        state = scan.nextLine();

        isCorrectEntry = false;
        while (!isCorrectEntry) {
            System.out.print(HighlightText.BLACK + "Código postal: " + HighlightText.RED);
            postalCode = scan.nextLine();
            if (postalCode.length() == 5 && regexComparation("\\d+", postalCode)) {
                isCorrectEntry = true;
            } else {
                System.out.println("codigo postal invalido, debe de ser un numero y contener 5 caracteres");
            }
        }

        isCorrectEntry = false;
        while (!isCorrectEntry) {
            System.out.print(HighlightText.BLACK + "Correo Electrónico: " + HighlightText.RED);
            email = scan.nextLine();
            if (regexComparation("^(.+)@(.+)$", email)) {
                isCorrectEntry = true;
            } else {
                System.out.println("inserta un correo valido Ejemplo : rafael@gmail.com");
            }
        }

        isCorrectEntry = false;
        while (!isCorrectEntry) {
            System.out.print(HighlightText.BLACK + "Número de teléfono: " + HighlightText.RED);
            phone = scan.nextLine();
            if (phone.length() == 10 && regexComparation("\\d+", phone)) {
                isCorrectEntry = true;
            } else {
                System.out.println("Numero invalido , el número debe de contener 10 caracteres");
            }
        }

        AddressEntry newEntry = new AddressEntry(name, lastName, street, state, postalCode, email, phone);
        boolean isRepeat = book.addAddress(newEntry);
        if (!isRepeat) {
            System.out.println(
                    "\n~" + HighlightText.PURPLE_BOLD + "+Dirección agregada con exito!" + HighlightText.BLACK);
        }

    }

    /**
     * metodo que usa implemente openFileViaExplorer() de la clase
     * FileManagement y
     * uploadAdressFromFile(path de la clase AddressBook para cargar
     * un archivo txt a un AddressBook
     * 
     * @throws FileNotFoundException exepcion en caso de no encontrar el archivo
     */
    private void toFileUpload() throws FileNotFoundException {

        System.out.println(HighlightText.CYAN + "Selecciona la ruta del archivo por la ventana");
        String path = FileManagement.openFileViaExplorer();

        if ("CANCELLED".equals(path)) {
            System.out.println(HighlightText.RED + "Operación cancelada. Volviendo al menú." + HighlightText.BLACK);
            return;
        }

        try {
            this.book.uploadAdressFromFile(path);
        } catch (FileNotFoundException e) {
            System.out.println(
                    HighlightText.RED + "Archivo no encontrado. Inténtalo de nuevo." + HighlightText.BLACK);
            return;
        }

    }

    /**
     * elimina uno o varios elementos dentro de un AddressBook
     * 
     * @param scan scanner para la entrada de datos por consola
     */
    private void toDelete(Scanner scan) {

        System.out.print(HighlightText.BLACK_BOLD + "¿Qué registro deseas eliminar?, " + HighlightText.BLACK_UNDERLINED
                + "ingresa el nombre del registro: "
                + HighlightText.RED);
        String search = scan.nextLine();

        ArrayList<AddressEntry> adressToDelete = book.filterAddress(search);
        if (adressToDelete.size() == 1) {
            AddressEntry entry = adressToDelete.get(0);
            System.out.print(
                    HighlightText.BLACK_BOLD + "Deseas eliminar este registro?\n" + HighlightText.GREEN_BOLD_BRIGHT
                            + "\n" + entry.generateInfoTable() + HighlightText.BLACK_BOLD + " \nescribe "
                            + HighlightText.BLACK_UNDERLINED + "si para aceptar y "
                            + "cualquier otra cadena para cancelar" + HighlightText.BLACK + ": " + HighlightText.RED);
            String cancel = scan.nextLine();
            if (cancel.equalsIgnoreCase("si")) {
                System.out.println();
                book.deleteAdress(entry);
                System.out.println(HighlightText.YELLOW_BOLD + entry.getName() + " " + entry.getLastName()
                        + HighlightText.RED_BOLD + " eliminado correctamente" + HighlightText.BLACK);
            } else {
                System.out.println(HighlightText.BLUE + "operacion cancelada!" + HighlightText.BLACK);
            }
        } else if (!adressToDelete.isEmpty()) {
            System.out.println(HighlightText.CYAN_BOLD + "Coincidencias:");
            for (AddressEntry entry : adressToDelete) {
                System.out.println(HighlightText.BLUE + adressToDelete.indexOf(entry) + HighlightText.BLACK + "."
                        + HighlightText.highlightSearch(entry.getName(), search));
            }
            optionsToDelete(scan, adressToDelete, search);
        } else {
            System.out.println(HighlightText.BLACK + "No se encontraron coincidencias.");
        }

    }

    /**
     * este metodo de diferentes opciones para eliminar uno o varios elementos de un
     * lista
     * 
     * @param scan            scanner para la entrada de datos por consola
     * @param addressToDelete lista de AddressEntry que contiene las
     *                        direcciones a eliminar
     * @param search          busqueda para resaltar el texto
     */
    private void optionsToDelete(Scanner scan, ArrayList<AddressEntry> addressToDelete, String search) {
        System.out.println("¿Qué opción deseas?:" +
                HighlightText.CYAN
                + "\nColoca en forma de lista las direcciones a eliminar:" + HighlightText.BLACK
                + HighlightText.CYAN_BOLD
                + " [1,2,3,4]" + HighlightText.BLUE
                + "\nEliminar todas las coincidencias " + HighlightText.BLUE_BOLD + "[e]" + HighlightText.GREEN
                + "\nVer informacion completa " + HighlightText.GREEN_BOLD + "[d]" + HighlightText.PURPLE
                + "\nCancelar" + HighlightText.PURPLE_BOLD + " [c]" + HighlightText.RED);
        System.out.print("$ " + HighlightText.RED);
        String deleteOption = scan.nextLine();
        switch (deleteOption) {
            case "e":
                // Eliminar todas las coincidencias
                System.out.println(HighlightText.PURPLE + "Registros eliminados:" + HighlightText.BLACK);
                for (AddressEntry entry : addressToDelete) {
                    book.deleteAdress(entry);
                    System.out.println(HighlightText.YELLOW_BOLD + entry.getName() + " " + entry.getLastName()
                            + HighlightText.BLACK);
                }
                break;
            case "c":
                // Cancelar
                System.out.println(HighlightText.CYAN + "Operación cancelada." + HighlightText.BLACK);
                break;
            case "d":
                showCompleteInformation(addressToDelete, scan, search);
                optionsToDelete(scan, addressToDelete, search);
                break;
            default:
                if (regexComparation("\\b\\d+(,\\d+)*\\b", deleteOption)) {
                    String[] addressToRemove = deleteOption.split(",");
                    for (String indexRemove : addressToRemove) {
                        int index = Integer.parseInt(indexRemove);
                        if (index >= 0 && index < addressToDelete.size()) {
                            AddressEntry entry = addressToDelete.get(index);
                            book.deleteAdress(entry);
                            System.out.println(HighlightText.YELLOW_BOLD + index + "." + entry.getName() + " "
                                    + entry.getLastName()
                                    + HighlightText.RED_BOLD + " eliminado correctamente" + HighlightText.BLACK);
                        } else {
                            System.out.println("Índice fuera de los límites: " + index);
                        }
                    }
                } else {
                    System.out.println("Formato de entrada inválido.");
                }
                break;
        }
    }

    /**
     * @param addressList
     * @param scan
     * @param search
     */
    private void showCompleteInformation(ArrayList<AddressEntry> addressList, Scanner scan, String search) {
        boolean isComplete = false;
        while (!isComplete) {
            if (addressList.isEmpty()) {
                return;
            }
            String coincidence = HighlightText.BLACK + "coincidencias: [";
            for (int i = 0; i < addressList.size(); i++) {
                AddressEntry entry = addressList.get(i);
                String higlightText = HighlightText.highlightSearch(entry.getName().toLowerCase(),
                        search.toLowerCase().strip());
                if (i == addressList.size() - 1) {
                    coincidence += i + "." + higlightText + HighlightText.BLACK + "']";
                } else {
                    coincidence += i + "." + higlightText + HighlightText.BLACK + ", ";
                }

            }
            System.out.println(coincidence);
            System.out
                    .print(HighlightText.BLACK_BOLD + "ingresa el " + HighlightText.BLACK_UNDERLINED
                            + "numero de la direcion" + HighlightText.BLACK
                            + " para ver la informacion completa, " + HighlightText.BLACK_UNDERLINED
                            + "coloca -1 para terminar" + HighlightText.BLACK + ": "
                            + HighlightText.RED);

            String preIndex = scan.nextLine();
            System.out.println(HighlightText.BLACK);
            if (regexComparation("^\\d+$", preIndex)) {
                int index = Integer.parseInt(preIndex);
                if (index < addressList.size()) {
                    AddressEntry entry = addressList.get(index);
                    System.out.println(
                            HighlightText.GREEN_BOLD_BRIGHT + entry.generateInfoTable() + HighlightText.BLACK);
                } else {
                    System.out.println("indice invalido");
                }
            } else if (preIndex.equals("-1")) {
                isComplete = true;
            } else {
                System.out.println("indice invalido");
            }
        }
    }

    /**
     * metodo para mostrar todas los elementos de un AddressBook
     */
    private void toShow() {

        book.showAdress();

    }

    /**
     * este metodo funciona para poder repetir las opciones dentro de un menu y
     * poder comfirmar la salida dentro de estas mismas
     * 
     * @param function interfaz funcional la cual se ejecutara dentro del metodo
     * @param message  mensaje de salida del metodo
     * @param scan     scanner para la entrada de datos por consola
     */
    private void exitToMenu(Function function, String message, Scanner scan) {
        boolean isCorrect = false;
        while (!isCorrect) {
            function.execute();
            System.out.print(
                    message + HighlightText.RED + " [si]" + HighlightText.BLACK
                            + HighlightText.BLUE + " [no] : " + HighlightText.BLACK + HighlightText.RED);
            String exit = scan.nextLine();
            System.out.println(HighlightText.BLACK);
            switch (exit) {
                case "no":
                    isCorrect = true;
                case "si":
                    break;
                case "$$":
                    System.out.println("adios!");
                    System.exit(0);
                default:
                    System.out.println(
                            HighlightText.PURPLE + "Selecciona una opción valida!" + HighlightText.BLACK);
                    break;

            }
        }
    }

    /**
     * metodo para exportar un AddressBook a un archivo .txt
     *
     * @param scan scanner para la entrada de datos por consola
     */
    private void toExport(Scanner scan) {

        System.out.print("Coloca el nombre del archivo sin la extension: " + HighlightText.RED);
        String name = scan.nextLine();
        System.out.println(HighlightText.CYAN + "Selecciona la ruta del archivo por la ventana");
        String directoryPath = FileManagement.openDirectoryViaExplorer();

        if ("CANCELLED".equals(directoryPath)) {
            System.out.println(HighlightText.RED + "Operación cancelada. Volviendo al menú." + HighlightText.BLACK);
            return;
        }

        System.out.println("selecciona el directorio del archivo desde la ventana...");
        book.exportAdressBook(directoryPath + "\\" + name + ".txt");

    }

    /**
     * 
     * @param regexExpresion expresion regular para comparar la entrada de texto
     * @param string         cadena para comparar con al expresion regular
     * @return true en caso de coincidir false en el caso contrario
     */
    private boolean regexComparation(String regexExpression, String string) {
        return Pattern.matches(regexExpression, string);

    }

    /***
     * interrumpe un hilo de ejecucion
     */
    private void interrupt() {
        if (Thread.currentThread().isInterrupted()) {
            Thread.interrupted();
        }
    }

    /**
     * despliega el menu en caso de no haber datos dentro del AddressBook lo
     * mostrará en la pantalla
     */
    private void menu() {

        if (book.getSize() == 0) {
            System.out.println(HighlightText.RED_BOLD + "--no hay datos en esta lista" + HighlightText.BLACK);
        }

        System.out.println("=============================================");
        System.out.println(
                "|           " + HighlightText.GREEN_BOLD + "Selecciona una opcion" + HighlightText.BLACK
                        + "           |");
        System.out.println("=============================================");
        System.out.println("|           " + HighlightText.GREEN_BRIGHT + "Opciones:" + HighlightText.BLACK
                + "                       |");
        System.out.println(
                "|                    " + HighlightText.YELLOW + "(a)" + HighlightText.BLACK + " Cargar archivo     |");
        System.out.println(
                "|                    " + HighlightText.YELLOW_BRIGHT + "(b)" + HighlightText.BLACK
                        + " Agregar            |");
        System.out.println(
                "|                    " + HighlightText.PURPLE + "(c)" + HighlightText.BLACK + " Eliminar           |");
        System.out.println(
                "|                    " + HighlightText.CYAN + "(d)" + HighlightText.BLACK + " Buscar             |");
        System.out.println(
                "|                    " + HighlightText.GREEN + "(e)" + HighlightText.BLACK + " Mostrar            |");
        System.out.println("|                    " + HighlightText.CYAN_BRIGHT + "(f)" + HighlightText.BLACK
                + " Exportar archivo   |");
        System.out.println(
                    "|                    " + HighlightText.RED + "(h)" + HighlightText.BLACK + " cambiar lista      |");
        System.out.println(
                        "|                    " + HighlightText.RED + "(i)" + HighlightText.BLACK + " agregar lista      |");
        System.out.println(
                            "|                    " + HighlightText.RED + "(j)" + HighlightText.BLACK + " eliminar lista     |");
        System.out.println(
                            "|                    " + HighlightText.RED + "(g)" + HighlightText.BLACK + " Salir              |");
        System.out.println("=============================================");
                        }
                    
    /*
     * despliega el menu por consola
     */
    public void displayMenu() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        if(booksDirectories.isEmpty()){
            System.out.println("crea una nueva lista no tienes lista");
            addAddressBook(scan);
        }else if(booksDirectories.size() == 1){
            book = new AddressBook(booksDirectories.get(0));
        }else{
            selectAddressBook(scan);
        }
        System.out.println(
            HighlightText.BLUE_BOLD + "==================Bienvenido=================" + HighlightText.BLACK + "\n");
            String option = "a";
        while (true) {
            System.out.println(HighlightText.GREEN_BOLD+getNameFromPath(book.getPath()));
            menu();
            System.out.print(HighlightText.RED_BOLD + "$ " + HighlightText.RED);
            option = scan.nextLine();
            System.out.println(HighlightText.BLACK);
            switch (option.toLowerCase()) {
                case "a":
                    exitToMenu(() -> {
                        try {
                            toFileUpload();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }, "¿Importar otro archivo?", scan);
                    interrupt();
                    break;
                case "b":
                    exitToMenu(() -> {
                        toAdd(scan);
                    }, "¿agregar otro registro?", scan);
                    break;
                case "c":
                    exitToMenu(() -> {
                        toDelete(scan);
                    }, "¿Eliminar otro registro?", scan);
                    break;
                case "d":
                    exitToMenu(() -> {
                        toSearch(scan);
                    }, "¿Volver a buscar?", scan);
                    break;
                case "e":
                    exitToMenu(() -> {
                        toShow();
                    }, "¿volver a mostrar?", scan);
                    break;
                case "f":
                    exitToMenu(() -> {
                        toExport(scan);
                    }, "¿exportar de nuevo?", scan);
                    interrupt();
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
                case"h":
                    selectAddressBook(scan);
                    continue;
                case"i":
                    addAddressBook(scan);
                    continue;
                case "j":
                    deleteAddressBook(scan);
                    continue;
                default:
                    System.out.println(HighlightText.PURPLE + "Selecciona una opción valida!" +
                            HighlightText.BLACK);
                    break;
            }
        }
    }

}
