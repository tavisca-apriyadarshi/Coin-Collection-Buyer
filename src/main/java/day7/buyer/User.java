package day7.buyer;

import java.io.IOException;
import java.util.Scanner;

public class User {
    static String name = "Akshay";
    static String id = "1068";
    public static void main(String[] args) {
        System.out.println("Press 1 to Buy\nPress 2 to Sell\nEnter your choice: ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        switch (choice){
            case "1":
                try {
                    Buyer buyer = new Buyer(name, id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "2":
                break;
            default:
                System.out.println("Enter a valid choice");
        }
    }
}
