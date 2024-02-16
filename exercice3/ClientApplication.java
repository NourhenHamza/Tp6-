import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientApplication {
    public static void main(String[] args) {
        String serverHost = "localhost";  
        int serverPort = 10000;  
        try {
             
            Socket socket = new Socket(serverHost, serverPort);

             
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

             
            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrez l'âge de la personne : ");
            int age = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Entrez le nom de la personne : ");
            String nom = scanner.nextLine();

            
            Personne personne = new Personne(age, nom);
            output.writeObject(personne);

             
            int clientID = input.readInt();
            System.out.println("Identificateur du client : " + clientID);

             
            output.close();
            input.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
