package sensorplacing;

import java.io.IOException;
import java.util.ArrayList;

import Drawer.FinishedRoomDrawer;
import Drawer.RoomDrawer;
import roomlogik.SensorImageViewList;
import sensoren.SensorList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import roomlogik.Room;
import roomlogik.Sensor;

public class PlaceSensorController {
	@FXML private AnchorPane Backround2;
	@FXML private MenuButton sensors;
	

	private Room room ;
	private RoomDrawer roomDrawer = new FinishedRoomDrawer();
	private SensorList sensorlist = new SensorList();
	private SensorImageViewList sensorImageViewList = new SensorImageViewList();
	private String sensorID ;
	private ArrayList <String> imageviewsToHide = new ArrayList <String>();
   
	
	public void initialize() {
		
		if(Backround2 == null) {
			System.out.println("Error kein Backround vorhanden");
		}
		else if(room == null)
		{
			System.out.println("Error kein Room vorhanden");
		}
		
		Sensor sensor1 = new Sensor("1");
		Sensor sensor2 = new Sensor("2");
		Sensor sensor3 = new Sensor("3");
		
	
		//sensorlist.addSensor(sensor1);
		//sensorlist.addSensor(sensor2);
		//sensorlist.addSensor(sensor3);
		
		//sensorImageViewList.addSensorImageView(sensor1);
		//sensorImageViewList.addSensorImageView(sensor2);
		//sensorImageViewList.addSensorImageView(sensor3);
		
		sensorImageViewList.getImageViewByIndex(0).setVisible(false);
		sensorImageViewList.getImageViewByIndex(1).setVisible(false);
		sensorImageViewList.getImageViewByIndex(2).setVisible(false);
		
		Backround2.getChildren().add(sensorImageViewList.getImageViewByIndex(0));
		Backround2.getChildren().add(sensorImageViewList.getImageViewByIndex(1));
		Backround2.getChildren().add(sensorImageViewList.getImageViewByIndex(2));
	}
	
	
	public void transferRoom(Room room) {
		this.room = room;
		roomDrawer.drawRoom(this.room, Backround2, sensorImageViewList);
	}
	
	public void setSensor1() {
		
		Backround2.setOnMouseMoved(null);
		Backround2.setOnMouseClicked(null);
		Backround2.setOnMouseClicked(SensorOne);
		sensorID = "1";
	}
	
	public void setSensor2() {
		Backround2.setOnMouseMoved(null);
		Backround2.setOnMouseClicked(null);
		Backround2.setOnMouseClicked(SensorOne);
		sensorID = "2";
	}
	
	public void setSensor3() {
		Backround2.setOnMouseMoved(null);
		Backround2.setOnMouseClicked(null);
		Backround2.setOnMouseClicked(SensorOne);
		sensorID = "3";
	}
	
public void removeSensor1() {
		
		Backround2.setOnMouseMoved(null);
		Backround2.setOnMouseClicked(null);
		sensorID = "1";
		sensorImageViewList.getImageViewById(sensorID).setVisible(false);
	}
	
	public void removeSensor2() {
		Backround2.setOnMouseMoved(null);
		Backround2.setOnMouseClicked(null);
		sensorID = "2";
		sensorImageViewList.getImageViewById(sensorID).setVisible(false);
	}
	
	public void removeSensor3() {
		Backround2.setOnMouseMoved(null);
		Backround2.setOnMouseClicked(null);
		sensorID = "3";
		sensorImageViewList.getImageViewById(sensorID).setVisible(false);
	}
	
	
	
	final EventHandler<MouseEvent> SensorOne =
	        new EventHandler<MouseEvent>() 
    {
		@Override
	            public void handle(MouseEvent event) 
	            {	

					double x = event.getX();
					double y = event.getY();
					
					sensorlist.getSensor(sensorID).setSensorPosition(x, y);
					sensorImageViewList.changePositionOfImageView( sensorlist.getSensor(sensorID));
					sensorImageViewList.getImageViewById(sensorID).setVisible(true);
					
					event.consume();
	            }	

	};
	
	
	
}
