import java.io.*;
import java.net.*; 
public class ServidorSocket2{
   	public static void main(String[] args){
   		ServerSocket ss = null;
   		Socket s = null;
   		BufferedReader textoRecibidoDelCliente;
   		DataOutputStream textoEnviarAlCliente;
   		String stringRecibido;
   
   		try{
   			ss = new ServerSocket(1234);
   			System.out.println("Servidor escuchando");
   			//Esperando a recibir una conexion de cliente en puerto 5432
 			s = ss.accept();
   			System.out.println("Ya se conecto el cliente");
   			textoRecibidoDelCliente = new BufferedReader(new InputStreamReader(s.getInputStream()));
 			textoEnviarAlCliente = new DataOutputStream(s.getOutputStream());
 			// Recibir y responder hasta que llegue vacio
   			do{
   				stringRecibido = textoRecibidoDelCliente.readLine();
   				System.out.println("Llego el texto: " + stringRecibido);
   				//Respondemos al cliente. En este caso, devolvemos respuesta si hemos encontrado
   				//la vocal a en el texto recibido
   			    String stringBuscar = "a";
   			    int posicionResultado=stringRecibido.indexOf(stringBuscar);
   			    //indexOf si encuentra la vocal a devolvera un entero con el n�mero de posicion, 
   			    //si no encuentra devuelve -1
   			    if (posicionResultado == -1){
   				textoEnviarAlCliente.writeUTF("No se encontro la vocal a. ");}
   			    else {
   			    textoEnviarAlCliente.writeUTF("Si se encontro la vocal a");
   			    }
   			}while(stringRecibido.length()!=0);
   			//Enviamos mensaje de cierre de conexion.
   			System.out.println("Ya termine de recibir, cerrando conexi�n");
   			textoEnviarAlCliente.close();
   			textoRecibidoDelCliente.close();
   			s.close();
   			ss.close(); 
   		}catch (IOException e){
   			System.err.println(e.getMessage());
   			System.exit(1);
   		}
   	}
}