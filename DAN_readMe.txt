This runs on Eclipse. (Preferably Version Mars)

+Extra JARS Needed:
-gson 2.4
-json simple 1.1.1
-JFreeChart 1.0.19 
-JCommon 1.0.23

+If the libs folder does not exist:
-Create a libs folder if needed and search for the JARS online
-Download the JARS and place them in the libs folder

+If the JARS are missing:
-Search for the JARS online
-Download the JARS and place them in the libs folder

+To add JARS to Eclipse
-import the project into Eclipse
-right-click the folder
-select "Properties" -> "Java Build Path" -> "Libraries" (tab)
-select "Add External JARS..." -> select the JARS from the libs folder and add them

+To run the Program:
-navigate to src -> DAN.DAN_testMain
-run file DAN_TestMain.java
-A json file of data will be created (Named: 10.json)

When the program starts, a GUI will be displayed.
The program reads commands from a "commands.txt" file.
By pressing the "DAN ON" button once, the DAN will perform one complete run 
through all the commands given in the "commands.txt" file.
When checking the "Automatic" checkbox and pressing the "DAN ON" button, the
program will continuously loop by constantly calling the "commands.txt" file.
The logs on the GUI can be saved as well as cleared. (Logs are saved in the logs folder.)
To stop the program press the "Exit" button.

On the bottom of the GUI, a graph of "Time versus Number of neutrons" is displayed.
As the program runs, the number of neutrons is calculated at a certain time and plotted on the graph.