package Drawer;

import java.util.ArrayList;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import roomlogik.Point;
import roomlogik.Room;
import roomlogik.SensorImageViewList;

public class FinishedRoomDrawer implements RoomDrawer {

	private double xCoordinate;
	private double yCoordinate;
	
	@Override
	public void drawRoom(Room room, AnchorPane Backround, SensorImageViewList sensor) {
		// TODO Auto-generated method stub
		Backround.getChildren().clear();
		for(int wallNumb =0;  wallNumb < room.numberOfWalls(); wallNumb++) 
		{ 
    		drawWall(Backround,room, wallNumb);
    		drawStartPointOfWall(Backround,room, wallNumb);
    		drawEndPointOfWall(Backround,room, wallNumb);
    		
		}
		drawImageViews(Backround,sensor );
	}
	
	public void passCoordinates(double x, double y)
	{
		xCoordinate = x;
		yCoordinate = y;		
	}
	
	protected void drawWall(AnchorPane Backround, Room room, int index ) 
	{
		Line line = new Line();
		line.setStrokeWidth(2);
		line.setStartX(room.getWall(index).getStartPointX()); 
		line.setStartY(room.getWall(index).getStartPointY()); 
		line.setEndX(room.getWall(index).getEndPointX()); 
	 	line.setEndY(room.getWall(index).getEndPointY());
	 	Backround.getChildren().add(line);
	}
	
	protected void drawStartPointOfWall(AnchorPane Backround, Room room, int index)
	{
		Circle startCircle = new Circle();
		 if(room.getWall(index).getStartPoint().isInAreaOf(xCoordinate, yCoordinate)) 
   		 {
 			startCircle.setFill(Color.RED);
   		 }
   		 	startCircle.setCenterX(room.getWall(index).getStartPointX());
   		 	startCircle.setCenterY(room.getWall(index).getStartPointY());
   		 	startCircle.setRadius(5.0f);
   			Backround.getChildren().add(startCircle);
	}
	
	protected void drawEndPointOfWall(AnchorPane Backround, Room room, int index)
	{
		Circle endCircle = new Circle();
		 if(room.getWall(index).getEndPoint().isInAreaOf(xCoordinate, yCoordinate)) 
   		 {
  			endCircle.setFill(Color.RED);
   		 }
   		 	endCircle.setCenterX(room.getWall(index).getEndPointX());
		 	endCircle.setCenterY(room.getWall(index).getEndPointY());
		 	endCircle.setRadius(5.0f);
   		 	Backround.getChildren().add(endCircle);
	}
	
	protected void drawPointsBetweenStartAndEnd(AnchorPane Backround, Room room, int index)
	{
		ArrayList <Point> points = room.getWall(index).getPointsBetweenStartAndEnd();
		for(int i = 0 ; i < points.size(); i++)
		{
			Circle endCircle = new Circle();
			endCircle.setFill(Color.RED);
			endCircle.setCenterX(points.get(i).getX());
		 	endCircle.setCenterY(points.get(i).getY());
		 	endCircle.setRadius(2.0f);
   		 	Backround.getChildren().add(endCircle);
		}
	}
	
	protected void drawImageViews(AnchorPane Backround, SensorImageViewList sensorsImages) {
		for(int i = 0; i < sensorsImages.getSize(); i++) {
			Backround.getChildren().add(sensorsImages.getImageViewByIndex(i));
		}
	}
}
