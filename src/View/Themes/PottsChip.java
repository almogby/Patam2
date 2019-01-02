package View.Themes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.sound.sampled.AudioInputStream;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class PottsChip implements Theme {

	private Image backgroundImage; 
	private Media backgroundMusic;
	private Image startImage;
	private Image goalImage;
	private Image winImage;
	private Image curvedPipe;
	private Image straightPipe;
	
	
	public PottsChip() {
		try {
			this.backgroundImage = new Image(new FileInputStream("./resources/Theme1_PottsChip/Background_1.jpg"));
			this.backgroundMusic = new Media (new File("./resources/Theme1_PottsChip/BeOurGuest_1.mp3").toURI().toString());
			this.startImage = new Image(new FileInputStream("./resources/Theme1_PottsChip/Start_Mrs_Potts_1.jpg"));
			this.goalImage = new Image(new FileInputStream("./resources/Theme1_PottsChip/Goal_Chip_1.png"));
			this.winImage = new Image(new FileInputStream("./resources/Theme1_PottsChip/Potts_Chip_Win_1.png"));
			this.curvedPipe = new Image(new FileInputStream("./resources/Theme1_PottsChip/CurvedPipe_1_1.png"));
			this.straightPipe = new Image(new FileInputStream("./resources/Theme1_PottsChip/StraightPipe_1_1.png"));
		} catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Image getBackgroundImage() {
		return this.backgroundImage;
	}

	@Override
	public Media getBackgroundMusic() {
		return this.backgroundMusic;
	}

	@Override
	public Image getStartImage() {
		return this.startImage;
	}

	@Override
	public Image getGoalImage() {
		return this.goalImage;
	}

	@Override
	public Image getWinImage() {
		return this.winImage;
	}

	@Override
	public Image getCurvedPipe() {
		return this.curvedPipe;
	}

	@Override
	public Image getStraightPipe() {
		return this.straightPipe;
	}

}
