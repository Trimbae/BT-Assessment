import java.io.*;
import java.util.*;
import java.lang.*;

public class ReadNodes{
        //instantiates a list of nodes that can be added to and iterated through when necessary
	public static List<Node> nodes = new ArrayList<Node>();
        //easy way to keep track of the nodes already created
        public static List<String> nodeNames = new ArrayList<String>();
        //instantiates list to store lines of document being read in to the program
        public static List<String> lines = new ArrayList<String>();


	//method to return a node based on name 
        public static Node findNode(String aNodeName){
		for(Node currentNode : nodes){
			if(currentNode.getName().equals(aNodeName)){
				return currentNode;
			}
		}
                System.out.println("Node not found");
		return null;
	}

        public static void newNode(String nodeName){

               nodes.add(new Node(nodeName));
               nodeNames.add(nodeName); 
        }
        //This method processes each notification
        public static void processNotification(String[] notificationArray){
                //checks if node that sent the notification has already been created as an instance of a node object
                if(!nodeNames.contains(notificationArray[2])){
                        newNode(notificationArray[2]);
                }
                //if length of array containing notification is 5, two nodes are involved (in this context), so then checks if second node has already
                //been instantiated
                if(notificationArray.length == 5)
                        if(!nodeNames.contains(notificationArray[4])){
                                newNode(notificationArray[4]);
                        }
                try{
                        //creates Notification objects for appropriate notification type, then perfoms doUpdate method        
                        if(notificationArray[3].equals("HELLO")){
                                Notification n = new Hello(notificationArray[0], notificationArray[1], findNode(notificationArray[2]));
                                n.doUpdate();
                        }
                        
                        else if(notificationArray[3].equals("FOUND")){
                                Notification n = new Found(notificationArray[0], notificationArray[1], findNode(notificationArray[2]), findNode(notificationArray[4]));
                                n.doUpdate();
                        }

                        else if(notificationArray[3].equals("LOST")){
                                Notification n = new Lost(notificationArray[0], notificationArray[1], findNode(notificationArray[2]), findNode(notificationArray[4]));
                                n.doUpdate();
                        }
                        //error occurs if the notification is not of a valid type.
                        else{
                                System.out.println("Invalid notification... " + notificationArray[3] + " exiting program");
                                System.exit(0);
                        }
                }
                catch(NumberFormatException e){
                        System.out.println("Error, Invalid format of notification");
                        System.exit(0);
                }
        }
        //simple method to read a text file and insert each line as a seperate string into an array
        public static void readFile(String fileName){

                String line = null;

                try {

                        FileReader fileReader = new FileReader(fileName);
                        
                        BufferedReader bufferedReader = new BufferedReader(fileReader);

                        while((line = bufferedReader.readLine()) != null) {
                                lines.add(line);
                        }   

                        bufferedReader.close();         
                }
                catch(FileNotFoundException exception) {
                        System.out.println("Unable to open file '" + fileName + "'");                
                }
                catch(IOException exception) {
                        System.out.println("Error reading file '" + fileName + "'");
                }
        }

	public static void main(String[] args){

                try{
                        String fileName = args[0];
                        //reads lines of file into string array
                        readFile(fileName);                 
                }
                //returns error if no arguments are specified
                catch(ArrayIndexOutOfBoundsException e){
                        System.out.println("Error, no arguments specified");
                }

                
                //Sorts lines by time of notification being sent by node, when notifications are processed in this order
                // the last notification processed for any given node will be the last one sent by that node.
                Collections.sort(lines, new StringNumberComparator());


                //processes each notification in order
                for(String tempLine : lines){
                        String[] lineAsArray = tempLine.split(" ");

                        if(lineAsArray.length == 4 || lineAsArray.length == 5){
                           processNotification(lineAsArray);     
                        }
                        else{
                                System.out.println("Error: notifications in invalid format");
                                System.exit(0);
                        }

                        
                }
                //prints all nodes and their current status, plus last notification that updated node concerned
                for(Node currentNode : nodes){
                	currentNode.printNode();
                }
        }
}