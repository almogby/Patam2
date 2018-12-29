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
	public IntegerProperty time;
	
	private PGModel PGModel;
	
	public PGViewModel (PGModel PGModel) {
		this.PGModel=PGModel;
		this.PGBorad.bind(PGModel.PGBorad);
		this.isGoal.bind(PGModel.isGoal);
		this.numSteps.bind(PGModel.numSteps);
		this.time.bind(PGModel.time);
	}
	
	public void connect (String serverIP, String serverPort) {
		this.PGModel.connect(serverIP, serverPort);
	}
	
	public void disconnect() {
		this.PGModel.disconnect();
	}
	
	public void solve() throws IOException, InterruptedException {
		this.PGModel.solve();
	}
	
	public void getNextClick(int row,int col) {
		this.PGModel.getNextClick(row, col);
	}
	
	 public void loadGame(String fileName){
		 this.PGModel.loadGame(fileName);
	 }

}
