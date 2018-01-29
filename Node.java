class Node{
	//variables are private so they can only be accessed using getters and setters
	private String name;
	private String status;
	private long timeLastSent;
	private long timeLastReceived;
	private String lastNotification;
	//These extra variables are only made use of in the event of an UNKNOWN status where more than one time and notification must be stored
	private String lastNotificationUnknown;
	private long timeLastReceivedUnknown;

	//node object can be intanciated with a name only, or with full data about status and update time
	public Node(String nodeName){
		name = nodeName;
	}

	public Node(String nodeName, long nodeTimeSent, long nodeTimeReceived, String nodeStatus){

		name = nodeName;
		timeLastSent = nodeTimeSent;
		timeLastReceived = nodeTimeReceived;
		status = nodeStatus;
	}

	//Series of getters and setters for clients to interact with variables 
	public long getTimeLastSent(){

		return timeLastSent;
	}

	public void setTimeLastSent(long time){

		timeLastSent = time;
	}

	public long getTimeLastReceived(){

		return timeLastReceived;
	}

	public void setTimeLastReceived(long time){

		timeLastReceived = time;
	}

	public String getName(){

		return name;
	}

	public String getStatus(){

		return status;
	}

	public void setStatus(String nodeStatus){

		status = nodeStatus;
	}

	public String getLastNotification(){

		return lastNotification;
	}

	public void setLastNotification(String nodeLastNotification){

		lastNotification = nodeLastNotification;
	}
	public String getLastNotificationUnknown(){

		return lastNotificationUnknown;
	}

	public void setLastNotificationUnknown(String nodeLastNotification){

		lastNotificationUnknown = nodeLastNotification;
	}

	public long getTimeLastReceivedUnknown(){

		return timeLastReceivedUnknown;
	}

	public void setTimeLastReceivedUnknown(long time){

		timeLastReceivedUnknown = time;
	}

//Prints out data about the node in the required format
	public void printNode(){
		//this process is done slightly differently if current status is UNKNOWN
		if(status.equals("UNKNOWN")){
			System.out.println(name + " " + status);
			System.out.println(timeLastReceived + " " + lastNotification);
			System.out.println(timeLastReceivedUnknown + " " + lastNotificationUnknown);
		}
		else{
			System.out.println(name + " "+ status + " " + timeLastReceived + " " + lastNotification);
		}
	}

}