package models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Scanner;

import controllers.ChatViewController;

/**
 * Model class for the chat client
 * 
 * @author Nico
 *
 */
public class ChatClientModel {
	
	private Socket socket;
	private DataInputStream buffer_in=null;
	private DataOutputStream buffer_out=null;
	private Scanner sc=new Scanner(System.in);
	
	public static final String ES="es";
	public static final String EN="en";
	
	public static String IP="localhost";
	public static int PORT=9090;
	public static String END_COMMAND="/salir";
	//TODO language changing support

	/**
	 * Method for establishing the connection with the server
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * 
	 */
	public void establishConnection() throws UnknownHostException, IOException {
		socket=new Socket(IP, PORT);
		System.out.println("Established connection with "+socket.getInetAddress().getHostName());
	}
	
    /**
     * Method for initiating the Input Streams
     * @throws IOException 
     */
	public void initBuffers() throws IOException {
		buffer_in=new DataInputStream(socket.getInputStream());
		buffer_out=new DataOutputStream(socket.getOutputStream());
		buffer_out.flush();
	}
	
    /**
     * Method for getting and displaying messages
     * @throws IOException 
     */
	public void getData(ChatViewController controller) {
		String st="";
		try {
			while (true) {
				st=(String) buffer_in.readUTF();
				
//				if (st.equals("end")) {
//					closeConnection();
//					return;
//				}
				
				controller.getMsg(st);
			}
		} catch (IOException e) {}
	}
	
    /**
     * Method for writing and sending messages
     * @throws IOException 
     */
	public void writeData(String msg) throws IOException {
		buffer_out.writeUTF(msg);
		buffer_out.flush();
	}
	
	public void selectLang(String lang) throws Exception {
		if (lang.equals(ES) || lang.equals(EN)) {
			buffer_out.writeUTF(lang);
			buffer_out.flush();
			return;
		} else throw new Exception("Language not supported");
	}
	
    /**
     * Method for closing connection
     */
	public void closeConnection() {
		try {
			buffer_in.close();
			buffer_out.close();
			socket.close();
		} catch (IOException e) {
			System.err.println("Error while closing connection: "+e.getMessage());
		} finally {
//			System.out.println("Conversation ended.");
//			System.exit(0);
		}
	}
	
    /**
     * Main method for starting up the server and placing it in a thread
     *
     */
	public void execConnection(ChatViewController controller, String lang) {
		
		Thread thr=new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						establishConnection();
						initBuffers();
						selectLang(lang);
						getData(controller);
					} catch (UnknownHostException e) {
						System.err.println("Unknown host. -> "+e.getMessage());
					} catch (IOException e) {
						System.err.println("IO exception. -> "+e.getMessage());
						controller.getMsg("Nuestro servidor no se encuentra disponible, por favor, intentelo mas tarde.");
						return;
					} catch (Exception e) {
						System.err.println(e.getMessage());
					} finally {
//						closeConnection();
					}
				}
				
			}
		});
		
		thr.start();
	}

	/**
	 * @return the iP
	 */
	public static String getIP() {
		return IP;
	}

	/**
	 * @return the pORT
	 */
	public static int getPORT() {
		return PORT;
	}

	/**
	 * @return the END_COMMAND
	 */
	public static String getEND_COMMAND() {
		return END_COMMAND;
	}

	/**
	 * @param iP the iP to set
	 */
	public static void setIP(String iP) {
		IP = iP;
	}

	/**
	 * @param pORT the pORT to set
	 */
	public static void setPORT(int pORT) {
		PORT = pORT;
	}

	/**
	 * @param END_COMMAND the END_COMMAND to set
	 */
	public static void setEND_COMMAND(String END_COMMAND) {
		END_COMMAND = END_COMMAND;
	}
	
	

}
