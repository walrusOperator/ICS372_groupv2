package ShelterModules;


import java.util.HashMap;

public class Main {
    private static final ShelterList shelterMap = new ShelterList();
    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        shelterMap.addHashMap(Utilities.loadJSON("Save_Data.json"));
        do {
            UserInterface.printMenu();
            int userSelection = UserInterface.userOption();
            userMenu(userSelection, shelterMap);
            Utilities.writeJSON(shelterMap, "Save_Data.json");
        }while(true);
    }

    public static void userMenu(int userOption, ShelterList shelterMap) {

        String selected;
        String filename;

        switch (userOption) {
            //call to add all shelters and animals from JSON file
            case 1:
                filename = "Project1_input.json";
                shelterMap.addIncomingJSON(filename);
                break;

            //validate shelter ID, if shelter exists create ShelterModules.Animal object and add to existing ShelterModules.Shelter object
            case 2:
                shelterMap.showShelters();
                selected = UserInterface.shelterSelection();
                if(ShelterList.shelterSearch(selected, shelterMap)) {
                    Animal newAnimal = Animal.createNewAnimal(shelterMap);
                    if(newAnimal != null) {
                        Shelter.addUserCreatedAnimal(newAnimal, selected, shelterMap);
                    }
                }
                break;

            //Check for existing shelter, if exists update shelter to accept incoming animals
            case 3:
                shelterMap.showShelters();
                selected = UserInterface.shelterSelection();
                if(ShelterList.shelterSearch(selected, shelterMap)) {
                    shelterMap.getShelter(selected).changeReceiving(true);
                }
                break;

            //Check for existing shelter, if exists update shelter to deny incoming animals
            case 4:
                shelterMap.showShelters();
                selected = UserInterface.shelterSelection();
                if(ShelterList.shelterSearch(selected, shelterMap)) {
                    shelterMap.getShelter(selected).changeReceiving(false);
                }
                break;

            //validate that shelter exists, print all objects contained in shelter object
            case 5:
                shelterMap.showShelters();
                selected = UserInterface.shelterSelection();
                if(ShelterList.shelterSearch(selected, shelterMap)){
                    System.out.println(shelterMap.getShelter(selected).showAnimals());
                }
                break;

            //call method to display all ShelterModules.Animal objects to console by shelter
            case 6:
                ShelterList.showAllAnimals(shelterMap);
                break;

            //call ShelterModules.Utilities method to create a JSON file of the shelterMap hashmap
            case 7:
                filename = "ProjectOutput.json";
                Utilities.writeJSON(shelterMap, filename);
                break;
            case 8:
                filename = "sample.xml";
                shelterMap.addIncomingXML(filename);
                break;
        }
    }
}

