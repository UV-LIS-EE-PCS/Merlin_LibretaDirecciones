package com.example.addressdata;

import com.example.utilities.*;
import java.util.ArrayList;


import java.io.*;
import de.vandermeer.asciitable.AsciiTable;

/**
 * AddressBook es una clase que maneja una colección de entradas de dirección.
 */
public class AddressBook {
    private ArrayList<AddressEntry> listAddress;
    private int size;
    private String path; 
    private static AddressBook instance;


    /***
     * consigue el tamaño de el libro
     * 
     * @return regresa el tamaño del AddressBook
     */
    public int getSize() {
        return this.size;
    }

    
  


    /**
     * metodo para obtener el path en el que se guarddan los contactos de este addressBook
     * @return ruta del archivo
     */
    public String getPath() {
        return path;
    }

    /**
     * Cambia la informacion de un AddressBook
     * @param path directorio del archivo que contiene la informacion
     * @throws FileNotFoundException error en caso de no encontrar el ar
     */
    public void changeInfo(String path) throws FileNotFoundException{
        try {
            this.path = path;
            ArrayList<AddressEntry> Address = FileManagement.jsonFileToArrayList(path);
            listAddress = new ArrayList<>(Address);
            this.size = listAddress.size();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(HighlightText.RED + "Archivo de Contactos no encontrado" + HighlightText.BLACK);
        };
    }
  /**
     * Constructor privado para evitar la creación de instancias desde fuera de la clase.
     * 
     * @throws FileNotFoundException no se encuentra el archivo
     */
    private AddressBook(String path) throws FileNotFoundException {
        changeInfo(path);
    }

    /**
     * Proveer el método para obtener la instancia única de AddressBook.
     * 
     * @return la instancia única de AddressBook
     */
    public static AddressBook getInstance(String path) throws FileNotFoundException {
        if (instance == null) {
            instance = new AddressBook(path);
        }
        return instance;
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

        for (AddressEntry address : listAddress) {
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
            listAddress.add(entry);
            FileManagement.writeAddressOnJsonFile(path, listAddress);
            size = listAddress.size();
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
        if (listAddress.isEmpty()) {
            System.out.println("lista vacia");
        } else if (listAddress.indexOf(entry) == -1) {
            System.out.println(HighlightText.CYAN + "no existe ese elemento en esta lista" + HighlightText.BLACK);
        } else {
            listAddress.remove(entry);
            FileManagement.writeAddressOnJsonFile(path, listAddress);
            size = listAddress.size();
        }
    }

    /***
     * 
     * muestra por pantalla todas las direcciones del libro
     */
    public void showAdress() {
        if (listAddress.isEmpty()) {
            System.out.println(HighlightText.CYAN + "Sin datos" + HighlightText.BLACK);
        } else {
            System.out.println(generateAllDataTable(listAddress) + "\n");

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
            for (AddressEntry entry : listAddress) {
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
        for (AddressEntry entry : listAddress) {
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
