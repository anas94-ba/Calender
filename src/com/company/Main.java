package com.company;

import com.sun.org.apache.xerces.internal.xs.ItemPSVI;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.util.ArrayList;


public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage1) {
        //setting stage size
        stage1.setWidth(440);
        stage1.setHeight(440);

        //creating ArrayList of Buttons
        ArrayList<Label> list = new ArrayList<>();

        //creating label1 , seting its size , font size and its padding
        Label label1 = new Label(" Month");
        label1.setStyle("-fx-font-size:2em; -fx-background-color:#ffff74 ; ");
        label1.setPrefSize(250, 70);
        label1.setPadding(new Insets(10));
        label1.setAlignment(Pos.CENTER);

        //creating text1 , seting its size , font size and its padding
        TextField text1 = new TextField();
        text1.setStyle("-fx-font-size:2em;");
        text1.setPrefSize(230, 70);
        text1.setPadding(new Insets(3, 3, 3, 3));


        //creating label2 , seting its size , font size and its padding
        Label label2 = new Label(" Year");
        label2.setStyle("-fx-font-size:2em; -fx-background-color:#ffff74 ;");
        label2.setPrefSize(250, 70);
        label2.setAlignment(Pos.CENTER);
        label2.setPadding(new Insets(3, 3, 3, 3));

        //creating text2 , seting its size , font size and its padding
        TextField text2 = new TextField();
        text2.setStyle("-fx-font-size:2em;");
        text2.setPrefSize(230, 70);
        text2.setPadding(new Insets(3, 3, 3, 3));


        //Creating a Grid Pane
        GridPane gridPane1 = new GridPane();
        GridPane gridPane2 = new GridPane();

        //Setting the padding && vertical and horizontal gaps between the columns
        gridPane2.setAlignment(Pos.CENTER);
        gridPane2.setPadding(new Insets(10));
        gridPane2.setVgap(5);
        gridPane2.setHgap(5);

        //creating labels , initilize , and seting its size
        String[] list2 = { "Sat" , "Sun", "Mon", "Tue", "Wed", "The", "Fri"};
        for (int i = 0; i < 7; i++) {
            Label temp = new Label(list2[i]);
            temp.setPrefSize(70, 70);
            temp.setAlignment(Pos.CENTER);
            temp.setStyle("-fx-background-color:#707b59 ; -fx-fontsize:2em; ");
            gridPane2.add(temp, (i % 7), 0);
        }

        //horizntal box
        Label label3 = new Label("Month:  ");
        label3.setStyle("-fx-font-size:2em;-fx-background-color:#00ff00 ;");
        //label3.setPrefSize(180, 2);
        label3.setPadding(new Insets(2));
        HBox h2 = new HBox(label3);
        h2.setAlignment(Pos.CENTER);



        //Setting the padding && vertical and horizontal gaps between the columns
        gridPane1.setAlignment(Pos.CENTER);
        gridPane1.setPadding(new Insets(10));
        gridPane1.setVgap(5);
        gridPane1.setHgap(5);


        //creating labels , initilize , and seting its size
        for (int i = 0; i < 37; i++) {
            Label temp = new Label("");
            list.add(temp);
            temp.setPrefSize(70, 70);
            temp.setAlignment(Pos.CENTER);
            temp.setStyle("-fx-background-color:#add8e6 ; -fx-fontsize:2em; -fx-text-fill: #ffffff");
            gridPane1.add(temp, (i % 7), (i / 7));
        }


        //creating and setting button
        Button b1 = new Button(" click to show Calender ");
        b1.setPrefSize(300, 300);
        b1.setStyle("-fx-background-color:#7154b0 ;");
        b1.setOnMouseEntered(e -> b1.setStyle("-fx-background-color:#475011 ;"));
        b1.setOnMouseExited(e -> b1.setStyle("-fx-background-color:#7154b0 ;"));
        b1.setOnMouseClicked(e -> b1.setStyle("-fx-background-color:#440017;"));
        b1.setOnAction(e -> {
            for (int i = 0; i < 37; i++) {
                Label temp=list.get(i);
                temp.setText("");
                temp.setStyle("-fx-background-color:#add8e6 ; -fx-fontsize:2em; -fx-text-fill: #ffffff");
            }
            int num1=0;
            int num2=0;

            try {
                num1 = Integer.parseInt(text1.getText());
                if(num1>12 || num1<1){
                    throw new Exception();
                }
            }catch (Exception exception){
                label3.setText("Month input is invalid");
                return;
            }

            try {
                num2 = Integer.parseInt(text2.getText());
            }catch (Exception exception){
                label3.setText("Year input is invalid");
                return;
            }

            printMonthTitle(num1, label3);
            printMonthBody(num1 , num2 ,list);
        });
        HBox h1 = new HBox(b1);
        h1.setAlignment(Pos.CENTER);
        h1.setPadding(new Insets(10));

        //hbox
        HBox h3 = new HBox(text1, label1, text2, label2);
        h3.setSpacing(10);
        h3.setAlignment(Pos.CENTER);
        h3.setPadding(new Insets(10));

        VBox v2 = new VBox(h3, h1, h2, gridPane2, gridPane1);

        Scene scene1 = new Scene(v2);

        //Setting the back ground color
        v2.setStyle("-fx-background-color:#74c2ff ;");
        //Setting title to the Stage
        stage1.setTitle("Calendar");

        //Adding scene to the stage
        stage1.setScene(scene1);

        //Displaying the contents of the stage
        stage1.show();


    }

    private void printMonthBody(int month, int year, ArrayList<Label> list) {
        int firstDay=startDay(month,year);
        int numberOfDays=numberOfDays(month,year);
        list.get(firstDay).setText("1");
        list.get(firstDay).setStyle("-fx-background-color:#445572 ; -fx-fontsize:2em; -fx-text-fill: #ffffff; ");
        for(int i=1 , j=firstDay+1 ; i<numberOfDays ; i++,j++){
            list.get(j).setText("" + (i+1) );
            list.get(j).setStyle("-fx-background-color:#445572 ; -fx-fontsize:2em; -fx-text-fill: #ffffff; ");
        }
    }

    private int numberOfDays(int month, int year) {
        int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        if (month == 2 && isLeapYear(year)) {
            days[month] = 29;
        }
        return days[month];
    }

    private boolean isLeapYear(int year) {
        return ( ( (year%4 == 0 ) && (year%100 != 0 ) ) ||
                ( (year%4 == 0 ) && (year%100 == 0 ) && (year%400 == 0 )) );
    }

    private int startDay(int month, int year) {
        //Zeller's congruence

        if(month == 1 || month == 2) {
            month += 12;
            year--;
        }
        int day = (1 + (int)( ( (month + 1) * 26 ) / 10.0 ) + year +
                (int)(year / 4.0) + 6 * (int)(year / 100.0) + (int)(year / 400.0) ) % 7;
        return day;
    }

    private void printMonthTitle(int num, Label label3) {
        String[] list = {"January", "February", "March", "April", "May", "June",
                       "July", "August", "September", "October", "November", "December "};
        label3.setText("Month : " + list [ num - 1 ] );
    }
}
