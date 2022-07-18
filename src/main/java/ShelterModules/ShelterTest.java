package ShelterModules;
import static org.junit.Assert.*;

public class ShelterTest {

    @org.junit.Test
    public void getReceivingStatusTest(){
        Shelter shelter = new Shelter("12345");
        //test that default status is true
        assertTrue(shelter.isReceiving());

        //test that changeReceiving toggles to false when status starts as true
        shelter.changeReceiving(shelter.isReceiving());
        assertFalse(shelter.isReceiving());

        //test that changeReceiving toggles to true when status starts as false
        shelter.changeReceiving(shelter.isReceiving());
        assertTrue(shelter.isReceiving());

    }

    @org.junit.Test
    public void addAnimalReceivingTest(){
        Shelter shelter = new Shelter("12345");
        Animal testAnimal = new Animal(
                "bird", "test", "12345",
                0.5, "lb", 1732299329);

        //test that animals can not be added to a shelter that is not receiving
        shelter.setReceiving(false);
        shelter.addAnimal(testAnimal);
        assertEquals(shelter.size(), 0);

        //test that animals can be added to a shelter that is receiving
        shelter.setReceiving(true);
        shelter.addAnimal(testAnimal);
        assertEquals(shelter.size(), 1);

    }

    @org.junit.Test
    public void addShelterMissingFieldsTest(){
        Shelter testShelter = new Shelter("12345");
        //test that default value for isReceiving is true
        assertTrue(testShelter.isReceiving());

        //test that if shelterName is not provided, "unlisted is provided"
        assertEquals(testShelter.getShelterName(), "unlisted");

    }

    @org.junit.Test
    public void addAnimalTest(){
        //test that
    }
}
