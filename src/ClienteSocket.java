import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteSocket {
    public static void main(String[] args) {
        String direccionServidor = "localhost"; // Dirección del servidor
        int puerto = 12345; // Puerto del servidor

        try (Socket socket = new Socket(direccionServidor, puerto);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado al servidor");

            int[] numeros = new int[10];
            System.out.println("Por favor, ingrese 10 números:");
            for (int i = 0; i < 10; i++) {
                System.out.print("Número " + (i + 1) + ": ");
                numeros[i] = scanner.nextInt();
                out.writeInt(numeros[i]);
            }

            String resultado = in.readUTF();
            System.out.println("Respuesta del servidor: " + resultado);

        } catch (IOException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
}

