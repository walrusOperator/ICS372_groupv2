package ShelterModules;

import java.util.ArrayList;
import java.util.List;

public class Shelter {
    private List<Animal> animalList = new ArrayList<>();
    private final String shelterID;
    private String shelterName;
    private static boolean receiving = true;

    Shelter(String shelterID){
        this.shelterID = shelterID;
        shelterName = "unlisted";
    }
    Shelter(String shelterId, String name){
        this.shelterID = shelterId;
        shelterName = name;
    }

    public boolean isReceiving(){
        return receiving;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> newlist) {
        animalList = newlist;
    }

    public String toString(){
        return shelterID;
    }
    /*
    returns a string of all animals in the list for specific shelter
     */
    public String showAnimals(){
        String str = "ShelterModules.Shelter: " + shelterID + "\n";

        for (int i = 0; i < size(); i++) {
            str += animalList.get(i).toString();
            str += "\n";
        }
        return str;
    }
    public int size(){
        return animalList.size();

    }
    /**
     * Method to toggle isReceiving attribute of a ShelterModules.Shelter object to receiving or not receiving based on
     * submitted shelter name and boolean value.
     * @param selected - (String) user submitted shelter name
     * @param status - (boolean) true enables receiving, false disables
     */
    public static void changeReceiving(String selected, boolean status) {
        if(status){
            receiving = true;
            System.out.println("Receiving enabled for shelter " + selected + "\n");
        } else {
            receiving = false;
            System.out.println("Receiving disabled for shelter " + selected + "\n");
        }
    }
    /**
     * Method responsible for adding ShelterModules.Animal object into previously created ShelterModules.Shelter object
     * @param newAnimal - (ShelterModules.Animal) animal object to be added to shelter object
     * @param selected - (String) shelter ID
     */
    public static void addUserCreatedAnimal(Animal newAnimal, String selected, ShelterList shelter){
        try{
            Shelter tempShelter = shelter.getShelter(selected);
            List<Animal> tempList = tempShelter.getAnimalList();
            tempList.add(newAnimal);
            tempShelter.setAnimalList(tempList);
             System.out.println("New ShelterModules.Animal has been added.\n");
        } catch (Exception e) {
            System.out.println("ShelterModules.Animal could not be added.\n");
        }
    }
}


