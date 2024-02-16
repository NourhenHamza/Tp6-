import java.io.*;
import java.net.*;
import java.util.Scanner;

class SocketClient {
    public static void main(String argv[]) {
        int port = 0;
        String host = "";
        Scanner keyb = new Scanner(System.in);

        //  Demande du nom du serveur à l'utilisateur
        System.out.print("Nom du serveur : ");
        host = keyb.next();

        //  Demande du port du serveur à l'utilisateur
        System.out.print("Port du serveur : ");

        try {
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            System.err.println("Le second paramètre n'est pas un entier.");
            System.exit(-1);
        }

        try {
            //  Résolution de l'adresse IP du serveur
            InetAddress adr = InetAddress.getByName(host);
            
            //   Création du socket client
            Socket socket = new Socket(adr, port);
            
            //  Initialisation des flux de sortie et d'entrée
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            
            //   Envoi d'une chaîne au serveur
            output.writeObject(new String("ma première socket"));
            
            //  Lecture de la réponse du serveur
            String chaine = (String) input.readObject();
            System.out.println(" recu du serveur : " + chaine);
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
