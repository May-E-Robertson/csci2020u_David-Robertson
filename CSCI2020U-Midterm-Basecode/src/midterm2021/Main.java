package midterm2021;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

public class Main extends Application {

    /**
     * The start method initalizes the main menu, a gridpane for main, and all buttons on main. It also contains the code that
     * handles the 3 main menu button presses, as well as the back to main button press.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("CSCI2020U - Midterm");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        //Creating the menu buttons
        Button btApp1 = new Button("Animation");
        btApp1.setPrefWidth(200);
        Button btApp2 = new Button("2D Graphics");
        btApp2.setPrefWidth(200);
        Button btApp3 = new Button("About");
        btApp3.setPrefWidth(200);
        Button back = new Button("Back to Main");
        back.setPrefWidth(250);

        /**
         * Handles the actions that occur when the "Animation" button is pressed
         *
         * creates an animation scene, adds an animation grid pane to it
         * then adds a canvas to gridpane.
         *
         * method drawAnimation is then called to handle animation
         */
        btApp1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                //animation grid
                GridPane aniGrid = new GridPane();
                grid.setAlignment(Pos.CENTER);
                grid.setHgap(10);
                grid.setVgap(10);

                //animation scene
                Scene animationScene = new Scene(aniGrid, 250, 250);
                primaryStage.setScene(animationScene);
                primaryStage.setTitle("Animation");
                System.out.println("Clicked on Animation button");

                //Create Canvas object and add it into the scene
                Canvas aniCanvas = new Canvas();
                aniCanvas.widthProperty().bind(primaryStage.widthProperty());
                aniCanvas.heightProperty().bind(primaryStage.heightProperty());

                aniGrid.add(aniCanvas, 1, 2);


                aniGrid.add(back, 1,1);

                drawAnimation(aniCanvas);
            }
        });

        /**
         * Handles the actions that occur when the "2D Graphics" button is pressed
         *
         * creates a graphics scene, adds a graphics grid pane to it
         * then adds a canvas to gridpane.
         *
         * method drawGraphics is then called to handle all drawing  of graphics
         */
        btApp2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GridPane graphicsGrid = new GridPane();
                grid.setAlignment(Pos.CENTER);
                grid.setHgap(10);
                grid.setVgap(10);

                //animation scene
                Scene graphicsScene = new Scene(graphicsGrid, 900, 875);
                primaryStage.setScene(graphicsScene);
                primaryStage.setTitle("2D Graphics");
                System.out.println("Clicked on Graphics 2D button");

                //Create Canvas object and add it into the scene
                Canvas graphicsCanvas = new Canvas();
                graphicsCanvas.widthProperty().bind(primaryStage.widthProperty());
                graphicsCanvas.heightProperty().bind(primaryStage.heightProperty());

                graphicsGrid.add(graphicsCanvas, 1, 2);

                graphicsGrid.add(back, 1,1);

                drawGraphics(graphicsCanvas);
            }
        });

        /**
         * Handles the actions that occur when the "About" button is pressed
         *
         * creates an about scene, adds an about grid pane to it
         * then adds a canvas to gridpane.
         *
         * method getInfo is then called to handle getting info from xml file
         */
        btApp3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GridPane aboutGrid = new GridPane();
                grid.setAlignment(Pos.CENTER);
                grid.setHgap(10);
                grid.setVgap(10);

                //animation scene
                Scene aboutScene = new Scene(aboutGrid, 900, 875);
                primaryStage.setScene(aboutScene);
                primaryStage.setTitle("About");
                System.out.println("Clicked on About button");

                //Create Canvas object and add it into the scene
                Canvas aboutCanvas = new Canvas();
                aboutCanvas.widthProperty().bind(primaryStage.widthProperty());
                aboutCanvas.heightProperty().bind(primaryStage.heightProperty());

                aboutGrid.add(aboutCanvas, 1, 2);
                aboutGrid.add(back, 1,1);

                try{
                    getInfo(aboutCanvas);
                } catch(IOException e){
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }

            }
        });



//        Add the menu buttons to the grid
        grid.add(btApp1, 0,1);
        grid.add(btApp2, 0,2);
        grid.add(btApp3, 0,3);

        // main App Scene
        Scene mainScene = new Scene(grid, 300, 275);

        primaryStage.setScene(mainScene);
        primaryStage.show();

        /**
         * method to handle all "back to main" buttons throughout all scenes
         *
         * simply sets the scene back to "mainScene"
         */
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.setScene(mainScene);
            }
        });
    }

    //initializing of animation variables
    private int frameWidth = 32;
    private int frameHeight = 36;
    private int numFrames = 8;
    private int sourceHeightOffset = 0;
    private int sourceWidthOffset = 193;
    private int frameIndex = 0;

    //used to keep track of the column being printed
    private int columnIndex = 0;


    /**
     * method used to animate duck based off sprite sheet located in resources
     *
     * a timeline is created that runs every 4000/3 milliseconds, allowing all three sprites
     * per row to show before moving to the next row.
     *
     * columnIndex is used to keep track of the column that is being printed. Once the third column
     * for the desired colour of duck is printed, columnIndex is reset and the next row will begin printing.
     *
     * @param canvas canvas that duck will be animated on
     */
    private void drawAnimation(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //loading image sprite using relative path
        Image image = new Image(getClass().getClassLoader().getResource("ducks.png").toString());

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        //4000/3 = 1333.333 is used so all three columns will be printed before moving on to next row
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1333.333), new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                //if the columnIndex is greater than 2 then reset our values and go down a row
                if(columnIndex >= 2){
                    //reset columnIndex
                    columnIndex = 0;
                    //set back to initial widthOffset

                    //square background for duck
                    //set to blue so it looks like water
                    gc.setFill(Color.SKYBLUE);
                    gc.fillRect(100, 100, frameWidth, frameHeight);

                    //drawing duck based off provided offsets
                    //offsets will find specific coordinates on on the sprite sheet then print a square of specified size at that location
                    gc.drawImage(image, sourceWidthOffset, sourceHeightOffset, frameWidth, frameHeight, 100, 100, frameWidth, frameHeight);

                    //these two lines are from tutorial code
                    //varying frameIndex from 0 to numFrames (not included) using "%"
                    frameIndex = (frameIndex+1) % numFrames;
                    //calculating offset height based off frameIndex
                    sourceHeightOffset = frameHeight*frameIndex;

                } else {
                    //else we increment to the right, printing all 3 ducks in the row

                    //column offset is the number that will be added to our inital sourceWidthOffset
                    //incrementing this allows us to iterate through column sprites before going to next row
                    //without changing our sourceWidthOffset inital value, which can cause problems
                    int columnOffset = columnIndex*32;

                    //square background for duck
                    //set to blue so it looks like water
                    gc.setFill(Color.SKYBLUE);
                    gc.fillRect(100, 100, frameWidth, frameHeight);

                    //drawing duck based off provided offsets
                    //offsets will find specific coordinates on on the sprite sheet then print a square of specified size at that location
                    gc.drawImage(image, sourceWidthOffset + columnOffset, sourceHeightOffset, frameWidth, frameHeight, 100, 100, frameWidth, frameHeight);

                    //increment column index by one
                    columnIndex++;
                }
            }
        }));
        //Starting the timeline
        timeline.playFromStart();

    }


    /**
     * method used to print shapes in a way that resembles my initials, D.R.
     *
     * @param canvas canvas that shapes will be drawn onto
     */
    private void drawGraphics(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //PRINTING LETTER D
        gc.setStroke(Color.MEDIUMORCHID);
        gc.setLineWidth(50);
        gc.strokeArc(0, 100, 300, 600,  270,180, ArcType.OPEN);

        gc.setFill(Color.MIDNIGHTBLUE);
        gc.fillRect(50, 75, 50, 650);


        //PRINTING LETTER R
        gc.setFill(Color.PALEVIOLETRED);
        gc.fillRect(500, 75, 50, 650);

        gc.setStroke(Color.YELLOWGREEN);
        gc.strokeArc(350, 100, 500, 300,  270,180, ArcType.OPEN);

        gc.setStroke(Color.MEDIUMTURQUOISE);
        gc.strokeLine(600, 475, 800, 675);

        //PRINTING DOTS
        gc.setFill(Color.MEDIUMSPRINGGREEN);
        gc.fillArc(275, 675, 50, 50,  0,360, ArcType.ROUND);

        gc.setFill(Color.LIGHTCORAL);
        gc.fillArc(840, 675, 50, 50,  0,360, ArcType.ROUND);

        //ADDING LABEL
        gc.setFill(Color.BLACK);
        gc.fillText("D.R.",50, 50);
    }

    /**
     * method used to get info from xml in string format, then output to textboxes on canvas
     *
     * @param canvas canvas that text will be displayed on
     * @throws IOException it is good practice to throw IOException when parsing files
     * @throws ParserConfigurationException added by IntelliJ to safely use newDocumentBuilder
     * @throws SAXException added by IntelliJ to safely parse inStream
     */
    //exceptions added by IntelliJ to catch parsers
    private void getInfo(Canvas canvas) throws IOException, ParserConfigurationException, SAXException {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        try {
            //reading fxml file
            String filepath = "../CSCI2020U-Midterm-Basecode/resources/info.xml";
            InputStream inStream = new FileInputStream(filepath);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            Document document = docBuilder.parse(inStream);
            document.getDocumentElement().normalize();

            NodeList itemNodes = document.getElementsByTagName("student");

            for (int i = 0; i < itemNodes.getLength(); i++) {
                Node itemNode = itemNodes.item(i);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element itemElement = (Element) itemNode;
                    String id = getTagValue("id", itemElement);
                    String name = getTagValue("name", itemElement);
                    String email = getTagValue("email", itemElement);
                    String desc = getTagValue("software-description", itemElement);

                    gc.setFill(Color.BLACK);
                    gc.fillText(id,150, 200);
                    gc.fillText(name, 150, 225);
                    gc.fillText(email, 150, 250);
                    gc.fillText(desc, 150, 300);
                }
            }
            inStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * method used to get information from xml file in string format
     *
     * @param tagName name of xml tag
     * @param elem elementNode
     * @return returns null
     */
    private static String getTagValue(String tagName, Element elem){
        NodeList tags = elem.getElementsByTagName(tagName);
        if (tags.getLength()>0){
            return tags.item(0).getTextContent();
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
