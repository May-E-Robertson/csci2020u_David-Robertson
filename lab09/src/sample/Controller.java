package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Controller {
    @FXML
    private Canvas mainCanvas;
    @FXML
    public GraphicsContext gc;

    private ArrayList<Float> data1;
    private ArrayList<Float> data2;

    private String stock1;
    private String stock2;

    public void initialize() throws IOException {
        gc = mainCanvas.getGraphicsContext2D();


        stock1 = "AAPL";
        stock2 = "GOOG";

        data1 = downloadStockPrices(stock1);
        data2 = downloadStockPrices(stock2);

        drawLinePlot(data1, Color.RED);
        drawLinePlot(data2, Color.BLUE);
    }

    public void drawLinePlot(ArrayList<Float> data, Color color) {

        gc.setLineWidth(2);
        gc.setStroke(Color.BLACK);

        //xaxis
        gc.strokeLine(50, 750, 750, 750);
        //yaxis
        gc.strokeLine(50, 0, 50, 750);

        plotLine(data, color);
    }

    public void plotLine(ArrayList<Float> data, Color color){
        gc.setStroke(color);
        int lineLength = 600/data.size();
        int lineStart = 50;
        int lineEnd = lineStart+lineLength;

        for(int i = 0; i < 71; i++){
            gc.strokeLine(lineStart,700-data.get(i),lineEnd,700-data.get(i+1));
            lineStart = lineEnd;
            lineEnd = lineEnd+lineLength;
        }
    }

    public ArrayList<Float> downloadStockPrices(String stock) throws IOException {
        ArrayList<Float> data = new ArrayList<Float>();
        String sURL = "https://query1.finance.yahoo.com/v7/finance/download/"+stock+"?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true";
        URL url = new URL(sURL);
        URLConnection conn = url.openConnection();
        conn.setDoOutput(false);
        conn.setDoInput(true);

        InputStreamReader inStream = new InputStreamReader(conn.getInputStream());
        BufferedReader buffer = new BufferedReader(inStream);
        String line;
        int lineCheck = 0;
        while ((line = buffer.readLine()) != null) {
            if (lineCheck == 0) {
                lineCheck++;
            } else {
                String[] columns = line.split(",");
                data.add(Float.valueOf(columns[4]));
            }
        }
        return data;
    }
}

