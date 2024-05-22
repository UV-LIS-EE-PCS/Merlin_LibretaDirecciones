package AddressData;
import com.example.AdressData.AdressEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AddressEntryTest{
    private AdressEntry entry = new AdressEntry("rafael","merlin","hermenegildo galeana","veracruz","96030","humer-merlin@hotmail.com","9241397640");
    
    
    @Test
    public void getNameTest(){
        String name = "rafael";
        AdressEntry entry = new AdressEntry(name,"merlin", "hermenegildo galeana", "veracruz", "96030","rafaelmerlinprieto@gmail.com", "9242449018");
        Assertions.assertEquals(name, entry.getName());
    }
    
    @Test
    public void setNameTest(){
        String name = "juan";
        Assertions.assertNotEquals(this.entry.getName(), name);
        this.entry.setName(name);
        Assertions.assertEquals(this.entry.getName(), name);
    }

    @Test
    public void getLastNameTest(){
        String lastName = "merlin";
        AdressEntry entry = new AdressEntry("rafael",lastName, "hermenegildo galeana", "veracruz", "96030","rafaelmerlinprieto@gmail.com", "9242449018");
        
        Assertions.assertEquals(lastName, entry.getLastName());
    }
    
    @Test
    public void setLastNameTest(){
        String lastName = "lopez";
        Assertions.assertNotEquals(this.entry.getLastName(), lastName);
        this.entry.setLastName(lastName);
        Assertions.assertEquals(this.entry.getLastName(), lastName);
    }

    @Test
    public void getStreetTest(){
        String street = "hermenegildo galeana";
        AdressEntry entry = new AdressEntry("rafael","merlin", street, "veracruz", "96030","rafaelmerlinprieto@gmail.com", "9242449018");
        Assertions.assertEquals(street, entry.getStreet());
    }

    @Test
    public void setStreetTest(){
        String newStreet = "simon bolivar";
        Assertions.assertNotEquals(newStreet, this.entry.getStreet());
        this.entry.setStreet(newStreet);
        Assertions.assertEquals(this.entry.getStreet(), newStreet);
    }

    @Test
    public void getStateTest(){
        String state = "veracruz";
        AdressEntry entry = new AdressEntry("rafael","merlin", "hermenegildo galeana", state, "96030","rafaelmerlinprieto@gmail.com", "9242449018");
        Assertions.assertEquals(state,entry.getState());
    }

    @Test
    public void setStateTest(){
        String newState = "chiapas";
        Assertions.assertNotEquals(this.entry.getState(), newState);
        this.entry.setState(newState);
        Assertions.assertEquals(this.entry.getState(),newState);
    }

    @Test
    public void getPostalCodeTest(){
        String postalCode = "96030";
        AdressEntry entry = new AdressEntry("rafael","merlin", "hermenegildo galeana", "veracruz",postalCode,"rafaelmerlinprieto@gmail.com", "9242449018");
        Assertions.assertEquals(postalCode, entry.getPostalCode());
    }

    @Test
    public void setPostalCodeTest(){
        String newPostalCode = "93000";
        Assertions.assertNotEquals(newPostalCode,this.entry.getPostalCode() );
        this.entry.setPostalCode(newPostalCode);
        Assertions.assertEquals(this.entry.getPostalCode(), newPostalCode);
    }

    @Test
    public void getEmailTest(){
        String email = "rafaelmerlinprieto@gmail.com";
        AdressEntry entry = new AdressEntry("rafael","merlin", "hermenegildo galeana", "veracruz","96030",email, "9242449018");
        Assertions.assertEquals(email, entry.getEmail());
    }

    @Test
    public void setEmailTest(){
        String newEmail = "notMorales@gmail.com";
        Assertions.assertNotEquals(this.entry.getEmail(), newEmail);
        this.entry.setEmail(newEmail);
        Assertions.assertEquals(this.entry.getEmail(), newEmail);
    }
    
    @Test
    public void getPhoneTest(){
        String phone = "9242449018";
        AdressEntry entry = new AdressEntry("rafael","merlin", "hermenegildo galeana", "veracruz","96030","rafaelmerlinprieto@gmail.com",phone);
        Assertions.assertEquals(phone, entry.getPhone());
    }
    
    @Test
    public void setPhoneTest(){
        String newPhone = "8198967890";
        Assertions.assertNotEquals(this.entry.getPhone(),newPhone);
        this.entry.setPhone(newPhone);
        Assertions.assertEquals(this.entry.getPhone(), newPhone);
    }
    
    
}