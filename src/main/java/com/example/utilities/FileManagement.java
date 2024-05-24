package com.example.utilities;

import com.example.AdressData.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

public class FileManagement {
    public static String openFileViaExplorer() {
        final String[] filePath = { "" };

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFileChooser fileChooser = new JFileChooser("src/main/java/com/example/info/");
                fileChooser.setDialogTitle("Select File");

                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    filePath[0] = selectedFile.getAbsolutePath();
                } else {
                    filePath[0] = "CANCELLED";
                }
            }
        });

        while (filePath[0].isEmpty()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        if ("CANCELLED".equals(filePath[0])) {
            Thread.currentThread().interrupt();
        }

        return filePath[0];
    }

    public static String openDirectoryViaExplorer() {
        final String[] filePath = { "" };

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setDialogTitle("Select Directory");

                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    filePath[0] = selectedFile.getAbsolutePath();
                } else {
                    filePath[0] = "CANCELLED";
                }
            }
        });

        while (filePath[0].isEmpty()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        if ("CANCELLED".equals(filePath[0])) {
            Thread.currentThread().interrupt();
        }

        return filePath[0];
    }

    public static ArrayList<AddressEntry> txtUploadToArraylist(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scan = new Scanner(file);
        int lineText = 0;
        String completeText = "";

        ArrayList<AddressEntry> listToUpload = new ArrayList<>();

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            completeText += line + ",";
            lineText++;
            if (lineText % 7 == 0) {
                String[] atributes = completeText.split(",");
                AddressEntry newEntry = new AddressEntry(atributes[0], atributes[1], atributes[2],
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

    public static void writeAdressToFile(AddressEntry entry, String path) {
        try (BufferedWriter bufferEscritor = new BufferedWriter(new FileWriter(path, true))) {
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

    public static String readFile(String path) {
        try (Reader reader = new FileReader(path)) {
            int valor = reader.read();
            String json = "";
            while (valor != -1) {
                json += (char) valor;
                valor = reader.read();
            }
            // Cerramos el stream
            reader.close();
            return json;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<AddressEntry> JsonFileToArrayList(String path) {
        String json = readFile(path);
        Gson gson = new Gson();
        Type userListType = new TypeToken<ArrayList<AddressEntry>>() {
        }.getType();
        if (json.isEmpty()) {
            return new ArrayList<AddressEntry>();
        } else {
            return gson.fromJson(json, userListType);

        }
    }

    public static void writeAddressOnJsonFile(String path, ArrayList<AddressEntry> list) {
        try (Writer writer = new FileWriter(path)) {
            Gson gson = new Gson();
            gson.toJson(list, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
