package View;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Task;

import Model.PGModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import javafx.stage.FileChooser;

import View.PipeDisplayer;
import View.Model.PGViewModel;
import View.NakedObject.NakedMsg;
import View.NakedObject.NakedObjDisplayer;
import View.NakedObject.ServerConfig;
import View.Themes.PottsChip;
import View.Themes.Theme;
import View.Themes.TimonPumba;



public class MainWindowController implements Initializable {
	
	private PGViewModel PGVM;
	private PGModel PGM;
	
    private NakedObjDisplayer nakedObjDisplayer = new NakedObjDisplayer();
    private ServerConfig serverConfig = new ServerConfig();
	
	@FXML
	PipeDisplayer pipeDisplayer;
	@FXML
	Label numStepsText;
	@FXML
	Label timeText;
	@FXML
	Button solve;
	@FXML
	Button start;
	
	 @Override
	    public void initialize(URL location, ResourceBundle resources) {
		 PGM = new PGModel();
		 PGVM = new PGViewModel(PGM);
		 
		 setThemePottsChip();
		 pipeDisplayer.setPipeData(PGVM.getPGBoard());
		 pipeDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->{
			 int col = (int) (e.getX() / pipeDisplayer.getW());
		     int row = (int) (e.getY() / pipeDisplayer.getH());
		     PGVM.getNextClick(col, row);
		     pipeDisplayer.redraw();
		     //check if goal
		     try {
		    	 PGVM.connect(serverConfig.getIP(), serverConfig.getPort());
				if (this.PGVM.isPlayerFinish())
					 winMsg();
				PGVM.disconnect();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 });


	     PGVM.PGBoradList.addListener((observable, oldValue, newValue) -> pipeDisplayer.setPipeData(PGVM.PGBoradList.toArray(new char[PGVM.PGBoradList.size()][])));
	     PGVM.numSteps.addListener((observable, oldValue, newValue) -> this.numStepsText.setText(Integer.toString(PGVM.numSteps.get())));
	     PGVM.time.addListener((observable, oldValue, newValue) -> this.timeText.setText(Integer.toString(PGVM.time.get())));
	     
	    }
	 
	 
	 public void solve() {
		 stop();
	        Task<Void> task = new Task<Void>() {
	            @Override
	            protected Void call() throws Exception {
	                try {
	                    System.out.println("Solve clicked ");
	                    Platform.runLater(() -> System.out.println("Start Solving"));
	                    PGVM.connect(serverConfig.getIP(), serverConfig.getPort());
	                    PGVM.solve();
	                    pipeDisplayer.redraw();
	                    PGVM.disconnect();
	                    Platform.runLater(() -> System.out.println("Server Status: Disconnected"));
	                } catch (IOException e) {
	                    Platform.runLater(() -> System.out.println("Server Status: Couldn't connect to the server"));
	                    e.printStackTrace();
	                }
	                return null;
	            }
	        };
	        new Thread(task).start();

	 }

	
	public void start() {
		this.PGVM.start();
		start.setDisable(true);
		System.out.println("Start");
	}
	
	
	public void stop() {
		this.PGVM.stop();
		start.setDisable(false);
		System.out.println("Stop");
	}
	
	public void serverConfig() {
		nakedObjDisplayer.display(this.serverConfig);
		
	}
	
	public void winMsg() {
		NakedMsg winMsg = new NakedMsg(pipeDisplayer.winMsg());
		nakedObjDisplayer.display(winMsg);
	}
	
	public void howToPlayMsg() {
		NakedMsg howToPlay = new NakedMsg(pipeDisplayer.howToPlayMsg());
		nakedObjDisplayer.display(howToPlay);
	}
	
	public void openFile() throws IOException {
		FileChooser fc= new FileChooser();
		fc.setTitle("Open pipe game file");
		fc.setInitialDirectory(new File("./resources"));
		
		FileChooser.ExtensionFilter txtExtensionFilter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
		fc.getExtensionFilters().add(txtExtensionFilter);
		fc.setSelectedExtensionFilter(txtExtensionFilter);
		File chosen = fc.showOpenDialog(null);
		if (chosen != null) {
			System.out.println(chosen.getName());
			this.PGVM.loadGame(chosen.getAbsolutePath());
		}
		else
			System.out.println("not found");
		
	}
	
	
	public void saveFile() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Save pipe game");
		FileChooser.ExtensionFilter fcef = new FileChooser.ExtensionFilter("Text Only", "*.txt");
		
		fc.getExtensionFilters().add(fcef);
		fc.setSelectedExtensionFilter(fcef);
		
		File fileToSave = fc.showSaveDialog(null);
		if (fileToSave!=null);
			this.PGVM.saveGame(fileToSave);
	}	

	public void setThemePottsChip() {
		Theme pottsChipTheme = new PottsChip();
		pipeDisplayer.setPipeTheme(pottsChipTheme);
	}
	
	public void setThemeTimonPumba() {
		Theme timonPumbaTheme = new TimonPumba();
		pipeDisplayer.setPipeTheme(timonPumbaTheme);
	}

	
	

}
