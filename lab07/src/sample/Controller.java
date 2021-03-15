package sample;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Controller {
    @FXML
    private Canvas mainCanvas;
    @FXML
    public GraphicsContext gc;

    private String filepath;
    private Map<String, Integer> freq;

    private static Color[] pieColours = {
            Color.AQUA , Color.GOLD , Color.DARKORANGE ,
            Color.DARKSALMON
    };

    private static String[] legendName = {

           "TORNADO",
           "SEVERE THUNDERSTORM",
           "FLASH FLOOD",
           "SPECIAL MARINE"

    };

    @FXML
    public void initialize() {
        gc = mainCanvas.getGraphicsContext2D();

        filepath = "../lab07/src/sample/weatherwarnings-2015.csv";

        FileLoader file = new FileLoader(filepath);
        freq = file.mapFile();

        drawChart(450, 250, freq, pieColours);
        drawLegend();

        //pwd is /Users/davidrobertson/Desktop/DevLabs/csci2020u/lab07
    }

    public void drawChart(int x, int y, Map<String, Integer> freq, Color[] colors){

        //calculate sum of array
        double total = freq.get("tornado")+freq.get("thunder")+freq.get("flood")+freq.get("marine")*1.0000;

        int[] arr = new int[3];

        int i = 0;
        double angleStart = 0;

        gc.setFill(pieColours[i]);

        double val = freq.get("tornado")*1.000;
        double math = val/total;

        for(Map.Entry<String,Integer> entry : freq.entrySet()){

            /*
            x - the X coordinate of the arc.
            y - the Y coordinate of the arc.
            w - the width of the arc.
            h - the height of the arc.
            startAngle - the starting angle of the arc in degrees.
            arcExtent - the angular extent of the arc in degrees.
            closure - closure type (Round, Chord, Open) or null.
            */

            gc.setFill(pieColours[i]);
            gc.fillArc(x, y, 450, 450,  angleStart,entry.getValue()/total*360, ArcType.ROUND);

            angleStart+=entry.getValue()/total * 360;
            i++;
        }
    }

    public void drawLegend(){

        /*
        x - the X position of the upper left corner of the rectangle.
        y - the Y position of the upper left corner of the rectangle.
        w - the width of the rectangle.
        h - the height of the rectangle.
        */

        int y = 200;
        //print rectangles
        for(int i = 0; i<4; i++){
            gc.setFill(pieColours[i]);
            gc.fillRect(50, y+=100, 100, 50);
        }

        y = 225;
        for(int i = 0; i<4; i++){
            gc.setFill(Color.BLACK);
            gc.fillText(legendName[i],175, y+=100);
        }
    }
}
