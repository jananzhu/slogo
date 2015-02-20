package slogo_front;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * class for Turtle object + properties
 * @author emresonmez
 *
 */
public class Turtle {
	protected int myID;
	private int xLoc;
	private int yLoc;
	private ImageView image; 
	
	public int getXloc() {
		return xLoc;
	}

	public int getYloc() {
		return yLoc;
	}
	
	public ImageView getImage(){
		return image;
	}
	
	public void setXLoc(int x){
		xLoc = x;
	}
	public void setYLoc(int y){
		yLoc = y;
	}
	
	/**
	 * basic constructor
	 * @param x
	 * @param y
	 * @param direction
	 * @param penColor
	 * @param imageURL
	 */
	public Turtle(int x, int y, float direction, Color penColor, String imageURL){
		image = new ImageView(new Image(getClass()
				.getResourceAsStream(imageURL)));
	}

}
