package ShelterModules;
import static org.junit.Assert.*;

public class ShelterTest {

    @org.junit.Test
    public void getReceivingStatus(){
        Shelter shelter = new Shelter("12345");
        //test that default status is true
        assertTrue(shelter.isReceiving());

        //test that changeReceiving
        shelter.changeReceiving(shelter.isReceiving());
        assertFalse(shelter.isReceiving());

        shelter.changeReceiving(shelter.isReceiving());
        assertTrue(shelter.isReceiving());

    }

}
