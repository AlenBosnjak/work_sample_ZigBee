# Overview
### What is Zigbee?
Zigbee is an IEEE 802.15.4-based specification for a suite of high-level communication protocols used to create personal area networks with small, low-power digital radios, such as for home automation, medical device data collection, and other low-power low-bandwidth needs, designed for small scale projects which need wireless connection. Hence, Zigbee is a low-power, low data rate, and close proximity (i.e., personal area) wireless ad hoc network. (https://en.wikipedia.org/wiki/Zigbee)

### What was the goal of this project?
In the context of the COVID-19 pandemic it was discussed whether face-to-face events should take place. One model suggested regular ventilation of the rooms to minimize the risk of infection. 
Therefore we developed a solution that measures the air quality and informs the user via a graphical interface when the air quality is too low. The graphical user interface contains a model of the room, which the user can create himself. In addition, he can indicate the position of the seven sensors.

# My Tasks and code structure
My task was to develop the part of the graphical interface where the user could draw and edit the rooms and place the sensors. The input works via mouse clicks on the provided canvas. The classes where structured in two packages:

### roomlogic ###  
The room logic pack contains classes that represent the basic building blocks of rooms. The basic logic is represented by several classes:

* One ***room*** consists of many walls.
* One ***wall*** consists of many points.
* One ***point*** consists of two coordinates.
* One ***sensor*** consists of two coordinates.
* ***WallCreator*** contains the rules that must be followed while creating the walls.
* Additionally the classes contain Methods which would provide information about the positioning of the objects in relation to a given point of the canvas.

### drawer ###  
Classes that draw lines, points etc. onto the screen. They are several Drawer classes that are based on the state of the application. Different state leads to different visual feedback.

* For example, the ***DeleteWallDrawer*** would change the color of the wall closest to the mouse pointer, signaling which wall would be removed on click.
* ***StartingWallDrawer*** indicates which points can be selected to start a new Wall.
* ***EndingWallDrawer*** shows a live preview of what the wall would look like when the user clicks to mark the end of the wall.
* ***FinishedRoomDrawer*** adds the wall permanently to the canvas.
