import java.lang.*;
//deals with hello notifications
class Hello implements Notification{

	private long timeReceived;
	private long timeSent;
	private Node nodeSentFrom;

	//Notification constructor 
	public Hello(String notificationTimeReceived, String notificationTimeSent, Node notificationSentFrom){
		timeReceived = Long.valueOf(notificationTimeReceived);
		timeSent = Long.valueOf(notificationTimeSent);
		nodeSentFrom = notificationSentFrom;
	}
	//this method deals with the UKNOWN problem based on the assumptions I have stated in the readme and updates any nodes accorindingly
	//It also returns a boolean value so that the doUpdate() method knows whether to execute the code in its if statements.
	public boolean checkForUnknown(Node currentNode){

		if(Math.abs(timeSent - currentNode.getTimeLastSent()) < 50){
			currentNode.setStatus("UNKNOWN");
			currentNode.setLastNotificationUnknown(nodeSentFrom.getName() + " HELLO");
			currentNode.setTimeLastReceivedUnknown(timeReceived);
			currentNode.setTimeLastSent(timeSent);
			return true;
		}
		else{
			return false;
		}
	}
	//if node status is not ambigous based on the timings, the nodes variables are set as necessary
	public void doUpdate(){
		if(!checkForUnknown(nodeSentFrom)){

			nodeSentFrom.setStatus("ALIVE");
			nodeSentFrom.setTimeLastReceived(timeReceived);
			nodeSentFrom.setTimeLastSent(timeSent);
			nodeSentFrom.setLastNotification(nodeSentFrom.getName() + " HELLO");
		}
	}

}