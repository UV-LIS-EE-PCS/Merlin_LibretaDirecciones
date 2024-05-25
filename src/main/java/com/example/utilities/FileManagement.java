package com.example.utilities;

import com.example.AddressData.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

public class FileManagement {

    /**
     * abre el <code>JFileChooser</code> para seleccionar un archivo
     * 
     * @return la ruta del archivo
     */
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

    /**
     * abre el <code>JFileChooser</code> para buscar un directorio
     * 
     * @return la ruta del direcotorio
     */
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

    /**
     * convierte un txt a un <code>ArrayList<AddressEntry><code>
     * &#64;param path ruta del archivo
     * @return regresa un <code>ArrayList</code> con todas las entradas convertidas
     * @throws FileNotFoundException no encuentra el archivo
     */
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

    /**
     * funciona para exportar los contactos a otro archivo .txt
     * 
     * @param entry objeto a convertir en un archivo .txt o añádir a uno existente
     * @param path  ruta del archivo a editar o crear
     */
    public static void writeAddressToFile(AddressEntry entry, String path) {
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

    /**
     * lee un archivo
     * 
     * @param path directorio del archivo
     * @return el texto que contiene el archivo
     */
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

    /**
     * convierte un archivo json a un <code>Arraylist</code>
     * 
     * @param path directorio del archivo
     * @return lista con los directorios
     */
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

    /**
     * reescribe o crea sobre un archivo json
     * 
     * @param path        ruta del archivo
     * @param addressList convierte una lista de AdressList a un archivo json o
     *                    reescribe uno existente
     */
    public static void writeAddressOnJsonFile(String path, ArrayList<AddressEntry> addressList) {
        try (Writer writer = new FileWriter(path)) {
            Gson gson = new Gson();
            gson.toJson(addressList, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
