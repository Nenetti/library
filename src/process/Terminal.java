package process;


public class Terminal {
	
	private String[] commands;
	private boolean isWait;
	private boolean isInheritIO;
	
	public static Terminal commands(String... commands) {
		Terminal terminal=new Terminal();
		terminal.commands=commands;
		return terminal;
	}
	
	public Terminal waitFor() {
		isWait=true;
		return this;
	}
	
	public Terminal inheritIO() {
		isInheritIO=true;
		return this;
	}
	
	public Process execute() {
		return execute(commands, isWait, isInheritIO);
	}
	
	public static Process execute(String[] commands, boolean isWait, boolean isInheritIO) {
		try {
			ProcessBuilder builder=new ProcessBuilder(commands);
			if(isInheritIO) {
				builder.inheritIO();
			}
			Process process=builder.start();
			if(isWait) {
				process.waitFor();
			}
			return process;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}