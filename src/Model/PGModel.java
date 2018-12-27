package Model;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;

public class PGModel {
	public ListProperty<char[]> PGBorad;
	public BooleanProperty isGoal;
	public IntegerProperty numSteps;
	
	private Socket serverSocket;
	
	public void connect (String serverIP, String serverPort) {
		try {
			this.serverSocket = new Socket(serverIP, Integer.parseInt(serverPort));
			System.out.println("Server Connect");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void disconnect () {
		if (serverSocket!=null)
			try {
				this.serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
