package ShelterModules;

import org.json.simple.*;
import org.json.simple.JSONObject;

import java.util.*;

public class ShelterList {
    Map<String, Shelter> mapOfShelters = new HashMap<>();

    /**
     * Reads in specified JSON file to fill out the ShelterModules.ShelterList
     */
    public void addIncoming() {
        JSONArray j = Utilities.readJSON();
//      https://howtodoinjava.com/java/library/json-simple-read-write-json-examples/
        assert j != null;
        j.forEach(animal -> parseAnimalObject( (JSONObject) animal));
    }

    /**
     * Takes a JSONObject and converts it into an ShelterModules.Animal, then adds it
     * mapOfShelters.
     * @param animal - (JSONObject) converted into an ShelterModules.Animal Object.
     */
    private void parseAnimalObject(JSONObject animal) {
        String shelter_id = (String) animal.get("shelter_id");
        String animal_type = (String) animal.get("animal_type");
        String animal_name = (String) animal.get("animal_name");
        String animal_id = (String) animal.get("animal_id");
        Object temp = animal.get("weight");
        double animal_weight;

        // checks incoming weight value and converts value to double
        if(temp instanceof Double){
            animal_weight = (Double) temp;
        } else {
            animal_weight = ((Long) temp).doubleValue();
        }
        long receipt_date = (long) animal.get("receipt_date");

        // if type is valid, creates ShelterModules.Animal and add to correct ShelterModules.Shelter. Creates shelter if it doesn't exist
        if (validAnimal(animal_type)) {
            Animal tempAnimal = new Animal(animal_type, animal_name, animal_id, animal_weight, receipt_date);
            if (!(mapOfShelters.containsKey(shelter_id))) {
                Shelter tempShelter = new Shelter(shelter_id);
                mapOfShelters.put(shelter_id, tempShelter);
            }
            Shelter tempShelter = mapOfShelters.get(shelter_id);
            List<Animal> tempAnimalList = tempShelter.getAnimalList(); //get current list from shelter object in map
            tempAnimalList.add(tempAnimal); //add new animal to list
            tempShelter.setAnimalList(tempAnimalList); //set revised animal list into ShelterModules.Shelter Object
            mapOfShelters.put(shelter_id, tempShelter); //replace previous map entry with updated key value pair.
        }
    }

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
            if (shelters.getShelter(selected).isReceiving()) {
                return true;
            } else {
                System.out.println("This shelter is not receiving animals\n");
                return false;
            }
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
            System.out.println("ShelterModules.Shelter ID: " + currentShelter);
            for (int j = 0; j < currentShelter.size(); j++) {
                System.out.println(currentShelter.getAnimalList().get(j));
            }
            System.out.println();
        }
    }
}
