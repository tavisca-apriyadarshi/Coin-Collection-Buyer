package day7.buyer;

import day7.AuctionDetails;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Buyer {
    String name;
    String id;

    private Socket socket = null;
    //private Socket registerSocket = null;
    Scanner sc = new Scanner(System.in);

    public Buyer(String name, String id) throws IOException {
        this.name = name;
        this.id = id;

        socket = new Socket("localhost",1138);
        //registerSocket = socket;
        System.out.println("Press   for\n" +
                "1       Available Auctions\n" +
                "2       Select Auction\n" +
                "3       Logout");
        boolean flag = true;
        while (flag){
            //socket = registerSocket;
            System.out.println("Enter your choice");
            String choice = sc.next();
            switch (choice){
                case "1":
                    System.out.println(getResultFromRegistry("availableAuctions", "allAuctions"));
                    break;
                case "2":
                    System.out.println("Enter Auction Id:");
                    String aId = sc.next();
                    System.out.println(getResultFromRegistry("selectAuction", aId));
                    System.out.println("Want to raise/change the bid?(y/n)");
                    String ans = sc.next();
                    if (ans.equals("y")){
                        System.out.println( "Press \"r/R\" to enter a higher bid\n" +
                                "Press \"c/C\" to cancel the bid");
                        ans = sc.next();
                        switch (ans){
                            case "r":
                            case "R":
                                System.out.println("Enter the raised value:");
                                float raise = sc.nextFloat();
                                sendRaiseAndUserToRegistry(raise);
                                break;
                            case "c":
                            case "C":
                                System.out.println("are you sure you want to cancel bid?(y/n)");
                                ans = sc.next();
                                if(ans.equals("y")){
                                    sendRaiseAndUserToRegistry(0f);
                                }else{}
                        }
                    }else{}
                    break;
                case "3":
                    flag = false;
                    break;
                default:
                    System.out.println("Enter a valid choice");
            }
        }
    }
    /*private String getAvailableAuction() throws IOException {
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        String sellers = dataInputStream.readUTF();
        dataInputStream.close();
        return sellers;
    }

    private void selectAuction() throws IOException {
        System.out.println("Enter auction Id:");
        String choice = sc.next();
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }*/
    private void sendRaiseAndUserToRegistry(float raise) throws IOException {
        AuctionDetails auctionDetails = new AuctionDetails();
        auctionDetails.userId = id;
        auctionDetails.UserName = name;
        auctionDetails.value = raise;
        Action action = new Action();
        action.setActionType("Bid");
        action.setAuctionDetails(auctionDetails);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(action);
        objectOutputStream.close();
    }

    private String getResultFromRegistry(String type, String value) throws IOException {
        Action action = new Action();
        action.setActionType(type);
        action.setActionValue(value);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(action);
        objectOutputStream.close();

        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        String result = dataInputStream.readUTF();
        dataInputStream.close();

        return result;
    }
}
