package Address;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import com.example.Adress.*;
import com.example.AdressData.AddressBook;
import com.example.AdressData.AddressEntry;

public class MenuTest {
    private ArrayList<AddressEntry> listAddress = new ArrayList<AddressEntry>();
    private AddressEntry entry = new AddressEntry("rafael", "merlin", "hermenegildo galeana", "veracruz", "96030",
            "humer-merlin@hotmail.com", "9241397640");
    private AddressEntry entry2 = new AddressEntry("juan", "merlin", "hermenegildo galeana", "veracruz", "96030",
            "humer-merlin@hotmail.com", "9241397640");
    private AddressBook book;

    @DisplayName("comprueba la agregacion de un nuevo elemento al AddressBook")
    @Test
    public void toAddTest() {
        listAddress.add(entry);
        listAddress.add(entry2);
        book = new AddressBook(listAddress);
        int initialSize = listAddress.size();

        String simulatedInput = "oliver\narbol\narboleda no.203\nveracruz\n12345\narboleate@gmail.com\n1234567890\n";
        InputStream originalIn = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        Menu menu = new Menu(book);
        Scanner scan = new Scanner(System.in);

        menu.toAdd(scan);

        System.setIn(originalIn);

        Assertions.assertEquals(initialSize + 1, listAddress.size());
    }

    @DisplayName("comprueba la eliminacion de un nuevo elemento al AddressBook")
    @Test
    public void toDeleteTest() {
        listAddress.add(entry);
        listAddress.add(entry2);
        book = new AddressBook(listAddress);
        int initialSize = listAddress.size();

        String simulatedInput = "rafael\nsi\n";
        InputStream originalIn = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        Menu menu = new Menu(book);
        Scanner scan = new Scanner(System.in);

        menu.toDelete(scan);

        System.setIn(originalIn);

        Assertions.assertEquals(initialSize - 1, listAddress.size());
    }

    @DisplayName("comprueba la eliminacion de mas de un elemento elemento del AddressBook")
    @Test
    public void toDeleteTestForMultipleRemove() {
        listAddress.add(entry);
        listAddress.add(entry2);
        book = new AddressBook(listAddress);

        String simulatedInput = "a\n0,1\n";
        InputStream originalIn = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        Menu menu = new Menu(book);
        Scanner scan = new Scanner(System.in);

        menu.toDelete(scan);

        System.setIn(originalIn);

        Assertions.assertEquals(true, listAddress.isEmpty());
    }
}