package com.example;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.io.*;
import de.vandermeer.asciitable.AsciiTable;

public class AdressBook {
    private ArrayList<AdressEntry> listAdress;

    public AdressBook() throws FileNotFoundException {
        String path = "src/main/java/com/example/Contactos.txt";
        ArrayList<AdressEntry> Adress = FileManagement.fileUploadToArraylist(path);
        listAdress = new ArrayList<>(Adress);
    }

    public boolean addAddress(AdressEntry entry) {
        boolean isDuplicate = false;

        for (AdressEntry address : listAdress) {
            if (address.toString().equals(entry.toString())) {
                AsciiTable at = new AsciiTable();
                at.setPaddingLeftRight(1, 1);
                at.addRule();
                at.addRow("Nombre", "Apellido", "Calle", "Estado", "Codigo Postal", "Correo Electrónico", "Telefono");
                System.out.println(ConsoleColors.PURPLE + "La entrada: " + ConsoleColors.BLACK);
                System.out.println(ConsoleColors.RED + at.render(150) + ConsoleColors.BLACK);
                System.out.println(
                        ConsoleColors.RED + GenerateInfoTable(entry) + "\n" + ConsoleColors.PURPLE + "ya existe!!"
                                + ConsoleColors.BLACK
                                + "\n");
                return true;
            }
        }

        if (!isDuplicate) {
            listAdress.add(entry);
            FileManagement.writeAdressToFile(entry);

            return isDuplicate;
        }
        return isDuplicate;
    }

    public void deleteAdress(int index) {
        if (listAdress.isEmpty()) {
            System.out.println("lista vacia");
        } else {
            AdressEntry entry = listAdress.get(index);
            listAdress.remove(entry);
            FileManagement.replaceArraylistToContacts(listAdress);
        }
    }

    public void deleteAdress(AdressEntry entry) {
        if (listAdress.isEmpty()) {
            System.out.println("lista vacia");
        } else {
            listAdress.remove(entry);
            FileManagement.replaceArraylistToContacts(listAdress);
        }
    }

    public void searchAdress(String search) {
        ArrayList<AdressEntry> entryToDisplay = filterAdress(search);
        if (entryToDisplay.isEmpty()) {
            System.out.println(ConsoleColors.PURPLE + "No se encontró ninguna coincidecia");
        } else {
            System.out.println(ConsoleColors.PURPLE + "Resultados de la busqueda:" + ConsoleColors.BLACK + "\n");
            AsciiTable dataText = new AsciiTable();
            dataText.setPaddingLeftRight(1, 1);
            dataText.addRule();
            dataText.addRow("Nombre", "Apellido", "Calle", "Estado", "Codigo Postal", "Correo Electrónico", "Telefono");
            int counterIndex = 0;
            for (AdressEntry entry : entryToDisplay) {
                String higlightText = highlightSearch(entry.getName().toLowerCase(), search.toLowerCase().strip());
                System.out.println(higlightText + ConsoleColors.BLACK + "\n");
                if (counterIndex == 0) {
                    String rend = dataText.render(150);
                    System.out.println(rend);
                }

                System.out.println(ConsoleColors.PURPLE + GenerateInfoTable(entry) + ConsoleColors.BLACK + "\n");
                counterIndex++;

            }
        }
    }

    public void showAdress() {
        if (listAdress.isEmpty()) {
            System.out.println(ConsoleColors.CYAN + "Sin datos" + ConsoleColors.BLACK);
        } else {
            System.out.println(GenerateAllDataTable(listAdress) + "\n");

        }
    }

    public void uploadAdressFromFile(String path) throws FileNotFoundException {
        ArrayList<AdressEntry> newAdressList = FileManagement.fileUploadToArraylist(path);
        if (newAdressList.isEmpty()) {
            System.out.println(ConsoleColors.CYAN + "archivo invalido" + ConsoleColors.BLACK);

        } else {
            for (AdressEntry entry : newAdressList) {
                addAddress(entry);

            }
        }
    }

    public void exportAdressBook(String path) {
        Path sourcePath = Paths.get("src/main/java/com/example/Contactos.txt");
        Path destinationPath = Paths.get(path);
        try {
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(ConsoleColors.PURPLE + "El archivo ha sido copiado con exito." + ConsoleColors.BLACK);
        } catch (IOException e) {
            System.err.println("Error al copiar el archivo: " + e.getMessage());
        }
    }

    public ArrayList<AdressEntry> filterAdress(String search) {
        ArrayList<AdressEntry> filterAdress = new ArrayList<>();
        for (AdressEntry entry : listAdress) {
            if (entry.getName().toLowerCase().contains(search.toLowerCase())) {
                filterAdress.add(entry);
            }
        }

        return filterAdress;
    }

    private String GenerateAllDataTable(ArrayList<AdressEntry> listEntry) {
        int numberOfAdress = 1;

        AsciiTable dataText = new AsciiTable();
        dataText.setPaddingLeftRight(1, 1);
        dataText.addRule();
        dataText.addRow("Direccion no: ", "Nombre", "Apellido", "Calle", "Estado", "Codigo Postal",
                "Correo Electrónico",
                "Telefono");
        dataText.addRule();

        for (AdressEntry entry : listEntry) {
            dataText.addRow(numberOfAdress, entry.getName(), entry.getLastName(), entry.getStreet(), entry.getState(),
                    entry.getPostalCode(), entry.getEmail(), entry.getPhone());
            dataText.addRule();
            numberOfAdress++;
        }
        String rend = dataText.render(150);
        return rend;
    }

    private String GenerateInfoTable(AdressEntry entry) {

        AsciiTable dataText = new AsciiTable();
        dataText.setPaddingLeftRight(1, 1);
        dataText.addRule();
        dataText.addRow(entry.getName(), entry.getLastName(), entry.getStreet(), entry.getState(),
                entry.getPostalCode(),
                entry.getEmail(), entry.getPhone());
        dataText.addRule();
        String rend = dataText.render(150);
        return rend;
    }

    public static String highlightSearch(String string, String search) {
        ArrayList<int[]> listOfPositions = find(string, search);
        String finalString = "";
        int indexCounter = 0;
        for (int i = 0; i < string.length(); i++) {

            if (i == listOfPositions.get(indexCounter)[0]) {
                finalString += ConsoleColors.PURPLE_UNDERLINED;
            }
            if (i == listOfPositions.get(indexCounter)[1]) {
                finalString += ConsoleColors.BLACK;
                if (listOfPositions.size() == indexCounter + 1) {
                    indexCounter = listOfPositions.size() - 1;
                } else {
                    indexCounter++;
                }

            }
            finalString += string.charAt(i);
        }

        return finalString + ConsoleColors.BLACK;

    }

    public static ArrayList<int[]> find(String string, String search) {
        int[] positionSearch = new int[2];
        ArrayList<int[]> listOfPostion = new ArrayList<>();

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == search.charAt(0)) {
                positionSearch[0] = i;
                int counter = i;
                for (int j = 0; j < search.length(); j++) {
                    if (string.charAt(counter) == search.charAt(j)) {

                        counter += 1;
                        if (j == search.length() - 1) {
                            positionSearch[1] = counter;
                            int[] positionSearchCopy = positionSearch.clone();
                            listOfPostion.add(positionSearchCopy);

                        }
                    } else {
                        break;
                    }
                }
            }
        }

        return listOfPostion;
    }

}
