package com.example;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class FileManagement {

    public static String openFileViaExplorer() {
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
            bufferEscritor.write("\n");
            bufferEscritor.write(entry.getName() + "\n");
            bufferEscritor.write(entry.getLastName() + "\n");
            bufferEscritor.write(entry.getStreet() + "\n");
            bufferEscritor.write(entry.getState() + "\n");
            bufferEscritor.write(entry.getPostalCode() + "\n");
            bufferEscritor.write(entry.getEmail() + "\n");
            bufferEscritor.write(entry.getPhone());
            bufferEscritor.close();

        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo.");
            e.printStackTrace();
        }
    }
}
