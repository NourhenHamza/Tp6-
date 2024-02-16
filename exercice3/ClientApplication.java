import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientApplication {
    public static void main(String[] args) {
        String serverHost = "localhost"; // Mettez l'adresse IP du serveur si nécessaire
        int serverPort = 10000; // Le port sur lequel le serveur écoute

        try {
            // Créer une connexion au serveur
            Socket socket = new Socket(serverHost, serverPort);

            // Initialiser les flux de sortie et d'entrée
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Demander les informations sur la personne à l'utilisateur
            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrez l'âge de la personne : ");
            int age = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la nouvelle ligne restante
            System.out.print("Entrez le nom de la personne : ");
            String nom = scanner.nextLine();

            // Envoyer les informations au serveur
            Personne personne = new Personne(age, nom);
            output.writeObject(personne);

            // Recevoir l'identificateur du client du serveur
            int clientID = input.readInt();
            System.out.println("Identificateur du client : " + clientID);

            // Fermer les flux et le socket
            output.close();
            input.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
