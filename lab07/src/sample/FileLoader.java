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

                if(columns[5].equals("FLASH FLOOD")){
                    flood++;
                } else if(columns[5].equals("SEVERE THUNDERSTORM")){
                    thunder++;
                } else if(columns[5].equals("SPECIAL MARINE")){
                    marine++;
                } else if(columns[5].equals("TORNADO")){
                    tornado++;
                }

                freq.put("flood", flood);
                freq.put("marine", marine);
                freq.put("tornado", tornado);
                freq.put("thunder", thunder);

            }
            //Color.AQUA, Color.GOLD, Color.DARKORANGE,Color.DARKSALMON
            //MAP {tornado=2298, thunder=18041, flood=4849, marine=4007}

            System.out.println("MAP "+freq);
            System.out.println("AQUA, GOLD, ORANGE, SALMON");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return freq;
    }
}