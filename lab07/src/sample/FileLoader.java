package sample;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileLoader {
    private String filename;
    private Map<String, Integer> freq;

    private int flood;
    private int thunder;
    private int marine;
    private int tornado;

    public FileLoader(String filename){
        this.filename = filename;
    }

    public Map<String, Integer> mapFile(){
        String line = "";
        flood = thunder = marine = tornado = 0;
        Map<String, Integer> freq = new HashMap<String, Integer>();

        try{
            BufferedReader br = new BufferedReader(new FileReader(this.filename));
            while ((line = br.readLine())!=null){
                String[] columns = line.split(",");

                if(freq.containsKey(columns[5])){
                    int count = freq.get(columns[5]);
                    //count++;
                    freq.put(columns[5],count+1);
                } else {
                    freq.put(columns[5], 1);
                }
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return freq;
    }
}