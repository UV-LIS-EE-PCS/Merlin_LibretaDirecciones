package com.example;

import java.util.ArrayList;
import java.io.*;

import de.vandermeer.asciitable.AsciiTable;

public class AdressBook {
    private ArrayList<AdressEntry> listAdress;

    private String red = "\033[31m";
    private String reset = "\u001B[0m";
    private String purple = "\u001B[35m";
    private String yellow = "\033[33m";
    private String cyan = "\033[36m";

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
                System.out.println(purple + "La entrada: " + reset);
                System.out.println(red + at.render(150) + reset);
                System.out.println(
                        red + GenerateInfoTable(entry) + "\n" + purple + "ya existe!!" + reset
                                + "\n");
                return true;
            }
        }

        if (!isDuplicate) {
            FileManagement.writeAdressToFile(entry);
            return isDuplicate;
        }
        return isDuplicate;
    }

    public void deleteAdress(AdressEntry entry) {
        if (listAdress.isEmpty()) {
            System.out.println(yellow + "Sin datos" + reset);
        } else {
            listAdress.remove(entry);
            System.out.println("Contacto " + purple + entry.getName() + reset + " Removido con exito!");
        }
    }

    public void searchAdress(String search) {
        ArrayList<AdressEntry> entryToDisplay = filterAdress(search);
        if (entryToDisplay.isEmpty()) {
            System.out.println(purple + "No se encontró ninguna coincidecia");

        } else {
            System.out.println(purple + "Resultados de la busqueda:" + reset + "\n");
            AsciiTable dataText = new AsciiTable();
            dataText.setPaddingLeftRight(1, 1);
            dataText.addRule();
            dataText.addRow("Nombre", "Apellido", "Calle", "Estado", "Codigo Postal", "Correo Electrónico", "Telefono");
            int counterIndex = 0;
            for (AdressEntry entry : entryToDisplay) {
                String higlightText = highlightSearch(entry.getName().toLowerCase(), search.toLowerCase().strip());
                System.out.println(higlightText);
                if (counterIndex == 0) {
                    String rend = dataText.render(150);
                    System.out.println(rend);
                }

                System.out.println(purple + GenerateInfoTable(entry) + reset + "\n");
                counterIndex++;

            }
        }
    }

    public void showAdress() {
        if (listAdress.isEmpty()) {
            System.out.println(yellow + "Sin datos" + reset);
        } else {
            System.out.println(GenerateAllDataTable(listAdress) + "\n");

        }
    }

    public void uploadAdressFromFile() throws FileNotFoundException {
        String path = FileManagement.openFileViaExplorer();
        ArrayList<AdressEntry> newAdressList = FileManagement.fileUploadToArraylist(path);
        if (newAdressList.isEmpty()) {
            System.out.println(cyan + "archivo invalido" + reset);

        } else {
            for (AdressEntry entry : newAdressList) {
                addAddress(entry);
                System.out.println(purple + "Archivo importado con exito!");

            }
        }
    }

    private ArrayList<AdressEntry> filterAdress(String search) {
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

    private String highlightSearch(String string, String search) {
        ArrayList<int[]> listOfPositions = find(string, search);
        String finalString = "";
        int indexCounter = 0;
        for (int i = 0; i < string.length(); i++) {

            if (i == listOfPositions.get(indexCounter)[0]) {
                finalString += purple;
            }
            if (i == listOfPositions.get(indexCounter)[1]) {
                finalString += reset;
                if (listOfPositions.size() == indexCounter + 1) {
                    indexCounter = listOfPositions.size() - 1;
                } else {
                    indexCounter++;
                }

            }
            finalString += string.charAt(i);
        }

        return finalString;

    }

    private ArrayList<int[]> find(String string, String search) {
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
