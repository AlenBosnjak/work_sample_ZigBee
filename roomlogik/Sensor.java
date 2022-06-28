package roomlogik;

import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sensor implements Serializable {

	
	private double sensorPositionX;
	private double sensorPositionY;
	
	private String ID;
	
	public Sensor(String ID){
		this.ID = ID;
		
	}
	
	public String getId() {
		return ID;
	}
	
	
	
	public double getSensorPositionX() {
		return sensorPositionX;
	}
	
	public double getSensorPositionY() {
		return sensorPositionY;
	}
	
	private void setSensorPositionX(double sensorPositionX) {
		this.sensorPositionX = sensorPositionX;
	}
	
	private void setSensorPositionY(double sensorPositionY) {
		this.sensorPositionY = sensorPositionY;
	}
	
	public void setSensorPosition(double x, double y) {
		setSensorPositionX(x);
		setSensorPositionY(y);
		
	}
	
}
