package roomlogik;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datenbank.DBS;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import raumLayout.KoordinatenPunkt;
import roomlogik.Sensor;
import sensorWindow.SensorWindowView;

public class SensorImageViewList implements Serializable {

	//private Image image = new Image("/sensorplacing/sensor-green.png");
	private Image green = 
		    new Image(this.getClass().getResourceAsStream("/sensorplacing/sensor-green.png"));
	
	private Image orange = 
		    new Image(this.getClass().getResourceAsStream("/sensorplacing/sensor-orange.png"));
	
	private Image red = 
		    new Image(this.getClass().getResourceAsStream("/sensorplacing/sensor-red.png"));
	
	private ArrayList <ImageView> ImageViewList = new ArrayList <ImageView>();
	
	private Room room = new Room();
	
	private sensoren.Sensor sensor = new sensoren.Sensor();
	
	
	
	public SensorImageViewList(){
		
	}
	
	public void setSensor(String UniqueID) throws SQLException {
		List<sensoren.Sensor> sensoren = new ArrayList<sensoren.Sensor>();
		sensoren = DBS.getAlleSensoren();
		for(sensoren.Sensor sen : sensoren) {
			if(sen.sameUniqueID(UniqueID)) {
				this.sensor = sen;
			}
		}
	}
	
	public void passRoom(Room room) {
		this.room = room;
	}
	
	public void addSensorImageView(sensoren.Sensor sensor) {
		
				Tooltip tooltip = new Tooltip(sensor.getUniqueID());
				Duration showDelay = new Duration(0);
				tooltip.setShowDelay(showDelay);
				 ImageView iv1 = getImageViewById(sensor.getID());	
				 if(iv1 == null) {
				 iv1 = new ImageView();
				 }
				 iv1.setImage(green);
				 iv1.setFitWidth(25);
				 iv1.setPreserveRatio(true);
				 iv1.setSmooth(true);
				 iv1.setCache(true);
				 iv1.setX(sensor.getSensorPositionX());
				 iv1.setY(sensor.getSensorPositionY());
				 iv1.setId(sensor.getID() +" " + sensor.getTyp().toString()); // ID von sensor ist nicht eindeutig daher schlüssel aus beiden
				 iv1.setOnMouseClicked(printIDofImageView);
				 Tooltip.install(iv1, tooltip);
				 ImageViewList.add(iv1);
			}
		
		
	public boolean contains(sensoren.Sensor sensor) {
		String sensorID = sensor.getID() + " " + sensor.getTyp().toString();
		for(int i = 0; i < ImageViewList.size(); i++) {
			if(ImageViewList.get(i).getId().equals(sensorID)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	
	public ImageView getImageViewByIndex(int i) {
		if( 0 <= i && i < ImageViewList.size() ) {
			return ImageViewList.get(i);
		}else {
			return null;
		}
	}
	
	public ImageView getImageViewById(String Id) {
		for(int i = 0 ; i < ImageViewList.size() ; i++) {
			if(ImageViewList.get(i).getId().equals(Id)) {
				return ImageViewList.get(i);
			}
		}
		return null;
	}
	
	public void changePositionOfImageView(sensoren.Sensor sensor) {
		String sensorID = sensor.getUniqueID();
		for(int i = 0; i < ImageViewList.size() ; i++) {
			if(sensorID.equals(ImageViewList.get(i).getId())) {
				ImageViewList.get(i).setX(sensor.getSensorPositionX());;
				ImageViewList.get(i).setY(sensor.getSensorPositionY());
			}
		}
	}
	
	public int getSize() {
		return ImageViewList.size();
	}
	
	public void removeImageView(String uniqueSensorID) {
		
		for(int i = 0; i < ImageViewList.size(); i++) {
			if(ImageViewList.get(i).getId().equals(uniqueSensorID)) {
				ImageViewList.remove(i);
			}
		}
	}
	
	public void clear() {
		ImageViewList.clear();
	}
	
	private void MouseClicked(String ID) throws SQLException {
		SensorWindowView sensorWindowView = new SensorWindowView();
		setSensor(ID);
		Stage stage = new Stage();
		stage.setScene(new Scene(sensorWindowView.getView()));
		stage.show();
		sensorWindowView.getPresenter().start(sensor, room.getRoomID());
	}
	
	  final EventHandler<MouseEvent> printIDofImageView =
				new EventHandler<MouseEvent>() 
				{

			@Override
			public void handle(MouseEvent event) {
				String Id = ((Node) event.getSource()).getId();
				
	        	try {
					MouseClicked(Id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
			}
							
					
				};
	
	public void changeColour(String colour) {
		if(colour.equals("ROT")) {
			
			changeAllImageViewToRed();
			
		}else if(colour.equals("GELB")) {
			
			changeAllImageViewToOrange();
			
		}else if(colour.equals("GRUEN")) {
			
			changeAllImageViewToGreen();
			
		}else {
			
		}
		
	}
	
	private void changeAllImageViewToRed() {
		for(ImageView view : ImageViewList) {
			view.setImage(red);
		}
	}
	
	private void changeAllImageViewToOrange() {
		for(ImageView view : ImageViewList) {
			view.setImage(orange);
		}
	}
	
	private void changeAllImageViewToGreen() {
		for(ImageView view : ImageViewList) {
			view.setImage(green);
		}
	}
	
}
