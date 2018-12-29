package View;


import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
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
	
	PGViewModel PGVM;
	public ListProperty<char[]> PGBorad;
	public BooleanProperty isGoal;
	public IntegerProperty numSteps;
	public IntegerProperty time;
	
	
    private NakedObjDisplayer nakedObjDisplayer = new NakedObjDisplayer();
    private ServerConfig serverConfig = new ServerConfig();
   
    char [][] pipeData = {
            {'s', 'L', 'F', '-', 'J', '7', '7', '7' , '7'},
            {'7', '7', '7', '7', '7', '7', '7', '7' , '7'},
            {'7', '7', '7', '7', '7', '7', '7', '7' , '7'},
            {'7', '7', '7', '7', '|', '7', '7', '7' , '7'},
            {'7', '7', '7', '7', 'L', '7', '7', '7' , '7'},
            {'7', '7', '7', '7', '7', '7', '7', '7' , '7'},
            {'7', '7', '7', '7', '|', '7', '7', '7' , '7'},
            {'7', '7', '-', '-', '-', '-', '-', '-' , 'g'},
    };
	
	@FXML
	PipeDisplayer pipeDisplayer;
	@FXML
	TextField numStepsText;
	@FXML
	TextField timeText;
	
/*	public void setViewModel (PGViewModel PGVM) {
		this.PGVM=PGVM;
		//Get solution from model and call to setPipeData to redraw
		this.PGBorad.bind(this.PGVM.PGBorad);
		this.buildUiOfSolution(PGVM);
		
		this.isGoal.bind(this.PGVM.isGoal);
		this.goalChecking(PGVM);
		
		this.mouseEvent(PGVM);
		
		this.numSteps.bindBidirectional(this.PGVM.numSteps);
		this.getAndShowNumOfSteps(PGVM);
		
		this.time.bind(this.PGVM.time);
		this.getAndShowTime(PGVM);
	
	}
	*/
	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		pipeDisplayer.setPipeData(pipeData);
		pipeDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->{pipeDisplayer.clickedOnPosition(e);});
	/*	setThemePottsChip();
		pipeDisplayer.insertImages();*/
	}
	
/*	private void getAndShowTime(PGViewModel PGVM) {
		this.time.addListener((observableValue, s, t1) -> {
			this.timeText.setText(Integer.toString(time.get()));
		});
	}
	
	private void getAndShowNumOfSteps (PGViewModel PGVM) {
		this.numSteps.addListener((observableValue, s, t1) -> {
			this.numStepsText.setText(Integer.toString(numSteps.get()));
		});
	}
	
	private void mouseEvent(PGViewModel PGVM) {
		pipeDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, 
				(e)->{
					double w = pipeDisplayer.getWidth() / PGBorad.get(0).length;
					double h = pipeDisplayer.getHeight() / PGBorad.size();
					int x = (int) (e.getX() / w);
					int y = (int) (e.getY() / h);
					PGVM.getNextClick(x, y);
				//	pipeDisplayer.redraw();
					});
	}
	
	private void buildUiOfSolution (PGViewModel PGVM) {
		this.PGBorad.addListener((observableValue, s, t1) -> {
			pipeDisplayer.setPipeData(this.PGBorad.toArray(new char[this.PGBorad.size()][]));
		});
	}
	
	private void goalChecking(PGViewModel PGVM) {
		this.isGoal.addListener((observableValue, s, t1) -> {
			if (isGoal.get() == true) {
				NakedMsg nm = new NakedMsg("You Won!");
				//nm.addMessage("Number of steps: " + numSteps.get());
				// Reset number of steps after solve
				numSteps.set(0);
				nakedObjDisplayer.display(nm);
			}
		});
	}*/


	
	public void start() {
		System.out.println("Start");
	}
	
	
	public void serverConfig() {
		nakedObjDisplayer.display(this.serverConfig);
		
	}
	
	public void howToPlayMsg() {
		NakedMsg howToPlay = new NakedMsg(pipeDisplayer.howToPlayMsg());
		nakedObjDisplayer.display(howToPlay);
	}
	
	public void openFile() throws IOException {
		FileChooser fc= new FileChooser();
		fc.setTitle("Open maze file");
		fc.setInitialDirectory(new File("./resources"));
		
		FileChooser.ExtensionFilter txtExtensionFilter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
		fc.getExtensionFilters().add(txtExtensionFilter);
		fc.setSelectedExtensionFilter(txtExtensionFilter);
		File chosen = fc.showOpenDialog(null);
		if (chosen != null) {
			System.out.println(chosen.getName());
			//PGVM.loadGame(chosen.getPath());
		}
	}
	
	public void saveFile() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Save maze");
		FileChooser.ExtensionFilter fcEf = new FileChooser.ExtensionFilter("Only Texts", "*.txt");
		
		fc.getExtensionFilters().add(fcEf);
		fc.setSelectedExtensionFilter(fcEf);
		
		File sFile = fc.showSaveDialog(null);
		if (sFile!=null);
			//TODO add save in ViewModel PipeGame
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
