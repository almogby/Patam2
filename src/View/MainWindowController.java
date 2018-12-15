package View;

import java.io.File;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import View.PipeDisplayer;

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
	}
	
	public void start() {
		System.out.println("Start");
	}
	
	public void openFile() {
		FileChooser fc= new FileChooser();
		fc.setTitle("open maze file");
		fc.setInitialDirectory(new File("./resources"));
		//fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Only XML", "*.xml"));
		File chosen = fc.showOpenDialog(null);
		if (chosen != null) {
			System.out.println(chosen.getName());
		}
	}
	
	public void goal() {
		pipeDisplayer.goalButton();
	}

}
