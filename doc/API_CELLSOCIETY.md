
# Internal
### Simulation
There are methods like getStateAt(), getNeighborsWithState(), and a lot more other methods that allow the intern classes get information about what the simulation status currently is. This is important because if the front end wants to display graphical information about the simulation, these methods will commonly be used to figure out what is going on. 

### Configuration
Setting default values Eg. if the user doesn’t choose any options sets rows and columns to certain value. There are many methods like getInitialGrid(), getInitialSimulationChoice() that are used internally to create the initial configuration of the simulation. It is important for frontend and backend to have access to this information from the XML reading classes to set up the initial conditions.

### Visualization
There are methods like removeCellFromRoot() and addCellsToRoot(). These two methods will be called inside the class to add or remove all the cells.
 
# External
### Simulation
The user interacts with the simulation by changing the parameters on the available sliders. Methods like propertiesOfSimulationChanged() handle when external input to sliders and buttons change the action of the simulation. Simulation can also be altered by clicking on the individual cells as well.

### Configuration
User can choose amount of rows and columns before simulation begins. User also can choose which simulation will run at that start using a drop-down menu. The user changes what is in the initial configuration in the XML file for the simulation configuration. You can also change the configuration by clicking on the states of the cells.

###	Visualization
User interacts with the visualization by setting the properties of the XML to change how the code runs. getSimulationProperties() sends the information for the visualization from the XML files. This is the main way the user interacts with the visualization aspe
