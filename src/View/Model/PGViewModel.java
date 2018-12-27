package View.Model;

import java.io.IOException;
import Model.PGModel;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;

public class PGViewModel {
	
	public ListProperty<char[]> PGBorad;
	public BooleanProperty isGoal;
	public IntegerProperty numSteps;
	
	private PGModel PGModel;
	
	public PGViewModel (PGModel PGModel) {
		this.PGModel=PGModel;
	}
	
	public void connect (String serverIP, String serverPort) {
		this.PGModel.connect(serverIP, serverPort);
	}
	
	public void disconnect() {
		this.PGModel.disconnect();
	}
	

}
