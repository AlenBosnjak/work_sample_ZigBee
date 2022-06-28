package roomlogik;

import java.io.Serializable;

import javafx.geometry.Point2D;

public class Point implements Serializable  {
	
	double x ;
	double y;
	public Point(double arg0, double arg1) {
		x=arg0;
		y=arg1;
		
	}

	public Point(Point copyValuesFromThisPoint) {
		// TODO Auto-generated constructor stub
		x=copyValuesFromThisPoint.getX();
		y=copyValuesFromThisPoint.getY();
	}
	
	public Point() {
		
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double distance(double xB, double yB) {
		double distance; 
		//distance = sqrt((x2-x1)^2 + (y2-y1)^2)
		double a = (xB-this.x);
		double b = (yB-this.y);
		a = Math.pow(a, 2);
		b = Math.pow(b, 2);
		distance = Math.sqrt(a+b);
		return distance;
	}
	
	public boolean isInAreaOf(double x , double y) 
	{
		if(pointIsInAreaOfReference(this.getX(),x) && pointIsInAreaOfReference(this.getY(),y) )
		{
			return true;
		}else 
		{
			return false;
		}
	}
	
	private boolean pointIsInAreaOfReference(double reference, double pointToBeChecked ) 
	{
		if(reference-4 <= pointToBeChecked && reference+4 >= pointToBeChecked)
		{
			return true;
		}else
		{
			return false;
		}
	}

}
