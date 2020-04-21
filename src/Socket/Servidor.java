
package Socket;
import javax.swing.*;

import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
//Hace dos tareas recibe la informacion del cliente y la segunda tarea es permanecer a la escucha todo el tiempo 
//HILOS THREADS PARA PODER HACER AMBAS COSAS AL MISMO TIEMPO
public class Servidor  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoServidor mimarco=new MarcoServidor();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}	
}

class MarcoServidor extends JFrame implements Runnable {
	
	public MarcoServidor(){
		
		setBounds(800,200,280,350);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		
		milamina.add(areatexto,BorderLayout.CENTER);
		
		add(milamina);
		
		setVisible(true);
		
		Thread mihilo=new Thread(this);
		mihilo.start();
		
		}
	
	private	JTextArea areatexto;

	
	@Override
	public void run() {
		
	try {
		ServerSocket miserver=new ServerSocket(9999);//Pusimos a la escuha nuestra app
		
		String nick, ip,mensaje;
		
		PaqueteEnvio paquete_recibido; 
		while(true) {
		Socket misocket=miserver.accept();
		//Crear un input stream
		
		ObjectInputStream paquete_Datos=new ObjectInputStream(misocket.getInputStream());
		
		paquete_recibido=(PaqueteEnvio) paquete_Datos.readObject();
		
		misocket.close();
		
		nick=paquete_recibido.getNick();
		
		ip=paquete_recibido.getIp();
		
		mensaje=paquete_recibido.getMensaje();
		
		areatexto.append("\n"+ nick+": "+mensaje+"para "+ip);
		
		
		}
		
	} catch (IOException | ClassNotFoundException e) {
		e.printStackTrace();
	}
	
	
	
		}
	
	
	
}
