/*
Name: Joseph M. Scollo
School: Suny Oswego
Course: CSC-365 Data Structures

Class: Main
About Class: This class is the Graphical User Interface (GUI) and main method of the application.
The details of its methods and their members and how the function ts documented through out the code.
*/

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class Main extends Application {

    private Button button;
    private Button button2;
    private TextField field;
    private Text fr;
    private TextFlow textFlow,r1, r2, r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16,r17,r18,r19,r20;
    private Text[] t;
    private String[] allScores;
    private String userEntry;
    private String score;
    private String r;
    Text temp;
    private Controller c;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException{

        String name = "webpages.txt";
        String currentWebPage = "";
        userEntry = "";
        BufferedReader buf = new BufferedReader(new FileReader(name)); // reads the titles of 20 web pages from a text file and stores the in an array
        String[] url = new String[20];
        int i = 0;
        c = new Controller(); // creates a controller object.
        t = new Text[20]; // creates array of type Text from the Javafx library. Created to display text on the window.
        while((currentWebPage = buf.readLine()) != null){
           url[i] = currentWebPage;
           i++;
        }
        buf.close();

        Text t1 = new Text(url[0]);        //Lines 65 - 84 creates text objects with url names
        Text t2 = new Text(url[1]);
        Text t3 = new Text(url[2]);
        Text t4 = new Text(url[3]);
        Text t5 = new Text(url[4]);
        Text t6 = new Text(url[5]);
        Text t7 = new Text(url[6]);
        Text t8 = new Text(url[7]);
        Text t9 = new Text(url[8]);
        Text t10 = new Text(url[9]);
        Text t11 = new Text(url[10]);
        Text t12 = new Text(url[11]);
        Text t13 = new Text(url[12]);
        Text t14 = new Text(url[13]);
        Text t15 = new Text(url[14]);
        Text t16 = new Text(url[15]);
        Text t17 = new Text(url[16]);
        Text t18 = new Text(url[17]);
        Text t19 = new Text(url[18]);
        Text t20 = new Text(url[19]);
        r1 = new TextFlow();  // 85 - 104 creates text objects inside a textflow wrapper.
        r2 = new TextFlow();
        r3 = new TextFlow();
        r4 = new TextFlow();
        r5 = new TextFlow();
        r6 = new TextFlow();
        r7 = new TextFlow();
        r8 = new TextFlow();
        r9 = new TextFlow();
        r10 = new TextFlow();
        r11 = new TextFlow();
        r12 = new TextFlow();
        r13 = new TextFlow();
        r14 = new TextFlow();
        r15 = new TextFlow();
        r16 = new TextFlow();
        r17 = new TextFlow();
        r18 = new TextFlow();
        r19 = new TextFlow();
        r20 = new TextFlow();
        Text tSim = new Text("Your selected web page is most similar to:");

        primaryStage.setTitle("Web Compare"); // sets the primary stage and titles its window "web compare".
        button = new Button("Compare"); // creates a new button called compare
        button2 = new Button("Clear"); // creates a new button called clear
        Button button3 = new Button("Quit");// creates a new button called quit
        field = new TextField("Enter a valid URL"); // Creates a text field for user input


        GridPane layout = new GridPane();  // sets up a gridpane to help organize the layout of buttons and text

        layout.setPadding(new Insets(20,20,20,20)); // sets the margins
        layout.setVgap(5);
        layout.setHgap(5);


        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { // Trips when Button is clicked
            @Override
            public void handle(ActionEvent e)
            {

                try {

                    button.setDisable(true); // Disables button so it cant be clicked twice in a row
                    userEntry = field.getText();
                    allScores = c.run(userEntry);
                    score = c.mostSimilar();

                    for(int i = 0; i < allScores.length; i++) {
                        temp = new Text(allScores[i]);
                        t[i] = temp;

                    }
                    r1.getChildren().add(t[0]); //lines 138 - 157 puts The scores in text form on the screen
                    r2.getChildren().add(t[1]);
                    r3.getChildren().add(t[2]);
                    r4.getChildren().add(t[3]);
                    r5.getChildren().add(t[4]);
                    r6.getChildren().add(t[5]);
                    r7.getChildren().add(t[6]);
                    r8.getChildren().add(t[7]);
                    r9.getChildren().add(t[8]);
                    r10.getChildren().add(t[9]);
                    r11.getChildren().add(t[10]);
                    r12.getChildren().add(t[11]);
                    r13.getChildren().add(t[12]);
                    r14.getChildren().add(t[13]);
                    r15.getChildren().add(t[14]);
                    r16.getChildren().add(t[15]);
                    r17.getChildren().add(t[16]);
                    r18.getChildren().add(t[17]);
                    r19.getChildren().add(t[18]);
                    r20.getChildren().add(t[19]);

                    button2.setDisable(false); // enables the clear button

                } catch (IOException ex) {
                   ex.printStackTrace();
                }


            }

        };
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>(){ //tripped when button2 is clicked
            public void handle(ActionEvent e){
                button.setDisable(false); // enables the compare button to be clicked again.
                button2.setDisable(true); // disables clear button so it cant be clicked twice in a row.
                field.clear();            // clears all textflow objects from screen. text field text, cosine scores, and max cosine score message.
                textFlow.getChildren().clear();
                r1.getChildren().clear();
                r2.getChildren().clear();
                r3.getChildren().clear();
                r4.getChildren().clear();
                r5.getChildren().clear();
                r6.getChildren().clear();
                r7.getChildren().clear();
                r8.getChildren().clear();
                r9.getChildren().clear();
                r10.getChildren().clear();
                r11.getChildren().clear();
                r12.getChildren().clear();
                r13.getChildren().clear();
                r14.getChildren().clear();
                r15.getChildren().clear();
                r16.getChildren().clear();
                r17.getChildren().clear();
                r18.getChildren().clear();
                r19.getChildren().clear();
                r20.getChildren().clear();
            }

        };
        layout.add(r1, 25, 0); // 198 - 221 places textflow objects on the screen at specific locations on the gridpane.
        layout.add(r2, 25, 1);
        layout.add(r3, 25, 2);
        layout.add(r4, 25, 3);
        layout.add(r5, 25, 4);
        layout.add(r6, 25, 5);
        layout.add(r7, 25, 6);
        layout.add(r8, 25, 7);
        layout.add(r9, 25, 8);
        layout.add(r10, 25, 9);
        layout.add(r11, 25, 10);
        layout.add(r12, 25, 11);
        layout.add(r13, 25, 12);
        layout.add(r14, 25, 13);
        layout.add(r15, 25, 14);
        layout.add(r16, 25, 15);
        layout.add(r17, 25, 16);
        layout.add(r18, 25, 17);
        layout.add(r19, 25, 18);
        layout.add(r20, 25, 19);
        textFlow = new TextFlow();
        fr = new Text(score);
        textFlow.getChildren().add(fr);
        layout.add(textFlow,0, 30);
        EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() { // tripped when quit button is clicked
            public void handle(ActionEvent e)
            {
                Platform.exit(); // shuts down the application
            }

        };
        button.setOnAction(event);
        button2.setOnAction(event2);
        button2.setDisable(true);
        button3.setOnAction(event3);

        layout.add(t1, 0, 0);  //234 - 258 maps text objects and button objects to specific locations on the gridpane.
        layout.add(t2, 0, 1);
        layout.add(t3, 0, 2);
        layout.add(t4, 0, 3);
        layout.add(t5, 0, 4);
        layout.add(t6, 0, 5);
        layout.add(t7, 0, 6);
        layout.add(t8, 0, 7);
        layout.add(t9, 0, 8);
        layout.add(t10, 0, 9);
        layout.add(t11, 0, 10);
        layout.add(t12, 0, 11);
        layout.add(t13, 0, 12);
        layout.add(t14, 0, 13);
        layout.add(t15, 0, 14);
        layout.add(t16, 0, 15);
        layout.add(t17, 0, 16);
        layout.add(t18, 0, 17);
        layout.add(t19, 0, 18);
        layout.add(t20, 0 ,19);
        layout.add(field, 0, 25);
        layout.add(button, 0, 26);
        layout.add(button2, 0, 27);
        layout.add(button3, 0, 28);
        layout.add(tSim,0, 29);

        Scene scene = new Scene(layout, 750, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}

