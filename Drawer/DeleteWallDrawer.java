package Drawer;

import java.util.ArrayList;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import roomlogik.Point;
import roomlogik.Room;
import roomlogik.SensorImageViewList;
import roomlogik.WallCreator;

public class DeleteWallDrawer extends StartingWallDrawer implements RoomDrawer  {

	private WallCreator wallCreator;
	private double xCoordinate;
	private double yCoordinate;
	private int initialSizeOfBackroundChildren;
	
	
	
	@Override
	public void drawRoom(Room room, AnchorPane Backround, SensorImageViewList sensor) {
		
		clearBackround(Backround);
		for(int wallNumb =0;  wallNumb < room.numberOfWalls(); wallNumb++) 
		{ 
			
    		drawWall(Backround,room, wallNumb);
    		drawStartPointOfWall(Backround,room, wallNumb);
    		drawEndPointOfWall(Backround,room, wallNumb);
    		drawWallToBeDeleted(Backround, room);
    		
		}
		
		drawImageViews(Backround,sensor );
	 	
	 	
		
	}
	
	protected void drawImageViews(AnchorPane Backround, SensorImageViewList sensorsImages) {
		for(int i = 0; i < sensorsImages.getSize(); i++) {
			Backround.getChildren().add(sensorsImages.getImageViewByIndex(i));
		}
	}
	
	private void drawWallToBeDeleted(AnchorPane Backround, Room room) {
		Line line = new Line();
		line.setStroke(Color.RED);
		line.setStrokeWidth(2);
		line.setStartX(room.getClosestWall(xCoordinate, yCoordinate).getStartPointX()); 
		line.setStartY(room.getClosestWall(xCoordinate,yCoordinate).getStartPointY()); 
		line.setEndX(room.getClosestWall(xCoordinate,yCoordinate).getEndPointX()); 
	 	line.setEndY(room.getClosestWall(xCoordinate, yCoordinate).getEndPointY());
	 	
	 	Circle endCircle = new Circle();
	 	endCircle.setCenterX(room.getClosestWall(xCoordinate,yCoordinate).getEndPointX());
	 	endCircle.setCenterY(room.getClosestWall(xCoordinate, yCoordinate).getEndPointY());
	 	endCircle.setRadius(5.0f);
	 	
	 	Circle StartCircle = new Circle();
	 	StartCircle.setCenterX(room.getClosestWall(xCoordinate,yCoordinate).getStartPointX());
	 	StartCircle.setCenterY(room.getClosestWall(xCoordinate, yCoordinate).getStartPointY());
	 	StartCircle.setRadius(5.0f);
	 	
		
	 	Backround.getChildren().add(line);
	 	Backround.getChildren().add(StartCircle);
		Backround.getChildren().add(endCircle);
	}
	
	private void drawPointWithSameY(ArrayList<Point> pointsWithSameY, AnchorPane Backround) {
		if(pointsWithSameY.size()==0) 
		{
			
		}
		else
		{
			for(int i = 0; i< pointsWithSameY.size(); i++)
			{
				Circle circle = new Circle();
				circle.setFill(Color.BLUEVIOLET);
				circle.setCenterX(pointsWithSameY.get(i).getX());
	   		 	circle.setCenterY(pointsWithSameY.get(i).getY());
	   		 	circle.setRadius(5.0f);
	   			Backround.getChildren().add(circle);
			}
		}
		
	}

	private void drawPointsWithSameX(ArrayList<Point> pointsWithSameX, AnchorPane Backround) {
		// TODO Auto-generated method stub
		if(pointsWithSameX.size()==0) 
		{
			
		}
		else
		{
			for(int i = 0; i< pointsWithSameX.size(); i++)
			{
				Circle circle = new Circle();
				circle.setFill(Color.BLUEVIOLET);
				circle.setCenterX(pointsWithSameX.get(i).getX());
	   		 	circle.setCenterY(pointsWithSameX.get(i).getY());
	   		 	circle.setRadius(5.0f);
	   			Backround.getChildren().add(circle);
			}
		}
	}

	public void passWallCreator(WallCreator wallCreator) {
		this.wallCreator = wallCreator;
	}
	
	public void passCoordinates(double x, double y)
	{
		xCoordinate = x;
		yCoordinate = y;		
	}

	//Diese Methoden sind modifizierte versionen von WallCreators EndPoint Methoden
	private Point setEndPoint(double x, double y, WallCreator wallCreator) {
		if(yIsBetweenBorders(y, wallCreator))  // if y is between Upper and Lower Limit of interval 
		{	
			return setEndPointCopyStartPointY(x, wallCreator);
			
		}
		else // if y is out of intervall
		{	
			return setEndPointCopyStartPointX(y, wallCreator);			
		}		
	}
	
	private boolean yIsBetweenBorders(double y, WallCreator wallCreator) {
		if(y < getUpperLimitOfInterval(wallCreator.getStartPoint()) && y > getLowerLimitOfInterval(wallCreator.getStartPoint())) {
			return true;
		}else
		{
			return false;
		}
	}
		
	private double getLowerLimitOfInterval(Point startPoint) {
						
			return startPoint.getY() - 10;
			
				
		}
		
	private double getUpperLimitOfInterval(Point startPoint) {
			return startPoint.getY() +10;
	}
		
	private Point setEndPointCopyStartPointX(double y, WallCreator wallCreator) {
			Point EndPoint = new Point(wallCreator.getStartPoint().getX(), y);
			return EndPoint;
			
		}

	private Point setEndPointCopyStartPointY(double x, WallCreator wallCreator) {
			Point EndPoint = new Point(x,wallCreator.getStartPoint().getY());
			return EndPoint;
		}
	//Hier enden die modifizierten methoden, ändert sich etwas bei wallcreator dann auch hier
}

