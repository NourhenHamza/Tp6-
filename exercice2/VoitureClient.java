import java.io.*;
import java.net.*;
import java.util.Scanner;

class VoitureClient {
    public static void main(String[] args) {
        int port = 0;
        String host = "";
        Scanner keyb = new Scanner(System.in);

       
        System.out.print("Nom du serveur : ");
        host = keyb.next();
        System.out.print("Port du serveur : ");
        try {
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            System.err.println("Le second paramètre n'est pas un entier.");
            System.exit(-1);
        }

        try {
            
            InetAddress adr = InetAddress.getByName(host);
            Socket socket = new Socket(adr, port);

 
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            
            Voiture voiture = new Voiture("SUV", "Toyota");
            output.writeObject(voiture);

         
            Voiture voitureModifiee = (Voiture) input.readObject();
            System.out.println("Carburant actuel après modification : " + voitureModifiee.getCarburant());

            
            output.close();
            input.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
