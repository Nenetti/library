package dictionary;

import java.util.HashMap;

import reader.CSV_Reader;






public class Dictionary {

	private HashMap<String, Sessions> sessions_en = new HashMap<>();
	private HashMap<String, Sessions> sessions_jp = new HashMap<>();

	public Dictionary(String filePath) {
		String[][] cells=CSV_Reader.read(filePath);
		if(cells==null) {return;}
		for(String[] rows: cells) {
			Session english;
			Session japanese;
			switch(rows.length) {
			case 3:
				//英語のみ
				english=new Session(rows[0], rows[2]);
				sessions_en.put(rows[0], new Sessions(english, null));
				break;
			case 6:
				//日本語あり
				english=new Session(rows[0], rows[2]);
				japanese=new Session(rows[3], rows[5]);
				Sessions sessions=new Sessions(english, japanese);
				sessions_en.put(rows[0], sessions);
				sessions_jp.put(rows[3], sessions);
				break;
			}
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