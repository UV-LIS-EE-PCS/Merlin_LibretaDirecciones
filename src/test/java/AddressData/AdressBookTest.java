package Addressdata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.addressdata.*;
import com.example.utilities.HighlightText;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;

class AdressBookTest {
  

    @Test
    @DisplayName("comprobar la agregacion de una AdressEntry a AdressBook")
    public void addAddressTest() throws FileNotFoundException {
        AddressBook book = AddressBook.getInstance("src/main/java/com/example/info/directorios/rafael.json");
        AddressEntry entryToAdd = new AddressEntry("hernesto", "perez", "hermenegildo galeana", "veracruz", "96030",
                "humer-merlin@hotmail.com", "9241397640");  
        int size = book.getSize();
        book.addAddress(entryToAdd);
        Assertions.assertEquals(size + 1, book.getSize());

    }

    @Test
    @DisplayName("comprueba si regresa true como valor al agregar un elemento al adressBook que ya se encuentra en el book")
    public void addAddressIsRepeatTest() throws FileNotFoundException {
        AddressBook book = AddressBook.getInstance("src/main/java/com/example/info/directorios/rafael.json");
        AddressEntry entryToAdd = new AddressEntry("hernesto", "perez", "hermenegildo galeana", "veracruz", "96030",
        "humer-merlin@hotmail.com", "9241397640");  
          
        // second add
        book.addAddress(entryToAdd);
        boolean isRepeat = book.addAddress(entryToAdd);
        
        Assertions.assertEquals(true, isRepeat);

    }

    @DisplayName("Comprobar la eliminación de un AddressBook")
    @Test
    public void deleteAdressTest() throws FileNotFoundException {
        AddressBook book = AddressBook.getInstance("src/main/java/com/example/info/directorios/rafael.json");
        AddressEntry entryToDelete = new AddressEntry("hernesto", "perez", "hermenegildo galeana", "veracruz", "96030",
        "humer-merlin@hotmail.com", "9241397640");  
        book.addAddress(entryToDelete);
        int size = book.getSize();
        book.deleteAdress(entryToDelete);
        Assertions.assertEquals(size-1, book.getSize());
    }

    @DisplayName("Comprobar la accion de la funcion deleteAddress en un AddressBook cuando no existe dentro de este ultimo")
    @Test
    public void deleteAdressIsUnavableTest() throws FileNotFoundException {
        double randomNumber =  Math.random();
        AddressBook book = AddressBook.getInstance("src/main/java/com/example/info/directorios/rafael.json");
        AddressEntry entryToDelete = new AddressEntry("este registro", "no existe", "en este AdressBook", "veracruz", "96030",
        "humer-merlin@hotmail.com", "no"+randomNumber);  
        int size =  book.getSize();
        book.deleteAdress(entryToDelete);
        Assertions.assertEquals(size, book.getSize());

    }

    @DisplayName("Comprobar la funcionalidad de filterAdress")
    @Test
    public void filterAdressTest() throws FileNotFoundException {
        double randomNumber =  Math.random();
        AddressBook book = AddressBook.getInstance("src/main/java/com/example/info/directorios/rafael.json");
        AddressEntry entry = new AddressEntry("registro"+randomNumber, "no existe", "en este AdressBook", "veracruz", "96030",
        "humer-merlin@hotmail.com", "no"+randomNumber);
        book.addAddress(entry);
        ArrayList<AddressEntry> address= book.filterAddress("registro"+randomNumber);
        assertEquals(address.size(),1);
    }

    @DisplayName("Comprobar la funcionalidad de filterAdress, cuando no se encuentra ninguna coincidencia")
    @Test
    public void filterAdressNoCoincidenceTest() throws FileNotFoundException {
        AddressBook book = AddressBook.getInstance("src/main/java/com/example/info/directorios/rafael.json");
        
        ArrayList<AddressEntry> address = book.filterAddress("name"+Math.random());
        assertEquals(address.isEmpty(), true);
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
