package roomlogik;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.geometry.Point2D;

public class Wall implements Serializable {


	
	private Point StartPoint;
	private Point EndPoint;
	private ArrayList <Point> pointsBetweenStartAndEnd;
	

	Wall(){
		pointsBetweenStartAndEnd = new ArrayList <Point>();
	}

	public Wall(Point startPoint, Point endPoint) {
		StartPoint = startPoint;
		EndPoint = endPoint;
		//StartPointIsSet = true;
		//EndPointIsSet = true;
	}

	public double getSmallestDistance(double x, double y) {
		double smallestDistance=10000;
		
			if(StartPoint.distance(x,y) < smallestDistance) {
				smallestDistance = StartPoint.distance(x, y);
			}
			if(EndPoint.distance(x, y) < smallestDistance) {
				smallestDistance = EndPoint.distance(x, y);
			}
		
			for(int i = 0; i < pointsBetweenStartAndEnd.size(); i++) {
				if(pointsBetweenStartAndEnd.get(i).distance(x,y) < smallestDistance) {
					smallestDistance = pointsBetweenStartAndEnd.get(i).distance(x,y);
				}
			}
		
		return smallestDistance;
		
	}
	
	public Point getClosestPointInWall(double x, double y) 
	{	
		Point ClosestPoint;
		if(this.StartPoint.distance(x,y) < this.EndPoint.distance(x, y)) 
		{
			ClosestPoint = this.StartPoint;
		}else
		{
			ClosestPoint = this.EndPoint;	
		}
		
		for(int i = 0 ; i< pointsBetweenStartAndEnd.size() ; i++ ) {
			if(pointsBetweenStartAndEnd.get(i).distance(x, y) < ClosestPoint.distance(x, y)) {
				ClosestPoint = pointsBetweenStartAndEnd.get(i);
			}
		}
		
		return ClosestPoint;
	}

	public double getStartPointX() {
		return StartPoint.getX();
	}
	
	public double getStartPointY() {
		return StartPoint.getY();
	}
	
	public double getEndPointX() {
		return EndPoint.getX();
	}
	
	public double getEndPointY() {
		return EndPoint.getY();
	}
	
	public void printProperties() {
		System.out.println("StartPoint:" + "X: " + this.getStartPointX() + "Y: " + this.getStartPointY());
		System.out.println("EndPoint:" + "X: " + this.getEndPointX() + "Y: " + this.getEndPointY());
	}

	
	public Point getStartPoint() {
		// TODO Auto-generated method stub
		return StartPoint;
	}
	
	public Point getEndPoint() {
		return EndPoint;
	}

	
	public void setPoints(ArrayList<Point> calculacteListWithPoints) 
	{
		pointsBetweenStartAndEnd = calculacteListWithPoints;
	}

	public ArrayList<Point> getPointsBetweenStartAndEnd() {
		
		return pointsBetweenStartAndEnd;
	}
	
	

}
