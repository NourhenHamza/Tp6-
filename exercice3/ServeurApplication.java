import java.io.*;
import java.net.*;

public class ServeurApplication {
    private static int clientCounter = 0;

    public static void main(String[] args) {
        int serverPort = 10000;  

        try {
             
            ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println("Le serveur est en attente de connexions...");

            while (true) {
                
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connexion établie avec : " + clientSocket.getInetAddress() +
                        ":" + clientSocket.getPort());

                 

                
                ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
 
                Personne personne = (Personne) input.readObject();
                System.out.println("Informations reçues du client " + clientID + " : " + personne);

                
                output.writeInt(clientID);
                output.flush();
  
                output.close();
                input.close();
                clientSocket.close();
            }
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
