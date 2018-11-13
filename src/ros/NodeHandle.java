package ros;

import java.util.HashMap;

import org.ros.message.Time;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import std_msgs.Header;

public abstract class NodeHandle extends AbstractNodeMain{

	private static ConnectedNode connectedNode;
	public static HashMap<String, Publisher> publishers=new HashMap<>();
	public static HashMap<String, Subscriber> subscribers=new HashMap<>();
	
	public NodeHandle() {
		UserProperty.read();
	}
	
	public static void init(ConnectedNode connectedNode) {
		NodeHandle.connectedNode=connectedNode;
	}
	
	@Override
	public void onStart(ConnectedNode connectedNode) {
		NodeHandle.connectedNode=connectedNode;
		start();
	}
	
	public static void registerPublisher(Publisher publisher, String topic) {
		publishers.put(topic, publisher);
	}
	
	public static void registerSubscriber(Subscriber subscriber, String topic) {
		subscribers.put(topic, subscriber);
	}
	
	public static Publisher getPublisher(String topic) {
		return publishers.get(topic);
	}
	
	public static Subscriber getSubscriber(String topic) {
		return subscribers.get(topic);
	}
	
	public static ConnectedNode connectedNode() {
		return connectedNode;
	}
	
	public static HashMap<String, Publisher> getPublishers() {
		return publishers;
	}
	public static HashMap<String, Subscriber> getSubscribers() {
		return subscribers;
	}
	
	public static boolean isPublisherRegistered(String topic) {
		return publishers.containsKey(topic);
	}
	public static boolean isSubscriberRegistered(String topic) {
		return subscribers.containsKey(topic);
	}

	public abstract void start();
	
	/******************************************************************************************
	 * 
	 * @param time
	 */
	public static void duration(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Time getTimeStamp(){
		return  connectedNode.getCurrentTime();
	}

}