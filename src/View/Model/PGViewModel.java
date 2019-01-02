package View.Model;

import java.io.File;
import java.io.IOException;
import Model.PGModel;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;

public class PGViewModel {
	
	public ListProperty<char[]> PGBoradList=new SimpleListProperty<>();
	public BooleanProperty isGoal=new SimpleBooleanProperty();
	public IntegerProperty numSteps=new SimpleIntegerProperty();
	public IntegerProperty time=new SimpleIntegerProperty();
	
	private PGModel PGModel;
	
	public PGViewModel (PGModel PGModel) {
		this.PGModel=PGModel;
		this.isGoal.bind(PGModel.isGoal);
		this.numSteps.bind(PGModel.numSteps);
		this.time.bind(PGModel.time);
		this.PGBoradList.bind(PGModel.PGListBoard);
		
	/*	this.PGModel=PGModel;
		this.PGBorad.bind(PGModel.PGBorad);
		this.isGoal.bind(PGModel.isGoal);
		this.numSteps.bind(PGModel.numSteps);
		this.time.bind(PGModel.time);*/
	}
	public void start() {
		this.PGModel.start();
	}
	
	public void stop() {
		this.PGModel.stop();
	}
	
	public void connect (String serverIP, String serverPort) {
		this.PGModel.connect(serverIP, serverPort);
	}
	
	public void disconnect() {
		this.PGModel.disconnect();
	}
	
	public char[][] getPGBoard(){
		return this.PGModel.getPGBoard();
	}
	
	public boolean isGameOn() {
		return this.PGModel.isGameOn();
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
	 
	 public void saveGame(File file) {
		 this.PGModel.saveGame(file);
	 }
	 public boolean isPlayerFinish() throws IOException, InterruptedException {
		 return this.PGModel.isPlayerFinish();
	 }

}
