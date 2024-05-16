package com.example;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.FileDialog;
import java.awt.Frame;
import javax.swing.SwingUtilities;

public class FileManagement {
    public static String openFileViaExplorer() {
        final String[] filePath = { "" };

        // Asegúrate de que esto se ejecute en el hilo de eventos
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FileDialog fileDialog = new FileDialog((Frame) null, "Select File", FileDialog.LOAD);
                fileDialog.setVisible(true);

                String fileName = fileDialog.getFile();
                if (fileName != null) {
                    String directory = fileDialog.getDirectory();
                    filePath[0] = directory + fileName;
                } else {
                    filePath[0] = "";
                }
            }
        });
        // Esperar a que el FileDialog se cierre y obtener el resultado
        while (filePath[0].isEmpty()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        return filePath[0];
    }

    public static ArrayList<AdressEntry> fileUploadToArraylist(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scan = new Scanner(file);
        int lineText = 0;
        String completeText = "";

        ArrayList<AdressEntry> listToUpload = new ArrayList<>();

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            completeText += line + ",";
            lineText++;
            if (lineText % 7 == 0) {
                String[] atributes = completeText.split(",");
                AdressEntry newEntry = new AdressEntry(atributes[0], atributes[1], atributes[2],
                        atributes[3], atributes[4], atributes[5], atributes[6]);
                listToUpload.add(newEntry);
                completeText = "";
            }
        }
        scan.close();
        if (lineText % 7 != 0) {
            return listToUpload;
        }
        return listToUpload;
    }

    public static void writeAdressToFile(AdressEntry entry) {
        String nombreArchivo = "src/main/java/com/example/Contactos.txt";
        try (BufferedWriter bufferEscritor = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            bufferEscritor.write(entry.getName() + "\n");
            bufferEscritor.write(entry.getLastName() + "\n");
            bufferEscritor.write(entry.getStreet() + "\n");
            bufferEscritor.write(entry.getState() + "\n");
            bufferEscritor.write(entry.getPostalCode() + "\n");
            bufferEscritor.write(entry.getEmail() + "\n");
            bufferEscritor.write(entry.getPhone() + "\n");
            bufferEscritor.close();

        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo.");
            e.printStackTrace();
        }
    }

    public static void replaceArraylistToContacts(ArrayList<AdressEntry> list) {
        emptyFile("src/main/java/com/example/Contactos.txt");
        for (AdressEntry adressToWrite : list) {
            writeAdressToFile(adressToWrite);
        }
    }

    public static void emptyFile(String filePath) {
        try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
            // truncate file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
