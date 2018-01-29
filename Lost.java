import java.lang.*;

class Lost implements Notification{

	private long timeReceived;
	private long timeSent;
	private Node nodeSentFrom;
	private Node subjectNode;
	//Constructor
	public Lost(String notificationTimeReceived, String notificationTimeSent, Node notificationSentFrom, Node notificationSubjectNode){
		timeReceived = Long.valueOf(notificationTimeReceived);
		timeSent = Long.valueOf(notificationTimeSent);
		nodeSentFrom = notificationSentFrom;
		subjectNode = notificationSubjectNode;
	}
	//this method deals with the UKNOWN problem based on the assumptions I have stated in the readme and updates any nodes accorindingly
	//It also returns a boolean value so that the doUpdate() method knows whether to execute the code in its if statements.
	public boolean checkForUnknown(Node currentNode){

		if(Math.abs(timeSent - currentNode.getTimeLastSent()) < 50){
			currentNode.setStatus("UNKNOWN");
			currentNode.setLastNotificationUnknown(nodeSentFrom.getName() + " LOST " + subjectNode.getName());
			currentNode.setTimeLastReceivedUnknown(timeReceived);
			currentNode.setTimeLastSent(timeSent);
			return true;
		}
		return false;
	}
	//if node status is not ambigous based on the timings, the nodes variables are set as necessary
	public void doUpdate(){
		if(!checkForUnknown(nodeSentFrom)){
			nodeSentFrom.setStatus("ALIVE");
			nodeSentFrom.setTimeLastReceived(timeReceived);
			nodeSentFrom.setTimeLastSent(timeSent);
			nodeSentFrom.setLastNotification(nodeSentFrom.getName() + " LOST " + subjectNode.getName());
		}
		if(!checkForUnknown(subjectNode)){
			subjectNode.setStatus("DEAD");
			subjectNode.setTimeLastReceived(timeReceived);
			subjectNode.setTimeLastSent(timeSent);
			subjectNode.setLastNotification(nodeSentFrom.getName() + " LOST " + subjectNode.getName());
		}
	}

}