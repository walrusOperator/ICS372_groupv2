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

    /**
     * Reads in specified JSON file to fill out the ShelterModules.ShelterList
     */
    public void addIncomingJSON(String filename) {
        JSONArray j = Utilities.readJSON(filename);
//      https://howtodoinjava.com/java/library/json-simple-read-write-json-examples/
        assert j != null;
        j.forEach(animal -> parseAnimalObject( (JSONObject) animal));
    }

    public void addIncomingXML(String filename){
        Document doc = Utilities.readXML(filename);
        NodeList nodeList = doc.getElementsByTagName("Shelter");

        for (int i = 0; i < nodeList.getLength(); i++) {
            //https://stackoverflow.com/questions/4138754/getting-an-attribute-value-in-xml-element
            Node currNode = nodeList.item(i);

            if(currNode.getNodeType() == Node.ELEMENT_NODE) {
                Element shelterEle = (Element) currNode;
                String id = currNode.getAttributes().getNamedItem("id").getNodeValue();
                String name = shelterEle.getElementsByTagName("Name").item(0).getChildNodes().item(0).getNodeValue();
                Shelter shelter = new Shelter(id, name);
                mapOfShelters.put(id, shelter);

                NodeList animalList = currNode.getChildNodes();
                for (int j = 0; j < animalList.getLength(); j++) {
                    Node aniNode = animalList.item(j);

                    if(aniNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element aniEle = (Element) aniNode;
                        String aniType;
                        String aniID;
                        String aniName;
                        String aniWeightUnit;
                        double aniWeight;
                        long aniReceipt;

                        if (aniNode.getAttributes().getNamedItem("type") != null) {
                            aniType = aniNode.getAttributes().getNamedItem("type").getNodeValue();
                            aniID = aniNode.getAttributes().getNamedItem("id").getNodeValue();
                        } else{
                            aniType = "unlisted";
                            aniID = "unlisted";
                        }
                        if (aniEle.getElementsByTagName("Name").item(0) != null) {
                            aniName = aniEle.getElementsByTagName("Name").item(0).getTextContent();
                        } else{
                            aniName = "unlisted";
                        }
                        if (aniNode.getAttributes().getNamedItem("Weight") != null) {
                            aniWeightUnit = aniEle.getElementsByTagName("Weight").item(0).getAttributes().getNamedItem("unit").getNodeValue();
                            aniWeight = Double.parseDouble(aniEle.getElementsByTagName("Weight").item(0).getTextContent());
                        } else{
                            aniWeightUnit = "";
                            aniWeight = 0.0;
                        }
                        if (aniEle.getElementsByTagName("ReceiptDate").item(0) != null) {
                            aniReceipt = Long.parseLong(aniEle.getElementsByTagName("ReceiptDate").item(0).getTextContent());
                        } else{
                            aniReceipt = 1111111111;
                        }
                        //checks for blank animal entry
                        if(!(aniType.equals("unlisted") && aniName.equals("unlisted")&& aniID.equals("unlisted") && aniWeight == 0.0 && aniWeightUnit.equals("") && aniReceipt == 1111111111)) {
                            Animal tempAnimal = new Animal(aniType, aniName, aniID, aniWeight, aniWeightUnit, aniReceipt);
                            addAnimalToShelter(id, tempAnimal);
                        }
                    }
                }
            }
        }


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
            Animal tempAnimal = new Animal(animal_type, animal_name, animal_id, animal_weight, "", receipt_date);
            if (!(mapOfShelters.containsKey(shelter_id))) {
                Shelter tempShelter = new Shelter(shelter_id);
                mapOfShelters.put(shelter_id, tempShelter);
            }
            addAnimalToShelter(shelter_id, tempAnimal);
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
