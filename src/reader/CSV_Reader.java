package reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSV_Reader {
	
	
	public static String[][] read(String path) {
		try {
			BufferedReader reader=new BufferedReader(new FileReader(new File(path)));
			String line;
			List<String[]> rows=new ArrayList<>();
			while((line=reader.readLine())!=null) {
				String[] cells=line.split("\t");
				if(!isEmpty(cells)) {
					autoTrim(cells);
					rows.add(cells);
				}
			}
			reader.close();
			return rows.toArray(new String[rows.size()][]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static void autoTrim(String[] cells) {
		for(String cell: cells) {
			cell=cell.trim();
		}
	}
	
	private static boolean isEmpty(String[] cells) {
		for(String cell: cells) {
			if(cell.length()>0) {
				return false;
			}
		}
		return true;
	}
	
	
	
	
	
}