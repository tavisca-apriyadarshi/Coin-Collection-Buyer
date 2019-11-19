package day7.registry;

import day7.AuctionDetails;
import day7.Communicator;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Registry {
    private static Map<String, AuctionDetails> Sellers = new HashMap<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1138);
            Socket socket = serverSocket.accept();
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Communicator communicator = (Communicator) objectInputStream.readObject();
            objectInputStream.close();

            String result = processCommunicator(communicator);

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(result);
            dataOutputStream.close();

            serverSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Registry Server Error");
            e.printStackTrace();
        }
    }

    private static String processCommunicator(Communicator communicator) {
        String type = communicator.getActionType();
        switch (type){
            case "availableAuctions": return getSellers();
            case "selectAuction":
                return ""+Sellers.get(communicator.getActionValue());
            case "Bid": Sellers.put("",communicator.getAuctionDetails());
            return "";
            default: return "Not a Correct Choice";
        }
    }

    private static AuctionDetails getSeller(String name){
        return Sellers.get(name);
    }

    private static String getSellers(){
        String servers = "";
        for (Map.Entry<String,AuctionDetails> entry : Sellers.entrySet())
            servers += entry.getKey() + " \n";//System.out.println(entry.getKey());

        return servers;
    }
}
