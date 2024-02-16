import java.io.*;
import java.net.*;

public class ServeurApplication {
    private static int clientCounter = 0;

    public static void main(String[] args) {
        int serverPort = 10000; // Le port sur lequel le serveur écoute

        try {
            // Créer le serveur socket
            ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println("Le serveur est en attente de connexions...");

            while (true) {
                // Accepter la connexion d'un client
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connexion établie avec : " + clientSocket.getInetAddress() +
                        ":" + clientSocket.getPort());

                // Créer un nouvel identificateur pour le client
                int clientID = ++clientCounter;

                // Initialiser les flux de sortie et d'entrée
                ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());

                // Recevoir les informations sur la personne du client
                Personne personne = (Personne) input.readObject();
                System.out.println("Informations reçues du client " + clientID + " : " + personne);

                // Envoyer l'identificateur du client au client
                output.writeInt(clientID);
                output.flush();

                // Fermer les flux et le socket pour ce client
                output.close();
                input.close();
                clientSocket.close();
            }
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
