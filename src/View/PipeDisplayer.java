package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import PG.PGSearchable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PipeDisplayer extends Canvas{
	
	char[][] pipeData;
	
	private double w,h;
	
	//pics fileName
	private StringProperty backgroundFileName;
	private StringProperty startPointFileName;
	private StringProperty targetPointFileName;
	private StringProperty winFileName;
	private StringProperty curvedPipeFileName;
	private StringProperty straightPipeFileName;
	
	//set pics
	private Image background;
	private Image startPoint;
	private Image targetPoint;
	private Image win;
	private Image curvedPipe;
	private Image curved90Pipe;
	private Image curved180Pipe;
	private Image curved270Pipe;
	private Image straightPipe;
	private Image straight90Pipe;
	
	private GraphicsContext gc = getGraphicsContext2D();
	
	
	public PipeDisplayer() {
		this.backgroundFileName = new SimpleStringProperty();
		this.startPointFileName = new SimpleStringProperty();
		this.targetPointFileName = new SimpleStringProperty();
		this.winFileName = new SimpleStringProperty();
		this.curvedPipeFileName = new SimpleStringProperty();
		this.straightPipeFileName = new SimpleStringProperty();
	}
	
	public void setPipeData (char[][] pipeData) {
		this.pipeData=pipeData;
		insertImages();
		redraw();
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
		
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		
		//Handle rotate straightPipe
		ImageView imageViewStraightPipe = new ImageView(straightPipe);
		imageViewStraightPipe.setRotate(90);
		straight90Pipe = imageViewStraightPipe.snapshot(params, null);
		
		//Handle rotate curvedPipe
		ImageView imageViewCurvedPipe = new ImageView(curvedPipe);
		imageViewCurvedPipe.setRotate(90);
		curved90Pipe=imageViewCurvedPipe.snapshot(params, null);
		imageViewCurvedPipe.setRotate(180);
		curved180Pipe=imageViewCurvedPipe.snapshot(params, null);
		imageViewCurvedPipe.setRotate(270);
		curved270Pipe=imageViewCurvedPipe.snapshot(params, null);
		
	}
	
	//Just for test - need to remove
	public void goalButton() {
		gc = getGraphicsContext2D();
		gc.clearRect(0, 0, getWidth(), getHeight());
		gc.drawImage(win, 0, 0, getWidth(), getHeight());
	}
	
	public void redraw() {
		if (pipeData!=null) {
			
			double W=getWidth();
			double H=getHeight();
			w= W / pipeData[0].length;
			h = H /pipeData.length;
			
			gc = getGraphicsContext2D();
			gc.clearRect(0, 0, W, H);
			
			//Set background
			gc.drawImage(background, 0,0,W,H);
		
			//Draw specific cell
			for (int i=0;i<pipeData.length;i++) {
				for(int j=0;j<pipeData[i].length;j++) {
					if (pipeData[i][j]!=0) {
						if (pipeData[i][j]=='s')
							gc.drawImage(startPoint, j*w, i*h, w, h);
						if (pipeData[i][j]=='g')
							gc.drawImage(targetPoint, j*w, i*h, w, h);
						if (pipeData[i][j]=='L')
							gc.drawImage(curvedPipe, j*w, i*h, w, h);
						if (pipeData[i][j]=='F')
							gc.drawImage(curved90Pipe, j*w, i*h, w, h);
						if (pipeData[i][j]=='7')
							gc.drawImage(curved180Pipe, j*w, i*h, w, h);
						if (pipeData[i][j]=='J')
							gc.drawImage(curved270Pipe, j*w, i*h, w, h);
						if (pipeData[i][j]=='|')
							gc.drawImage(straightPipe, j*w, i*h, w, h);
						if (pipeData[i][j]=='-')
							gc.drawImage(straight90Pipe, j*w, i*h, w, h); 		
						}
					}
				}
			}
						
		}
	
    @Override
    public void resize(double width, double height) {
        super.setWidth(width);
        super.setHeight(height);
        redraw();
    }
    
    public void clickedOnPosition(MouseEvent event) {
        int col = (int) (event.getX() / w);
        int row = (int) (event.getY() / h);
        pipeData[row][col] = PGSearchable.getNextClick(pipeData[row][col]);
        //TODO: clear to prev image after click
		//gc.clearRect(pipeData[row][col], pipeData[row][col], w, h);
        redraw();
    }
	@Override
	public boolean isResizable() {
		return true;
	}
    
    @Override
	public double minHeight(double width) {
		return 64;
	}

	@Override
	public double maxHeight(double width) {
		return 1000;
	}

	@Override
	public double prefHeight(double width) {
		return minHeight(width);
	}

	@Override
	public double minWidth(double height) {
		return 0;
	}

	@Override
	public double maxWidth(double height) {
		return 10000;
	}

	}

