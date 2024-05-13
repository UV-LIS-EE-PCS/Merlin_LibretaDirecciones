package com.example;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import de.vandermeer.asciitable.AsciiTable;


public class AdressBook {
    private ArrayList<AdressEntry> listAdress = new ArrayList<>();
    private String red = "\033[31m";
    private String reset = "\u001B[0m";
    private String purple = "\u001B[35m";
    private String yellow = "\033[33m";
    private String cyan = "\033[36m";

    public void addAddress(AdressEntry entry) {
        boolean isDuplicate = false;
        
        for (AdressEntry address : listAdress) {
            if (address.toString().equals(entry.toString())) {
                AsciiTable at = new AsciiTable();
                at.setPaddingLeftRight(1, 1);    
                at.addRule();
                at.addRow("Nombre","Apellido","Calle","Estado","Codigo Postal","Correo Electrónico","Telefono" );
                System.out.println(purple+"La entrada: "+reset);
                System.out.println(red+at.render(190)+reset);
                System.out.println(
                        red + GenerateTableInfo(entry) + "\n" + purple + "ya existe!!" + reset
                                + "\n");
                isDuplicate = true;
                break;
            }
        }

        if (!isDuplicate) {
            listAdress.add(entry);
        }
    }

    public void deleteAdress(AdressEntry entry) {
        listAdress.remove(entry);
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

    public void searchAdress(String search) {
        ArrayList<AdressEntry> entryToDisplay = filterAdress(search);
        if (entryToDisplay.isEmpty()) {
            System.out.println(purple + "No se encontró ninguna coincidecia");

        } else {
            System.out.println(purple + "Resultados de la busqueda:" + reset + "\n");
            AsciiTable at = new AsciiTable();
            at.setPaddingLeftRight(1, 1);    
            at.addRule();
            at.addRow("Nombre","Apellido","Calle","Estado","Codigo Postal","Correo Electrónico","Telefono" );
            for(AdressEntry entry: entryToDisplay){
                String higlightText = highlightSearch(entry.getName().toLowerCase(), search.toLowerCase().strip());
                System.out.println(higlightText);
               
                String rend = at.render(190);
                System.out.println(rend);
                System.out.println(purple+GenerateTableInfo(entry) +reset+"\n");

            }
        }
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

    private String openFileViaExplorer() {
        FileDialog fileDialog = new FileDialog(new Frame(), "Select File", FileDialog.LOAD);
        
        fileDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("File selection canceled.");
            }
        });
        
        fileDialog.setVisible(true);
        
        String fileName = fileDialog.getFile();
        if (fileName != null) {
            String directory = fileDialog.getDirectory();
            String filePath = directory + fileName;
            System.out.println(filePath);
            
            return filePath;
        } else {
            System.out.println("No file selected.");
            
            return "";
        }
    }

    public void fileUpload() throws FileNotFoundException {
        String path = openFileViaExplorer();

        File file = new File(path);

        Scanner scan = new Scanner(file);

        int lineText = 1;

        String completeText = "";

        while (scan.hasNextLine()) {

            String line = scan.nextLine();
            completeText += line + ",";
            if (lineText % 7 == 0) {
                String[] atributes = completeText.split(",");
                AdressEntry newEntry = new AdressEntry(atributes[0], atributes[1], atributes[2],
                        atributes[3], atributes[4], atributes[5], atributes[6]);
                addAddress(newEntry);
                completeText = "";
            }
            lineText++;
        }
        System.out.println("\n" + cyan + "archivo importado con exito!" + reset + "\n");
        scan.close();
        if (lineText < 7) {
            System.out.println("Archivo invalido");

        }

    }

    public void showAdress() {
        if (listAdress.isEmpty()) {
            System.out.println(yellow+"Sin datos"+reset);
        } else {
           System.out.println(showGenerateTableInfo(listAdress)+"\n");
            
        }
    }

    private String showGenerateTableInfo(ArrayList<AdressEntry> listEntry){
        int numberOfAdress = 1;
        
        AsciiTable at = new AsciiTable();
        at.setPaddingLeftRight(1, 1);
        at.addRule();
        at.addRow("Direccion no: ","Nombre","Apellido","Calle","Estado","Codigo Postal","Correo Electrónico","Telefono" );
        at.addRule();
            
        for (AdressEntry entry : listEntry) {
            at.addRow(numberOfAdress,entry.getName(),entry.getLastName(),entry.getStreet(),entry.getState(),entry.getPostalCode(),entry.getEmail(),entry.getPhone());
            at.addRule();
            numberOfAdress++;
        }
        String rend = at.render(190);
        return rend;
    }

    private String GenerateTableInfo(AdressEntry entry){
        
        AsciiTable at = new AsciiTable();
        at.setPaddingLeftRight(1, 1);
        at.addRule();
        at.addRow(entry.getName(),entry.getLastName(),entry.getStreet(),entry.getState(),entry.getPostalCode(),entry.getEmail(),entry.getPhone());
        at.addRule();
        String rend = at.render(190);
        return rend;
    }

}
