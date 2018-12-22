package View.Themes;

import javafx.scene.image.Image;
import javafx.scene.media.Media;


public interface Theme {

	public Image getBackgroundImage();
	
	public Media getBackgroundMusic();
	
	public Image getStartImage();
	
	public Image getGoalImage();

	public Image getWinImage();

	public Image getCurvedPipe();

	public Image getStraightPipe();

}
