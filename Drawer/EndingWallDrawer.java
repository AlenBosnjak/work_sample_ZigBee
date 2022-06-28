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

public class EndingWallDrawer extends StartingWallDrawer implements RoomDrawer  {

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
		}
		
		Line line = new Line();
		line.setStrokeWidth(0.5);
		line.setStartX(wallCreator.getStartPoint().getX()); 
		line.setStartY(wallCreator.getStartPoint().getY()); 
		Point EndPoint = setEndPoint(xCoordinate, yCoordinate, wallCreator);
		line.setEndX(EndPoint.getX()); 
	 	line.setEndY(EndPoint.getY());
	 	Backround.getChildren().add(line);
	 	
	 	ArrayList <Point> pointsWithSameX = room.getPointsWithSameX(xCoordinate);
	 	ArrayList <Point> pointsWithSameY = room.getPointsWithSameY(yCoordinate);
	 	
	 	drawPointsWithSameX(pointsWithSameX, Backround);
	 	drawPointWithSameY(pointsWithSameY, Backround);
		drawImageViews(Backround,sensor );
		
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

	protected void drawImageViews(AnchorPane Backround, SensorImageViewList sensorsImages) {
		for(int i = 0; i < sensorsImages.getSize(); i++) {
			Backround.getChildren().add(sensorsImages.getImageViewByIndex(i));
		}
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
	//Hier enden die modifizierten methoden, aendert sich etwas im wallcreator dann auch hier
}

