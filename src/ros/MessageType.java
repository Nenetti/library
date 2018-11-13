package ros;







public class MessageType {
	
	public enum Type {
		String("std_msgs/String"),
		Int32("std_msgs/Int32"),
		Marker("visualization_msgs/Marker"),
		MarkerArray("visualization_msgs/MarkerArray"),
		LaserScan("sensor_msgs/LaserScan"),
		TF2("tf2_msgs/TFMessage"),
		Odometry("nav_msgs/Odometry"),
		Joy("sensor_msgs/Joy"),
		MoveBaseGoal("move_base_msgs/MoveBaseGoal"),
		MoveBaseActionGoal("move_base_msgs/MoveBaseActionGoal"),
		Twist("geometry_msgs/Twist");
		
		
		private String name;
		private Type(String name) {
			this.name=name;
		}
		public static Type getType(String str) {
			for(Type t:Type.values()) {
				if(t.name.equals(str)) {
					return t;
				}
			}
			return null;
		}
	}
	
}