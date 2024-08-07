import java.io.*;
import java.net.*;

public class ServidorSocket {
    public static void main(String[] args) {
        int puerto = 12345; // Puerto en el que el servidor escuchará las conexiones

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            while (true) {
                try (Socket socket = serverSocket.accept();
                     DataInputStream in = new DataInputStream(socket.getInputStream());
                     DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                    System.out.println("Cliente conectado");

                    int[] numeros = new int[10];
                    for (int i = 0; i < 10; i++) {
                        numeros[i] = in.readInt();
                    }

                    int max = numeros[0];
                    int min = numeros[0];
                    int suma = 0;
                    for (int num : numeros) {
                        if (num > max) {
                            max = num;
                        }
                        if (num < min) {
                            min = num;
                        }
                        suma += num;
                    }

                    String resultado = String.format("El número mayor es %d, el número menor es %d, y la suma de todos los números digitados es %d", max, min, suma);
                    out.writeUTF(resultado);

                    System.out.println("Resultados enviados al cliente: " + resultado);
                } catch (IOException e) {
                    System.err.println("Error en la conexión con el cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}
