package Socket;
import java.net.*;
import java.awt.event.*;
import java.io.*;
import java.io.IOException;

import javax.swing.*;


public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoCliente mimarco=new MarcoCliente();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}


class MarcoCliente extends JFrame{
	
	public MarcoCliente(){
		
		setBounds(200,200,280,350);
				
		LaminaMarcoCliente milamina=new LaminaMarcoCliente();
		
		add(milamina);
		
		setVisible(true);
		}	
	
}

class LaminaMarcoCliente extends JPanel{
	
	public LaminaMarcoCliente(){
		nick=new JTextField(5);
		
		add(nick);
		
	
		JLabel texto=new JLabel("CHAT");
		
		add(texto);
		
		ip=new JTextField(5);
		
		add(ip);
		
		campochat=new JTextArea(12,18);
		
		add(campochat);
	
		campo1=new JTextField(20);
	
		add(campo1);		
	
		miboton=new JButton("Enviar");
		
		EnviaTexto mievento=new EnviaTexto();
		
		miboton.addActionListener(mievento);
		
		add(miboton);	
		
	}
	
	private class EnviaTexto implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//System.out.println(campo1.getText());
			try {
				Socket misocket =new Socket("192.168.0.8",9999);//Direccion ip de algun sevidor en este caso la mia
				
				PaqueteEnvio datos=new PaqueteEnvio();
				
				datos.setNick(nick.getText());
				
				datos.setIp(ip.getText());
				
				datos.setMensaje(campo1.getText());
				
				ObjectOutputStream paquete_datos=new ObjectOutputStream(misocket.getOutputStream());
				
				paquete_datos.writeObject(datos);
				
				misocket.close();
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		
		
		
	}
	
	
		
	private JTextArea campochat;	
		
	private JTextField campo1,nick,ip;
	
	private JButton miboton;
	
}

class PaqueteEnvio implements Serializable{
	
	private static final long serialVersionUID = -5880756945788727175L;
	private String nick;
	private String ip;
	private String mensaje;
	
	//-------------
	
	
	
	
	//-----------GETTER Y SETTER-------------
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
}