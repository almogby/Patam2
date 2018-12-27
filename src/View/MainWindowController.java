package View;


import java.io.File;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import javafx.stage.FileChooser;

import View.PipeDisplayer;
import View.Themes.PottsChip;
import View.Themes.Theme;
import View.Themes.TimonPumba;


public class MainWindowController implements Initializable {
	
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

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		pipeDisplayer.setPipeData(pipeData);
		pipeDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->{pipeDisplayer.clickedOnPosition(e);});
	}
	
	public void start() {
		System.out.println("Start");
	}
	
	public void openFile() {
		FileChooser fc= new FileChooser();
		fc.setTitle("Open maze file");
		fc.setInitialDirectory(new File("./resources"));
		//fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Only XML", "*.xml"));
		File chosen = fc.showOpenDialog(null);
		if (chosen != null) {
			System.out.println(chosen.getName());
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
	public void goal() {
		pipeDisplayer.goalButton();
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
