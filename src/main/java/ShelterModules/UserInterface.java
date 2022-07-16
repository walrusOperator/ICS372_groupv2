package ShelterModules;

import java.util.Scanner;

public class UserInterface {
    static Scanner scan = new Scanner(System.in);

    public static int userOption(){
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    public static void printMenu(){
        System.out.println("Please choose from the following options:\n" +
                "1: Import json of animals\n" +
                "2: Add additional incoming animal\n" +
                "3: Enable receiving animals\n" +
                "4: Disable receiving animals\n" +
                "5: See animals in a shelter\n" +
                "6: See animals in ALL shelters\n" +
                "7: Export json file\n" +
                "8: Import xml of animals");
    }

    public static String shelterSelection(){
        System.out.println("Please select a shelter: ");
        String selected;
        selected = scan.nextLine();
        return selected;
    }

}
