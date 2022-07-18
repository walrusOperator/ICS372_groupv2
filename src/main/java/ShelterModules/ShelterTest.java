package ShelterModules;
import static org.junit.Assert.*;

public class ShelterTest {

    @org.junit.Test
    public void getReceivingStatus(){
        Shelter shelter = new Shelter("12345");
        assertTrue(shelter.isReceiving());

    }

}
