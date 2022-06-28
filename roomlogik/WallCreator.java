package roomlogik;

import java.io.IOException;
import java.util.ArrayList;

public class WallCreator {
	private Point StartPoint;
	private Point EndPoint;
	
	
	public WallCreator(){
		StartPoint = null;
		EndPoint = null;
	}
	
	public Wall createWallAndResetWallCreator() 
	{
		Wall wall = new Wall(StartPoint, EndPoint);
		wall.setPoints(calculacteListWithPoints());
		StartPoint = null;
		EndPoint = null;
		return wall;
	}
	
	public void addValueToWall(double x, double y, Room room) {
		if(!StartPointIsSet())
		{
			setBeginningOfWall(x,y,room);
		}else if(EndPointCanBeSet())
		{
			setEndOfWall(x,y,room);
		}
		
	}
	
	public boolean wallIsComplete() {
		if (StartPointIsSet() && EndPointIsSet())
		{
			return true;
		}else 
		{
			return false;
		} 
	}

	public int numberOfSetPointsInWallCreator() {
		if(!StartPointIsSet() && !EndPointIsSet()) 
		{
			return 0; // No Point is set yet
		}
		else if(StartPointIsSet() && !EndPointIsSet())
		{
			return 1; // 1 point is. EndPoint can never be set before StartPoint
		}else {
			return 2; // both points are set
		}
	}
	
	

	private void setBeginningOfWall(double x, double y, Room room) 
	{
		if(room.numberOfWalls() == 0)
		{
			StartPoint = new Point(x,y);
		}else
		{	
			
			Wall tempWall = room.getClosestWall(x, y);
			if(tempWall.getClosestPointInWall(x, y).isInAreaOf(x, y)) {
			StartPoint = new Point(tempWall.getClosestPointInWall(x, y)); 
			
			}
		}
	}
	
	private void setEndOfWall(double x, double y, Room room) 
	{
		if(EndPointCanBeSet())
		{
			setEndPoint(x,y);
		}
			
	}
	
	private boolean StartAndEndPointIsSet() {
		if (StartPointIsSet() && EndPointIsSet())
		{
			return true;
		}else 
		{
			return false;
		} 
	}

/*StartPoint Methods */
	private boolean StartPointIsSet() {

		if(StartPoint == null)
		{
			return false;
		}else
		{
			return true;
		}
	}
	
	public Point getStartPoint()
	{
		if(StartPointIsSet())
		{
			return StartPoint;
		}else
		{
		 throw new NullPointerException(
		               " :: 'StartPoint' was null inside method 'getStartPoint' / in WallCreator.");
		}
	}
/*StartPoint Methods */	
	
//EndPoint Methods 
	
	private void setEndPoint(double x, double y) {
		if(yIsBetweenBorders(y))  // if y is between Upper and Lower Limit of interval 
		{	
			setEndPointCopyStartPointY(x);
			
		}
		else // if y is out of intervall
		{	
			setEndPointCopyStartPointX(y);			
		}		
	}

	private boolean EndPointCanBeSet() {
		if(StartPointIsSet() && !EndPointIsSet()) {
			return true;
		}else 
		{
		return false;
		}
	}
	
	private void setEndPointCopyStartPointX(double y) {
		EndPoint = new Point(this.StartPoint.getX(), y);
		
	}

	private void setEndPointCopyStartPointY(double x) {
		EndPoint = new Point(x, this.StartPoint.getY());
	}

	private boolean yIsBetweenBorders(double y) {
		if(y < this.getUpperLimitOfInterval() && y > this.getLowerLimitOfInterval()) {
			return true;
		}else
		{
			return false;
		}
		
	}

	private boolean EndPointIsSet() {
		if(EndPoint == null)
		{
			return false;
		}else
		{
			return true;
		}
	}

	private double getLowerLimitOfInterval() {
		if(StartPointIsSet())
		{
		return this.StartPoint.getY() - 10;
		}else
		{
			System.out.println("WallCreator:getLowerLimit(): nullpointerException");
			return 0;
		}
		
		
	}
	
	private double getUpperLimitOfInterval() {
		if(StartPointIsSet())
		{
		return this.StartPoint.getY() + 10;
		}else
		{
			System.out.println("WallCreator:getUpperLimit(): nullpointerException");
			return 0;
		}
	}

//EndPoint Methods	
	
//fillWall Methods
	
	private double getSmallerX(Point StartPoint, Point EndPoint) {
		if(StartPoint.getX() < EndPoint.getX())
		{
			return StartPoint.getX();
		}else
		{
			return EndPoint.getX();
		}
	}
	
	private double getBiggerX(Point StartPoint, Point EndPoint) {
		if(StartPoint.getX() < EndPoint.getX())
		{
			return EndPoint.getX();
		}else
		{
			return StartPoint.getX();
		}
	}
	
	private double getSmallerY(Point StartPoint, Point EndPoint) {
		if(StartPoint.getY() < EndPoint.getY()) {
			return StartPoint.getY();
		}else
		{
			return EndPoint.getY();
		}
	}
	
	private double getBiggerY(Point StartPoint, Point EndPoint) {
		if(StartPoint.getY() < EndPoint.getY()) {
			return EndPoint.getY();
		}else {
			return StartPoint.getY();
		}
	}
	
	private boolean pointIsLocatedInHorizontalWall(Point testedPoint, Point endPoint) {
		 
		double smallerXValue = getSmallerX( StartPoint,  EndPoint);
		double biggerXValue = getBiggerX( StartPoint,  EndPoint);
		if(smallerXValue <= testedPoint.getX() && testedPoint.getX() <= biggerXValue)
		{
			return true;
		}else
		{
		return false;
		}
	}
	
	
	
	private boolean pointIsLocatedInVerticalWall(Point testedPoint, Point endPoint) {
		
		double smallerYValue = getSmallerY( StartPoint,  EndPoint);
		double biggerYValue = getBiggerY( StartPoint,  EndPoint);
		if(smallerYValue <= testedPoint.getY() && testedPoint.getY() <= biggerYValue)
		{
			return true;
		}else
		{
		return false;
		}
		
	}
	
	private ArrayList <Point> fillHorizontalWall()
	{
		boolean addingPoints = true;
		int distanceBetweenPoints = setDistanceBetweenPointsHorizontal();
		
		
		if(StartAndEndPointIsSet()) 
		{
			ArrayList <Point> points = new ArrayList <Point>();
			
			Point firstPoint = new Point(StartPoint.getX()+distanceBetweenPoints,StartPoint.getY());
			points.add(firstPoint);
			
			do {
					Point point = new Point(points.get(points.size()-1).getX()+distanceBetweenPoints,StartPoint.getY());
					if(pointIsLocatedInHorizontalWall(point, this.EndPoint)) 
					{
						points.add(point);
					}else
					{
						addingPoints = false;
					}
			}while(addingPoints);
			
			return points;
			
		}else
		{
			return null;
		}
		
	}
	
	private int setDistanceBetweenPointsHorizontal() {
		int distanceBetweenPoints=0;
		if(StartPoint.getX() < EndPoint.getX())
		{
			distanceBetweenPoints = 10;
		}else if(StartPoint.getX() > EndPoint.getX()) {
			distanceBetweenPoints = -10;
		}
		return distanceBetweenPoints;
	}

	private ArrayList <Point> fillVerticalWall()
	{
		boolean addingPoints = true;
		int distanceBetweenPoints = setDistanceBetweenPointsVertical();
		
		if(StartAndEndPointIsSet()) 
		{
			ArrayList <Point> points = new ArrayList <Point>();
			
			Point firstPoint = new Point(StartPoint.getX(),StartPoint.getY()+distanceBetweenPoints);
			points.add(firstPoint);
			
			do {
					Point point = new Point(StartPoint.getX(),points.get(points.size()-1).getY()+distanceBetweenPoints);
					if(pointIsLocatedInVerticalWall(point, this.EndPoint)) 
					{
						points.add(point);
					}else
					{
						addingPoints = false;
					}
			}while(addingPoints);
			
			return points;
			
		}else
		{
			return null;
		}
		
		
	}

	private int setDistanceBetweenPointsVertical() {
		int distanceBetweenPoints=0;
		if(StartPoint.getY() < EndPoint.getY())
		{
			distanceBetweenPoints = 10;
		}else if(StartPoint.getY() > EndPoint.getY()) {
			distanceBetweenPoints = -10;
		}
		return distanceBetweenPoints;
	}

	private ArrayList<Point> calculacteListWithPoints()
	{
		if(StartPoint.getY() == EndPoint.getY()) // wand ist horizontal
		{
			return fillHorizontalWall();
		}
		else if(StartPoint.getX()== EndPoint.getX()) {
			return fillVerticalWall();
		}else 
		{
			return null;
		}
	}

	
	
	
	
	
}
