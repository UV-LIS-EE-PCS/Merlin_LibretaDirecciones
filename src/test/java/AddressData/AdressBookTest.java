package Addressdata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.addressdata.*;
import com.example.utilities.HighlightText;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

class AdressBookTest {
    private ArrayList<AddressEntry> listAddress = new ArrayList<AddressEntry>();
    private AddressEntry entry = new AddressEntry("rafael", "merlin", "hermenegildo galeana", "veracruz", "96030",
            "humer-merlin@hotmail.com", "9241397640");
    private AddressEntry entry2 = new AddressEntry("juan", "merlin", "hermenegildo galeana", "veracruz", "96030",
            "humer-merlin@hotmail.com", "9241397640");
    private AddressBook book;

    @Test
    @DisplayName("comprobar la agregacion de una AdressEntry a AdressBook")
    public void addAddressTest() {
        listAddress.add(entry);
        listAddress.add(entry2);
        book = new AddressBook("src/main/java/com/example/info/directorios/rafael.json",listAddress);
        AddressEntry entryToAdd = new AddressEntry("hernesto", "perez", "hermenegildo galeana", "veracruz", "96030",
                "humer-merlin@hotmail.com", "9241397640");

        int size = listAddress.size();
        book.addAddress(entryToAdd);
        Assertions.assertEquals(size + 1, listAddress.size());
        Assertions.assertNotEquals(listAddress.indexOf(entryToAdd), -1);

    }

    @Test
    @DisplayName("comprueba si regresa true como valor al agregar un elemento al adressBook que ya se encuentra en el book")
    public void addAddressIsRepeatTest() {
        AddressEntry entryToAdd = new AddressEntry("hernesto", "perez", "hermenegildo galeana", "veracruz", "96030",
                "humer-merlin@hotmail.com", "9241397640");
        // first add
        listAddress.add(entry);
        listAddress.add(entryToAdd);
        book = new AddressBook("src/main/java/com/example/info/directorios/rafael.json",listAddress);
        // second add
        boolean isRepeat = book.addAddress(entry);
        Assertions.assertEquals(true, isRepeat);
        isRepeat = book.addAddress(entryToAdd);
        Assertions.assertEquals(true, isRepeat);

    }

    @DisplayName("Comprobar la eliminación de un AddressBook")
    @Test
    public void deleteAdressTest() {
        listAddress.add(entry);
        listAddress.add(entry2);
        book = new AddressBook("src/main/java/com/example/info/directorios/rafael.json",listAddress);
        int size = listAddress.size();
        Assertions.assertNotEquals(-1, listAddress.indexOf(entry2));
        book.deleteAdress(entry2);
        Assertions.assertEquals(-1, listAddress.indexOf(entry2));
        Assertions.assertEquals(size - 1, listAddress.size());
    }

    @DisplayName("Comprobar el retorno de la funcion deleteAddress en un AddressBook cuando no existe dentro de este ultimo")
    @Test
    public void deleteAdressIsUnavableTest() {
        listAddress.add(entry);
        book = new AddressBook("src/main/java/com/example/info/directorios/rafael.json",listAddress);
        int size = listAddress.size();
        book.deleteAdress(entry2);
        Assertions.assertEquals(-1, listAddress.indexOf(entry2));
        Assertions.assertEquals(size, listAddress.size());
    }

    @DisplayName("Comprobar la funcionalidad de filterAdress")
    @Test
    public void filterAdressTest() {
        String search = "ra";
        listAddress.add(entry);
        listAddress.add(entry2);
        book = new AddressBook("src/main/java/com/example/info/directorios/rafael.json",listAddress);
        ArrayList<AddressEntry> filterlist = book.filterAddress(search);
        assertEquals(1, filterlist.size());
        assertEquals(entry, listAddress.get(0));
    }

    @DisplayName("Comprobar la funcionalidad de filterAdress, cuando no se encuentra ninguna coincidencia")
    @Test
    public void filterAdressNoCoincidenceTest() {
        String search = "roberto";
        listAddress.add(entry);
        listAddress.add(entry2);
        book = new AddressBook("src/main/java/com/example/info/directorios/rafael.json",listAddress);
        ArrayList<AddressEntry> filterlist = book.filterAddress(search);
        assertEquals(0, filterlist.size());
    }

    @DisplayName("comprobar la salida de find debera de ser un arraylist de pocisiones")
    @Test
    public void findTest() {

        String string = "test from findTest";
        String search = "from";
        int[] expected_positions = { 5, 9 };
        int[] positions = HighlightText.find(string, search).get(0);
        Assertions.assertEquals(expected_positions[0], positions[0]);
        Assertions.assertEquals(expected_positions[1], positions[1]);
    }

    @Test
    @DisplayName("comprueba el resaltado del texto del metodo highlightSearch")
    public void highlightSearch() {
        String string = "test from findTest";
        String search = "from";
        String textHighlightExpected = "test " + HighlightText.PURPLE_UNDERLINED + "from" + HighlightText.BLACK
                + " findTest" + HighlightText.BLACK;
        String textHighlight = HighlightText.highlightSearch(string, search);
        Assertions.assertEquals(textHighlightExpected, textHighlight);
    }

}
