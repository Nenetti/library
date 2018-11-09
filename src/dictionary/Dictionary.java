package dictionary;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;






public class Dictionary {

	private HashMap<String, Sessions> sessions_en = new HashMap<>();
	private HashMap<String, Sessions> sessions_jp = new HashMap<>();

	public Dictionary(String filePath) {
		try {
			BufferedReader reader=new BufferedReader(new FileReader(new File(filePath)));
			String line;
			while((line=reader.readLine())!=null) {
				String[] cells=line.split(",");
				if(cells!=null) {
					Session english;
					Session japanese;
					switch(cells.length) {
					case 3:
						//英語のみ
						english=new Session(cells[0], cells[2]);
						sessions_en.put(cells[0], new Sessions(english, null));
						break;
					case 6:
						//日本語あり
						english=new Session(cells[0], cells[2]);
						japanese=new Session(cells[3], cells[5]);
						Sessions sessions=new Sessions(english, japanese);
						sessions_en.put(cells[0], sessions);
						sessions_jp.put(cells[3], sessions);
						break;
					}
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Sessions getSessions(String key, Language language) {
		switch (language) {
		case English:
			return sessions_en.get(key);
		case Japanese:
			return sessions_jp.get(key);
		}
		return null;
	}
	
	public Session getSession(String key, Language language) {
		Sessions sessions=getSessions(key, language);
		if(sessions!=null) {
			return sessions.getSession(language);
		}
		return null;
	}

	public static void main(String[] args) {
		new Dictionary("/home/ubuntu/ros/sound/julius/dictionary/word_en.yomi");
	}









}