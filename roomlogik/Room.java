package roomlogik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class Room implements Serializable {

	private ArrayList<Wall> room = new ArrayList<Wall>();
	String RoomID;
	private int closestWall=0;

	public Room()
	{
		
	}
	
	public Room(String RaumId, Room raum) {
		RoomID = RaumId;
		room = raum.getAllWalls();
	}
	
	public String getRoomID() {
		return RoomID;
	}
	public ArrayList<Wall> getAllWalls(){
		return this.room;
	}
	
	public Wall getWall(int index) 
	{
		return room.get(index);
	}
	
	public void addWall(Wall wall) 
	{
		this.room.add(wall);
	}
	
	public Wall getLastWall() 
	{
		return room.get(room.size()-1);
	}
	
	public int numberOfWalls() 
	{
		return room.size();
	}
	
	//PunkteDieDenGleichenXoderYWertHabenWie(Punkt b) Punkt = (x/y)
	public ArrayList<Point> getPointsWithSameX(double x){
		ArrayList <Point> points = new ArrayList();
		
		for(int i = 0; i<room.size(); i++) 
		{
			if(room.get(i).getStartPointX()-3 < x && x < room.get(i).getStartPointX()+3) 
			{
			  points.add(room.get(i).getStartPoint());
			}
			if(room.get(i).getEndPointX()-3 < x && x < room.get(i).getEndPointX()+3) {
				points.add(room.get(i).getEndPoint());
			}
		}
		
		
		return points;
	}
	
	public ArrayList<Point> getPointsWithSameY(double y){
		ArrayList <Point> points = new ArrayList();
		
		for(int i = 0; i<room.size(); i++) 
		{
			if(room.get(i).getStartPointY()-3 < y && y < room.get(i).getStartPointY()+3) 
			{
			  points.add(room.get(i).getStartPoint());
			}
			if(room.get(i).getEndPointY()-3 < y && y < room.get(i).getEndPointY()+3) {
				points.add(room.get(i).getEndPoint());
			}
		}
		
		
		return points;
	}

	public void printProperties() {
		for(int i = 0; i < room.size(); i++) {
			System.out.println("Wall: " + i + " ");
			room.get(i).printProperties();
		}
	}
	
	public Wall getClosestWall(double x, double y) 
	{
		setClosestWall(x,y);
		return this.room.get(closestWall);
	}

	private void setClosestWall(double x, double y)
	{
		
			
		
			double smallestDistance=100000000;
			for(int i=0; i < room.size(); i++) 
			{
				if(room.get(i).getSmallestDistance(x,y) < smallestDistance)
				{
					smallestDistance = room.get(i).getSmallestDistance(x,y);
					this.closestWall = i;
				}
			
			}
	}
		
	public void deleteWall(double x, double y) {
		room.remove(this.getClosestWall(x, y));
	}
	

	
}
