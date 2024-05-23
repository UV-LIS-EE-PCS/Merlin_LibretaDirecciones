package Address;

import java.util.ArrayList;

import com.example.Adress.Menu;
import com.example.AdressData.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
// private void toAdd() {

//     boolean isCorrectEntry = false;
//     String name = "no data", lastName = "no data", street = "no data", state = "no data", postalCode = "no data",
//             email = "no data",
//             phone = "no data";

//     System.out.print(ConsoleColors.BLACK + "Nombre: " + ConsoleColors.RED);
//     name = scan.nextLine();

//     System.out.print(ConsoleColors.BLACK + "Apellido: " + ConsoleColors.RED);
//     lastName = scan.nextLine();

//     System.out.print(ConsoleColors.BLACK + "Calle: " + ConsoleColors.RED);
//     street = scan.nextLine();

//     System.out.print(ConsoleColors.BLACK + "Estado: " + ConsoleColors.RED);
//     state = scan.nextLine();

//     isCorrectEntry = false;
//     while (!isCorrectEntry) {
//         System.out.print(ConsoleColors.BLACK + "Código postal: " + ConsoleColors.RED);
//         postalCode = scan.nextLine();
//         if (postalCode.length() == 5 && regexComparation("\\d+", postalCode)) {
//             isCorrectEntry = true;
//         } else {
//             System.out.println("codigo postal invalido, debe de ser un numero y contener 5 caracteres");
//         }
//     }

//     isCorrectEntry = false;
//     while (!isCorrectEntry) {
//         System.out.print(ConsoleColors.BLACK + "Correo Electrónico: " + ConsoleColors.RED);
//         email = scan.nextLine();
//         if (regexComparation("^(.+)@(.+)$", email)) {
//             isCorrectEntry = true;
//         } else {
//             System.out.println("inserta un correo valido Ejemplo : rafael@gmail.com");
//         }
//     }

//     isCorrectEntry = false;
//     while (!isCorrectEntry) {
//         System.out.print(ConsoleColors.BLACK + "Número de teléfono: " + ConsoleColors.RED);
//         phone = scan.nextLine();
//         if (phone.length() == 10 && regexComparation("\\d+", phone)) {
//             isCorrectEntry = true;
//         } else {
//             System.out.println("Numero invalido , el número debe de contener 10 caracteres");
//         }
//     }

//     AdressEntry newEntry = new AdressEntry(name, lastName, street, state, postalCode, email, phone);
//     book.addAddress(newEntry);
//     System.out.println(
//             "\n~" + ConsoleColors.PURPLE_BOLD + "+Dirección agregada con exito!" + ConsoleColors.BLACK);

// }

public class MenuTest {
    private ArrayList<AdressEntry> listAddress = new ArrayList<AdressEntry>();
    private AdressEntry entry = new AdressEntry("rafael","merlin","hermenegildo galeana","veracruz","96030","humer-merlin@hotmail.com","9241397640");
    private AdressEntry entry2 = new AdressEntry("juan","merlin","hermenegildo galeana","veracruz","96030","humer-merlin@hotmail.com","9241397640");
    private AdressBook book;



    

}
