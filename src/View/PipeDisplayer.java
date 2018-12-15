package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class PipeDisplayer extends Canvas{
	
	char[][] pipeData;
	int cRow, cCol;
	
	//pics fileName
	private StringProperty backgroundFileName;
	private StringProperty startPointFileName;
	private StringProperty targetPointFileName;
	private StringProperty winFileName;
	private StringProperty curvedPipeFileName;
	private StringProperty straightPipeFileName;
	
	
	public PipeDisplayer() {
		this.startPointFileName = new SimpleStringProperty();
		this.targetPointFileName = new SimpleStringProperty();
		this.winFileName = new SimpleStringProperty();
		this.curvedPipeFileName = new SimpleStringProperty();
		this.straightPipeFileName = new SimpleStringProperty();
		cCol=0;
		cRow=1;
	}
	
	public void setPipeData (char[][] pipeData) {
		this.pipeData=pipeData;
	}

	public String getBackgroundFileName() {
		return backgroundFileName.get();
	}


	public void setBackgroundFileName(String backgroundFileName) {
		this.backgroundFileName.set(backgroundFileName);
	}
	public String getStartPointFileName() {
		return startPointFileName.get();
	}


	public void setStartPointFileName(String startPointFileName) {
		this.startPointFileName.set(startPointFileName);
	}


	public String getTargetPointFileName() {
		return targetPointFileName.get();
	}


	public void setTargetPointFileName(String targetPointFileName) {
		this.targetPointFileName.set(targetPointFileName);
	}


	public String getWinFileName() {
		return winFileName.get();
	}


	public void setWinFileName(String winFileName) {
		this.winFileName.set(winFileName);
	}


	public String getCurvedPipeFileName() {
		return curvedPipeFileName.get();
	}


	public void setCurvedPipeFileName(String curvedPipeFileName) {
		this.curvedPipeFileName.set(curvedPipeFileName);
	}


	public String getStraightPipeFileName() {
		return straightPipeFileName.get();
	}


	public void setStraightPipeFileName(String straightPipeFileName) {
		this.straightPipeFileName.set(straightPipeFileName);
	}
	
	private void insertImages () {
		Image background=null;
		Image startPoint=null;
		Image targetPoint=null;
		Image win=null;
		Image curvedPipe=null;
		Image straightPipe=null;
		
		try {
			background = new Image(new FileInputStream(backgroundFileName.get()));
			startPoint = new Image(new FileInputStream(startPointFileName.get()));
			targetPoint = new Image(new FileInputStream(targetPointFileName.get()));
			win = new Image(new FileInputStream(winFileName.get()));
			curvedPipe = new Image(new FileInputStream(curvedPipeFileName.get()));
			straightPipe = new Image(new FileInputStream(straightPipeFileName.get()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
