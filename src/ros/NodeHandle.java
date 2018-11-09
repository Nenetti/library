package ros;

import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;

public abstract class NodeHandle extends AbstractNodeMain{

	private static ConnectedNode connectedNode;
	
	@Override
	public void onStart(ConnectedNode connectedNode) {
		NodeHandle.connectedNode=connectedNode;
		start();
	}
	
	public static ConnectedNode connectedNode() {
		return connectedNode;
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

}