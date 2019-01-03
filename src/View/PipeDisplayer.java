 package View;

import View.Themes.PottsChip;
import View.Themes.Theme;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.paint.Color;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PipeDisplayer extends Canvas{
	
	char[][] pipeData;
	Theme theme;
	
	private MediaPlayer mediaPlayer;
	public double w,h;
	
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
		theme = new PottsChip();
	}
	
	public double getW() {
		return w;
	}
	
	public double getH() {
		return h;
	}
	public void setPipeTheme(Theme theme) {
		this.theme=theme;
		setPipeData(this.pipeData);
	}
	
	public void setPipeData (char[][] pipeData) {
		this.pipeData=pipeData;
		insertImages();
		startMusic();
		redraw();
	}
	
	public void startMusic() {
			if (this.mediaPlayer!=null)
				this.mediaPlayer.stop();
			Media media = theme.getBackgroundMusic();
			this.mediaPlayer = new MediaPlayer(media);
			this.mediaPlayer.play();	
	}
	
	
	public void insertImages () {
		
			background = theme.getBackgroundImage();//new Image(new FileInputStream(backgroundFileName.get()));
			startPoint = theme.getStartImage();//new Image(new FileInputStream(startPointFileName.get()));
			targetPoint = theme.getGoalImage(); //new Image(new FileInputStream(targetPointFileName.get()));
			win =theme.getWinImage(); // new Image(new FileInputStream(winFileName.get()));
			curvedPipe = theme.getCurvedPipe(); //new Image(new FileInputStream(curvedPipeFileName.get()));
			straightPipe = theme.getStraightPipe(); //new Image(new FileInputStream(straightPipeFileName.get()));

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
	
	public void isGoal() {
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
		
			//Draw specific cell:
			for (int row=0;row<pipeData.length;row++) {
				for(int col=0;col<pipeData[row].length;col++) {
					if (pipeData[row][col]!=0) {
						if (pipeData[row][col]=='s')
							gc.drawImage(startPoint, col*w, row*h, w, h);
						if (pipeData[row][col]=='g')
							gc.drawImage(targetPoint, col*w, row*h, w, h);
						if (pipeData[row][col]=='L')
							gc.drawImage(curved180Pipe, col*w, row*h, w, h);
						if (pipeData[row][col]=='F')
							gc.drawImage(curved270Pipe, col*w, row*h, w, h);
						if (pipeData[row][col]=='7')
							gc.drawImage(curvedPipe, col*w, row*h, w, h);
						if (pipeData[row][col]=='J')
							gc.drawImage(curved90Pipe, col*w, row*h, w, h);
						if (pipeData[row][col]=='|')
							gc.drawImage(straight90Pipe, col*w, row*h, w, h);
						if (pipeData[row][col]=='-')
							gc.drawImage(straightPipe, col*w, row*h, w, h); 		
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

	public String winMsg() {
		return ("Good job! You won");
	}
	public String howToPlayMsg() {
		return ("Pipeline is a surprisingly fun 3D pipe laying game. "
			+	System.lineSeparator()+ "Presented as a kid's game, gamers will enjoy it as a light contest too. "
			+   System.lineSeparator()+ "4 players have colourful plastic sets of plumbing; straights, angles, T joints and capping pieces. "
			+   System.lineSeparator()+ "Start from one side of the board, "
			+   System.lineSeparator()+ "spin a spinner (or roll a special die) to select your piece and try to build a pipeline over to ending points on the opposite side. "
			+   System.lineSeparator()+ "You can rotate your pipeline if required, but not backtrack, so when your neighbour's pipe gets in your way, "
			+   System.lineSeparator()+ "you have to build over or around. First to connect wins.");
		
	}
	}

