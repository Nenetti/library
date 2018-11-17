package reader;

import java.io.*;

public class CSV_Writer {

    public static void write(String path, String[][] data) {
        try {
            BufferedWriter writer=new BufferedWriter(new FileWriter(new File(path)));
            for(String[] rows:data){
                for(int i=0;i<rows.length;i++){
                    String cell=rows[i];
                    writer.write(cell);
                    if(rows.length>1&&i!=rows.length-1){
                        writer.write("\t");
                    }
                }
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}