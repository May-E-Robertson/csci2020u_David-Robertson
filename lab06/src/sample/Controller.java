package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Controller {
    @FXML private Canvas mainCanvas;
    @FXML public GraphicsContext gc;

    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static double[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color.AQUA , Color.GOLD , Color.DARKORANGE ,
            Color.DARKSALMON , Color.LAWNGREEN , Color.PLUM
    };

    @FXML
    public void initialize() {
        gc = mainCanvas.getGraphicsContext2D();
        drawGraph(200, 800, avgHousingPricesByYear, Color.RED, 0);
        drawGraph(200, 800, avgCommercialPricesByYear, Color.BLUE, 25);

        drawCircle(450, 300, ageGroups, purchasesByAgeGroup, pieColours);
    }


    public void drawGraph(int width, int height, double[] data, Color color, int xPosition) {
        double maxVal = Double.NEGATIVE_INFINITY;
        double minVal = Double.MAX_VALUE;

        for (double val : avgCommercialPricesByYear) {
            if (val > maxVal)
                maxVal = val;
            if (val < minVal)
                minVal = val;
        }
        minVal = 0;

        double barWidth = width / data.length;
        gc.setFill(color);

        for (double val : data) {
            double barHeight = ((val - minVal) / (maxVal - minVal)) * height;
            gc.fillRect(xPosition, (height - barHeight), barWidth, barHeight);
            //2 times to add space between bars
            xPosition += 2 * barWidth;
        }
    }

    public void drawCircle(int cWidth, int cHeight, String[] ageGroups, double[] pByAgeGroup, Color[] colors){
            //x,y same for every circle, location
            //w, width
            //h, height
            //startAngle start
            //arcExtent end
            //arr[i]/total * 360
            int total = 0;

            //calculate sum of array
            for(int i = 0; i<pByAgeGroup.length; i++){
                total += pByAgeGroup[i];
            }

            double angleStart = 0;
            for(int i = 0; i<pByAgeGroup.length; i++){

                gc.setFill(pieColours[i]);

                gc.fillArc(cWidth, cHeight, 350, 350,  angleStart,pByAgeGroup[i]/total*360, ArcType.ROUND);

                angleStart+=pByAgeGroup[i]/total * 360;
            }
        }

    }

