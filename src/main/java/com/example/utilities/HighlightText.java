package com.example.utilities;

import java.util.ArrayList;

/**
 * una clase para dar estilo a la terminal y busquedas
 */
public class HighlightText {

    /**
     * default constructor
     */
    HighlightText() {

    }

    /**
     * Text Reset
     */
    public static final String RESET = "\033[0m";

    // Regular Colors

    /**
     * Regular Black color
     */
    public static final String BLACK = "\033[0;30m";

    /**
     * Regular Red color
     */
    public static final String RED = "\033[0;31m";

    /**
     * Regular Green color
     */
    public static final String GREEN = "\033[0;32m";

    /**
     * Regular Yellow color
     */
    public static final String YELLOW = "\033[0;33m";

    /**
     * Regular Blue color
     */
    public static final String BLUE = "\033[0;34m";

    /**
     * Regular Purple color
     */
    public static final String PURPLE = "\033[0;35m";

    /**
     * Regular Cyan color
     */
    public static final String CYAN = "\033[0;36m";

    /**
     * Regular White color
     */
    public static final String WHITE = "\033[0;37m";

    // Bold

    /**
     * Bold Black color
     */
    public static final String BLACK_BOLD = "\033[1;30m";

    /**
     * Bold Red color
     */
    public static final String RED_BOLD = "\033[1;31m";

    /**
     * Bold Green color
     */
    public static final String GREEN_BOLD = "\033[1;32m";

    /**
     * Bold Yellow color
     */
    public static final String YELLOW_BOLD = "\033[1;33m";

    /**
     * Bold Blue color
     */
    public static final String BLUE_BOLD = "\033[1;34m";

    /**
     * Bold Purple color
     */
    public static final String PURPLE_BOLD = "\033[1;35m";

    /**
     * Bold Cyan color
     */
    public static final String CYAN_BOLD = "\033[1;36m";

    /**
     * Bold White color
     */
    public static final String WHITE_BOLD = "\033[1;37m";

    // Underline

    /**
     * Underlined Black color
     */
    public static final String BLACK_UNDERLINED = "\033[4;30m";

    /**
     * Underlined Red color
     */
    public static final String RED_UNDERLINED = "\033[4;31m";

    /**
     * Underlined Green color
     */
    public static final String GREEN_UNDERLINED = "\033[4;32m";

    /**
     * Underlined Yellow color
     */
    public static final String YELLOW_UNDERLINED = "\033[4;33m";

    /**
     * Underlined Blue color
     */
    public static final String BLUE_UNDERLINED = "\033[4;34m";

    /**
     * Underlined Purple color
     */
    public static final String PURPLE_UNDERLINED = "\033[4;35m";

    /**
     * Underlined Cyan color
     */
    public static final String CYAN_UNDERLINED = "\033[4;36m";

    /**
     * Underlined White color
     */
    public static final String WHITE_UNDERLINED = "\033[4;37m";

    // Background

    /**
     * Black background color
     */
    public static final String BLACK_BACKGROUND = "\033[40m";

    /**
     * Red background color
     */
    public static final String RED_BACKGROUND = "\033[41m";

    /**
     * Green background color
     */
    public static final String GREEN_BACKGROUND = "\033[42m";

    /**
     * Yellow background color
     */
    public static final String YELLOW_BACKGROUND = "\033[43m";

    /**
     * Blue background color
     */
    public static final String BLUE_BACKGROUND = "\033[44m";

    /**
     * Purple background color
     */
    public static final String PURPLE_BACKGROUND = "\033[45m";

    /**
     * Cyan background color
     */
    public static final String CYAN_BACKGROUND = "\033[46m";

    /**
     * White background color
     */
    public static final String WHITE_BACKGROUND = "\033[47m";

    // High Intensity

    /**
     * High intensity Black color
     */
    public static final String BLACK_BRIGHT = "\033[0;90m";

    /**
     * High intensity Red color
     */
    public static final String RED_BRIGHT = "\033[0;91m";

    /**
     * High intensity Green color
     */
    public static final String GREEN_BRIGHT = "\033[0;92m";

    /**
     * High intensity Yellow color
     */
    public static final String YELLOW_BRIGHT = "\033[0;93m";

    /**
     * High intensity Blue color
     */
    public static final String BLUE_BRIGHT = "\033[0;94m";

    /**
     * High intensity Purple color
     */
    public static final String PURPLE_BRIGHT = "\033[0;95m";

    /**
     * High intensity Cyan color
     */
    public static final String CYAN_BRIGHT = "\033[0;96m";

    /**
     * High intensity White color
     */
    public static final String WHITE_BRIGHT = "\033[0;97m";

    // Bold High Intensity

    /**
     * Bold high intensity Black color
     */
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m";

    /**
     * Bold high intensity Red color
     */
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";

    /**
     * Bold high intensity Green color
     */
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m";

    /**
     * Bold high intensity Yellow color
     */
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";

    /**
     * Bold high intensity Blue color
     */
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";

    /**
     * Bold high intensity Purple color
     */
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";

    /**
     * Bold high intensity Cyan color
     */
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";

    /**
     * Bold high intensity White color
     */
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m";

    // High Intensity backgrounds

    /**
     * High intensity Black background color
     */
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";

    /**
     * High intensity Red background color
     */
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";

    /**
     * High intensity Green background color
     */
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";

    /**
     * High intensity Yellow background color
     */
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";

    /**
     * High intensity Blue background color
     */
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";

    /**
     * High intensity Purple background color
     */
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m";

    /**
     * High intensity Cyan background color
     */
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";

    /**
     * High intensity White background color
     */
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";

    /**
     * resalta el texto de busqueda
     * 
     * @param string cadena en la cual se resaltara el texto de busqued
     * @param search texto de busqueda de una cadena
     * @return cadena con el texto de busqueda resaltado
     */
    public static String highlightSearch(String string, String search) {
        ArrayList<int[]> listOfPositions = find(string, search);
        String finalString = "";
        int indexCounter = 0;
        for (int i = 0; i < string.length(); i++) {

            if (i == listOfPositions.get(indexCounter)[0]) {
                finalString += HighlightText.PURPLE_UNDERLINED;
            }
            if (i == listOfPositions.get(indexCounter)[1]) {
                finalString += HighlightText.BLACK;
                if (listOfPositions.size() == indexCounter + 1) {
                    indexCounter = listOfPositions.size() - 1;
                } else {
                    indexCounter++;
                }

            }
            finalString += string.charAt(i);
        }

        return finalString + HighlightText.BLACK;

    }

    /***
     * encuentra las coincidencias de la busqueda
     * 
     * @param string cadena por la cual buscar coincidencia
     * @param search cadena que contiene la coincidencia deseada
     * @return Arraylist que contiene las pocisiones de inicio y fin de las
     *         coincidencias
     */

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