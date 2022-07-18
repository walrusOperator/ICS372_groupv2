package ShelterModules;

import org.json.simple.*;
import org.json.simple.JSONObject;

import java.util.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ShelterList {
    Map<String, Shelter> mapOfShelters = new HashMap<>();

    public void addHashMap(HashMap<String, Shelter> shelters){
        mapOfShelters.putAll(shelters);
    }

    public void addShelter(String id, Shelter shelter){
        mapOfShelters.put(id, shelter);
    }

    /**
     * Reads in specified JSON file to fill out the ShelterModules.ShelterList
     */


    /**
     * Takes a JSONObject and converts it into an ShelterModules.Animal, then adds it
     * mapOfShelters.
     * @param animal - (JSONObject) converted into an ShelterModules.Animal Object.
     */


    /**
     * Prints out the values of mapOfShelters directly to the console
     */
    public void showShelters(){
        System.out.println(mapOfShelters.values());
    }

    /**
     * Returns true if a given shelter id is found in mapOfShelters.
     * @param id - (String) user selected shelter id
     * @return (boolean) - if a given shelter exists
     */
    public boolean containsShelter(String id){
        return mapOfShelters.containsKey(id);
    }

    /**
     * Returns a ShelterModules.Shelter that corresponds to the
     * @param id - (String) user given id
     * @return (ShelterModules.Shelter) - ShelterModules.Shelter based on corresponding id.
     */
    public Shelter getShelter(String id){
        return mapOfShelters.get(id);
    }

    /**
     * Returns a Collection of the Shelters stored in mapOfShelters.
     * @return (Collection) - values of stored Shelters.
     */
    public Collection<Shelter> getShelters(){
        return mapOfShelters.values();}

    /**
     * Checks whether a given animal type is supported.
     * @param type - (String) user given animal type.
     * @return (boolean) - validity of given type.
     */
    public boolean validAnimal(String type){
        return type.equalsIgnoreCase("dog") || type.equalsIgnoreCase("cat") ||
                type.equalsIgnoreCase("bird") || type.equalsIgnoreCase("rabbit");
    }

    public static boolean shelterSearch(String selected, ShelterList shelters){
        if (shelters.containsShelter(selected)) {
            return true;
        } else {
            System.out.println("Invalid shelter ID\n");
        }
        return false;
    }

    /**
     * Method loops through all shelters in map and all animal objects in each shelter object
     * and prints all to console.
     */
    public static void showAllAnimals (ShelterList shelterMap) {
        List<Shelter> allShelters = new ArrayList<>(shelterMap.getShelters());
        for (Shelter currentShelter : allShelters) {
            System.out.println(currentShelter.showAnimals());
        }
    }

    public void addAnimalToShelter(String id, Animal animal){
        Shelter tempShelter = mapOfShelters.get(id);
        tempShelter.addAnimal(animal);
    }
}
