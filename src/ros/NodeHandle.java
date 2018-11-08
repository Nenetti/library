package ros;

import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.Node;
import org.ros.node.NodeMain;

public abstract class NodeHandle extends AbstractNodeMain{

	private static ConnectedNode connectedNode;

	public void onStart(ConnectedNode connectedNode) {
		NodeHandle.connectedNode=connectedNode;
	}
	
	public static ConnectedNode connectedNode() {
		return connectedNode;
	}

	public void start() {}

	public void onShutdown(Node node) {}

	public void onShutdownComplete(Node node) {}

	public void onError(Node node, Throwable throwable) {}
	
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

}