package com.example.AddressData;

import com.example.utilities.*;
import java.util.ArrayList;
import java.io.*;
import de.vandermeer.asciitable.AsciiTable;

/**
 * AddressBook es una clase que maneja una colección de entradas de dirección.
 */
public class AddressBook {
    private ArrayList<AddressEntry> listAdress;
    private int size;

    /***
     * consigue el tamaño de el libro
     * 
     * @return regresa el tamaño del AddressBook
     */
    public int getSize() {
        return this.size;
    }

    /**
     * constructor por defecto carga siempre un archivo
     * 
     * @throws FileNotFoundException no se encuentra el archivo
     */
    public AddressBook() throws FileNotFoundException {
        try {
            String path = "src/main/java/com/example/info/contactos.json";
            ArrayList<AddressEntry> Adress = FileManagement.JsonFileToArrayList(path);
            listAdress = new ArrayList<>(Adress);
            this.size = listAdress.size();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(HighlightText.RED + "Archivo de Contactos no encontrado" + HighlightText.BLACK);
        }
    }

    /**
     * constructor para usar una coleccion de adressEntry como AddressBook
     * 
     * @param listAdress una lista de AdressEntry la cual tomara el
     *                   objeto como el parametro listAdress
     * 
     */
    public AddressBook(ArrayList<AddressEntry> listAdress) {
        this.listAdress = listAdress;
        FileManagement.writeAddressOnJsonFile("src/main/java/com/example/info/contactos.json", listAdress);
        this.size = listAdress.size();
    }

    /**
     * añade una nueva direccion al libro
     * 
     * @param entry Address entry a agregar al libro de direcciones
     * @return devuelve true si el parametro entry es
     *         repetido o false si es nuevo
     */
    public boolean addAddress(AddressEntry entry) {
        boolean isDuplicate = false;

        for (AddressEntry address : listAdress) {
            if (address.equals(entry)) {
                System.out.println(HighlightText.PURPLE + "esta entrada ya existe: " + HighlightText.BLACK);
                System.out.println(HighlightText.RED + HighlightText.BLACK);
                System.out.println(
                        HighlightText.RED + entry.generateInfoTable() + "\n"
                                + HighlightText.BLACK
                                + "\n");
                return true;
            }
        }

        if (!isDuplicate) {
            listAdress.add(entry);
            FileManagement.writeAddressOnJsonFile("src/main/java/com/example/info/contactos.json", listAdress);
            size = listAdress.size();
            return isDuplicate;
        }
        return isDuplicate;
    }

    /***
     * elimina una entrada en el libro
     * 
     * @param entry entrada a eliminar del libro de direcciones
     */
    public void deleteAdress(AddressEntry entry) {
        if (listAdress.isEmpty()) {
            System.out.println("lista vacia");
        } else if (listAdress.indexOf(entry) == -1) {
            System.out.println(HighlightText.CYAN + "no existe ese elemento en esta lista" + HighlightText.BLACK);
        } else {
            listAdress.remove(entry);
            FileManagement.writeAddressOnJsonFile("src/main/java/com/example/info/contactos.json", listAdress);
            size = listAdress.size();
        }
    }

    /***
     * 
     * muestra por pantalla todas las direcciones del libro
     */
    public void showAdress() {
        if (listAdress.isEmpty()) {
            System.out.println(HighlightText.CYAN + "Sin datos" + HighlightText.BLACK);
        } else {
            System.out.println(generateAllDataTable(listAdress) + "\n");

        }
    }

    /***
     * carga direcciones de un archivo de texto
     * 
     * @param path ruta del archivo de texto a cargar
     * @throws FileNotFoundException exepcion en caso de no encontrar el archivo
     */
    public void uploadAdressFromFile(String path) throws FileNotFoundException {
        ArrayList<AddressEntry> newAdressList = FileManagement.txtUploadToArraylist(path);
        if (newAdressList.isEmpty()) {
            System.out.println(HighlightText.CYAN + "archivo invalido" + HighlightText.BLACK);

        } else {
            boolean isRepeat;
            for (AddressEntry entry : newAdressList) {
                isRepeat = addAddress(entry);
                if (!isRepeat) {
                    System.out.println(HighlightText.BLUE + "Contacto de nombre: " + HighlightText.BLUE_BOLD
                            + entry.getName() + " " + entry.getLastName() + HighlightText.RED + " importado"
                            + HighlightText.BLACK);
                }

            }
        }
    }

    /***
     * devuelve un archivo .txt en una direccion especificado con todos los
     * contactos de
     * la libreta
     * 
     * @param destinationPath ruta del archivo a crear
     */
    public void exportAdressBook(String destinationPath) {
        try {
            for (AddressEntry entry : listAdress) {
                FileManagement.writeAddressToFile(entry, destinationPath);
            }
            System.out.println(HighlightText.PURPLE + "Archivo importado con exito" + HighlightText.BLACK);
        } catch (Exception e) {
            System.out.println("no se logró exportar el archivo");
        }
    }

    /**
     * filtra por nombre las entradas del libro
     * 
     * @param search cadena de busqueda para un AddressEntry dentro de
     *               un AddressBook
     * @return una ArrayList con las direcciones
     *         encontradas
     */
    public ArrayList<AddressEntry> filterAddress(String search) {
        ArrayList<AddressEntry> filterAddress = new ArrayList<>();
        for (AddressEntry entry : listAdress) {
            if (entry.getName().toLowerCase().strip().contains(search.toLowerCase().strip())) {
                filterAddress.add(entry);
            }
        }

        return filterAddress;
    }

    /***
     * genera una tabla con los datos del libro
     * 
     * @param listEntry lista con diferentes AddressEntry
     * @return una tabla que contiene toda la informacion de un
     *         AdressBook
     */
    private String generateAllDataTable(ArrayList<AddressEntry> listAddress) {
        int numberOfAdress = 1;

        AsciiTable dataText = new AsciiTable();
        dataText.setPaddingLeftRight(1, 1);
        dataText.addRule();
        dataText.addRow("Direccion no: ", "Nombre", "Apellido", "Calle", "Estado", "Codigo Postal",
                "Correo Electrónico",
                "Telefono");
        dataText.addRule();

        for (AddressEntry entry : listAddress) {
            dataText.addRow(numberOfAdress, entry.getName(), entry.getLastName(), entry.getStreet(), entry.getState(),
                    entry.getPostalCode(), entry.getEmail(), entry.getPhone());
            dataText.addRule();
            numberOfAdress++;
        }
        String rend = dataText.render(150);
        return rend;
    }

}
