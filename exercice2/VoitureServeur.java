import java.io.*;
import java.net.*;

public class VoitureServeur {
    public static void main(String[] args) {
        int port = 0;
        Scanner keyb = new Scanner(System.in);

       
        System.out.print("Port d'écoute : ");
        try {
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            System.err.println("Le paramètre n'est pas un entier.");
            System.exit(-1);
        }

        try {
            
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Attente de connexion...");

            
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connexion établie avec : " + clientSocket.getInetAddress() +
                    ":" + clientSocket.getPort());

 
            ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());

 
            Voiture voiture = (Voiture) input.readObject();
            System.out.println("Voiture reçue - Carburant initial : " + voiture.getCarburant());

          
            voiture.setCarburant(50);

           
            output.writeObject(voiture);

          
            input.close();
            output.close();
            clientSocket.close();
            serverSocket.close();
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
