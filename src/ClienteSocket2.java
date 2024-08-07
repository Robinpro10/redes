import java.io.*;
import java.net.*;
public class ClienteSocket2 {
	public static void main(String[] args) throws Exception {
		Socket s=null;
		PrintWriter textoAlServidor;
   		BufferedReader textoDelTeclado;
   		DataInputStream textoDelServidor;
   		String tecleado;
		try {
			s=new Socket("127.0.0.1",1234);
   			System.out.println("Ya me conecte al servidor");
   			//Abrimos conexion texto del teclado
   			textoDelTeclado = new BufferedReader(new InputStreamReader(System.in));
   			
   			//texto al servidor
   			textoAlServidor = new PrintWriter(s.getOutputStream(),true);
   			
   			//texto del servidor
   			textoDelServidor = new DataInputStream(s.getInputStream());
			
   			do{
   				//Enviamos y recibimos informacion hasta 
   				//que se envie vacio el texto.
   				System.out.print("Servidor recibiendo informacion, teclea un texto: ");
   				tecleado = textoDelTeclado.readLine();
   				textoAlServidor.println(tecleado);
   				System.out.println("Respuesta = "+textoDelServidor.readUTF()); 
   			}while(tecleado.length()!=0);
   			System.out.println("Cliente termino de enviar");
   		   //cerramos conexiones abiertes
   			textoDelServidor.close();
   			textoDelTeclado.close();
   			textoAlServidor.close();
   			s.close();
   			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}