package Drawer;

import javafx.scene.layout.AnchorPane;
import roomlogik.Room;
import roomlogik.SensorImageViewList;

public interface RoomDrawer {

	public void drawRoom(Room room, AnchorPane pane, SensorImageViewList sensor);
}
