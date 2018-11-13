package ros;

import java.util.List;

import ros.MessageType.Type;

public class Publisher{
	
	private org.ros.node.topic.Publisher<Object> publisher;
	public Type type;

	public Publisher(String topic, String type) {
		this.type=Type.getType(type);
		publisher=NodeHandle.connectedNode().newPublisher(topic, type);
		NodeHandle.registerPublisher(this, topic);
	}
	
	@SuppressWarnings("unchecked")
	public void publish(Object data) {
		try {
			Object message=publisher.newMessage();
			switch (type) {
			case String:
				((std_msgs.String)message).setData((String)data);
				publisher.publish(message);
				break;
			case Int32:
				((std_msgs.Int32)message).setData((int)data);
				publisher.publish(message);
				break;
			case MarkerArray:
				((visualization_msgs.MarkerArray)message).setMarkers((List<visualization_msgs.Marker>)data);
				publisher.publish(message);
				break;
			//case Marker:
			//case TF2:
			//case Joy:
			//case Odometry:
			//case Twist:
			//case LaserScan:
			default:
				publisher.publish(data);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Object newMessage() {
		return publisher.newMessage();
	}
}