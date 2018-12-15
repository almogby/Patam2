package View;

import java.io.File;

import javafx.stage.FileChooser;

public class MainWindowController {
	
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
}
