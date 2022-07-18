package ShelterModules;

import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FileUtilities {

    /**
     * Method used to convert a json file into json array
     *
     * @return (JSONArray) - animals parsed from json input file
     */
    public static JSONArray readJSON(String filename) {
        JSONParser parser = new JSONParser();
        try {
            FileReader inputFile = new FileReader(filename);
            JSONObject obj = (JSONObject) parser.parse(inputFile);
            return (JSONArray) obj.get("shelter_roster");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HashMap loadJSON(String filename) {
        JSONArray shelters = readJSON(filename);
        HashMap<String, Shelter> shelterRoster = new HashMap<>();

        if (shelters == null) {
            return new HashMap<>();
        }
        try {
            for (Object tempshelter : shelters.toArray()) {
                JSONObject shelter = (JSONObject) tempshelter;
                String id = (String) shelter.get("shelter_id");
                String name = (String) shelter.get("shelter_name");
                boolean receiving = (Boolean) shelter.get("shelter_receiving");

                Shelter currShelter = new Shelter(id, name);

                JSONArray animallist = (JSONArray) shelter.get("animals");
                for (Object tempanimal : animallist) {
                    JSONObject animal = (JSONObject) tempanimal;
                    String aniType = (String) animal.get("animal_type");
                    String aniName = (String) animal.get("animal_name");
                    String aniID = (String) animal.get("animal_id");
                    Object temp = animal.get("weight");
                    String aniUnit = (String) animal.get("weight_unit");
                    long aniReceipt = (Long) animal.get("receipt_date");
                    double aniWeight;

                    if (temp instanceof Double) {
                        aniWeight = (Double) temp;
                    } else {
                        aniWeight = ((Long) temp).doubleValue();
                    }

                    Animal ani = new Animal(aniType, aniName, aniID, aniWeight, aniUnit, aniReceipt);
                    currShelter.addAnimal(ani);
                    currShelter.setReceiving(receiving);
                }
                shelterRoster.put(id, currShelter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return shelterRoster;
    }

    /**
     * Method used to create a JSON output file of the given shelterList
     *
     * @param roster - (ShelterModules.ShelterList) shelterList used to create the JSON output file
     */
    public static void writeJSON(ShelterList roster, String filename) {
        JSONArray sheltersToWrite = new JSONArray();
        JSONObject fileData = new JSONObject();
        List<Shelter> allShelters = new ArrayList<>(roster.getShelters());

        for (Shelter currentShelter : allShelters) {
            JSONObject currentShelterData = new JSONObject();
            JSONArray animalsInShelterData = new JSONArray();

            currentShelterData.put("shelter_id", currentShelter.getShelterID());
            currentShelterData.put("shelter_name", currentShelter.getShelterName());
            currentShelterData.put("shelter_receiving", currentShelter.isReceiving());
            for (int j = 0; j < currentShelter.size(); j++) {
                Animal currentAnimal = currentShelter.getAnimalList().get(j);
                JSONObject currentAnimalData = new JSONObject();

                try {
                    currentAnimalData.put("animal_type", currentAnimal.getAnimal_Type());
                    currentAnimalData.put("animal_name", currentAnimal.getAnimal_Name());
                    currentAnimalData.put("animal_id", currentAnimal.getAnimal_ID());
                    currentAnimalData.put("weight", currentAnimal.getAnimal_weight());
                    currentAnimalData.put("weight_unit", currentAnimal.getWeight_unit());
                    currentAnimalData.put("receipt_date", currentAnimal.getReceipt_date());
                } catch (Exception e) {
                    System.out.println("File creation unsuccessful");
                }
                animalsInShelterData.add(currentAnimalData);
            }
            currentShelterData.put("animals", animalsInShelterData);
            sheltersToWrite.add(currentShelterData);
        }
        fileData.put("shelter_roster", sheltersToWrite);

        try (FileWriter file = new FileWriter(filename)) {
            file.write(fileData.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Document readXML(String filename) {
        //https://initialcommit.com/blog/how-to-read-xml-file-in-java
        try {
            File xmlFile = new File(filename);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


