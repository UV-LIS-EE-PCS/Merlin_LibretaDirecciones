package AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.example.AdressData.*;
import java.util.ArrayList;
class AdressBookTest {
    private ArrayList<AdressEntry> listAddress = new ArrayList<AdressEntry>();
    private AdressEntry entry = new AdressEntry("rafael","merlin","hermenegildo galeana","veracruz","96030","humer-merlin@hotmail.com","9241397640");
    private AdressEntry entry2 = new AdressEntry("juan","merlin","hermenegildo galeana","veracruz","96030","humer-merlin@hotmail.com","9241397640");
    private AdressBook book;

    
    @Test
    @DisplayName("comprobar la agregacion de una AdressEntry a AdressBook")
    public void addAddressTest(){
        listAddress.add(entry);
        listAddress.add(entry2);
        book = new AdressBook(listAddress);
        AdressEntry entryToAdd = new AdressEntry("hernesto","perez","hermenegildo galeana","veracruz","96030","humer-merlin@hotmail.com","9241397640");
        
        int size = listAddress.size();
        book.addAddress(entryToAdd);
        Assertions.assertEquals(size+1,listAddress.size());
        Assertions.assertNotEquals(listAddress.indexOf(entryToAdd),-1);
        
    }

    @Test
    @DisplayName("comprueba si regresa true como valor al agregar un elemento al adressBook que ya se encuentra en el book")
    public void addAddressIsRepeatTest(){
        AdressEntry entryToAdd = new AdressEntry("hernesto","perez","hermenegildo galeana","veracruz","96030","humer-merlin@hotmail.com","9241397640");        
        //first add
        listAddress.add(entry);
        listAddress.add(entryToAdd);
        book = new AdressBook(listAddress);
        //second add
        boolean isRepeat = book.addAddress(entry);
        Assertions.assertEquals(true, isRepeat);
        isRepeat = book.addAddress(entryToAdd);
        Assertions.assertEquals(true,isRepeat);
        
    }

    @DisplayName("Comprobar la eliminación de un AddressBook")
    @Test
    public void deleteAdressTest(){
        listAddress.add(entry);
        listAddress.add(entry2);
        book = new AdressBook(listAddress);
        int size = listAddress.size();
        Assertions.assertNotEquals(-1,listAddress.indexOf(entry2));
        book.deleteAdress(entry2);
        Assertions.assertEquals(-1,listAddress.indexOf(entry2));
        Assertions.assertEquals(size-1,listAddress.size());
    }

    @DisplayName("Comprobar el retorno de la funcion deleteAddress en un AddressBook cuando no existe dentro de este ultimo")
    @Test
    public void deleteAdressIsUnavableTest(){
        listAddress.add(entry);
        book = new AdressBook(listAddress);
        int size = listAddress.size();
        book.deleteAdress(entry2);
        Assertions.assertEquals(-1,listAddress.indexOf(entry2));
        Assertions.assertEquals(size,listAddress.size());
    }
    
}
