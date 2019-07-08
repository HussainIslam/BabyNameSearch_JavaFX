package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);

        TextField textYear = new TextField();
        TextField textGender = new TextField();
        TextField textName = new TextField();

        pane.add(new Label("Enter the Year"), 0, 0);
        pane.add(textYear, 1, 0);
        pane.add(new Label("Enter the Gender: "), 0, 1);
        pane.add(textGender, 1, 1);
        pane.add(new Label("Enter the Name: "), 0, 2);
        pane.add(textName, 1, 2);

        Label outputText = new Label("");
        pane.add(outputText, 0, 3);
        GridPane.setColumnSpan(outputText, 2);

        HBox buttons = new HBox();
        GridPane.setColumnSpan(buttons, 2);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);
        pane.add(buttons, 0, 4);


        Button btnSubmitQuery = new Button("Submit Query");
        btnSubmitQuery.prefWidth(100);
        Button btnExit = new Button("Exit");
        btnExit.setPrefWidth(100);
        buttons.getChildren().addAll(btnSubmitQuery, btnExit);


        btnSubmitQuery.setOnAction(e ->{
            int year;
            char gender;
            String name;
            try {
                if(textYear.getText().equals("") || textName.equals("") || textGender.equals("")){
                    throw new Exception();
                }
                year = Integer.parseInt(textYear.getText());
                if(year < 2001 || year > 2010){
                    throw new WrongYearException();
                }

                gender = textGender.getText().charAt(0);
                if(!(gender == 'M' || gender == 'm' || gender == 'F' || gender == 'f')){
                    throw new WrongGenderException();
                }

                name = textName.getText();
                Reader fileName = new FileReader("babynamesranking2001to2010/babynamesranking"+year+".txt");
                BufferedReader bis = new BufferedReader(fileName);
                String rank="";
                String line;
                while((line = bis.readLine()) != null){
                    String[] tokens = line.split("\t");
                    for(int i = 0; i < tokens.length; i++){
                        tokens[i] = tokens[i].trim();
                    }
                    if(gender == 'M' || gender == 'm'){
                        if(tokens[1].equals(name)){
                            rank = tokens[0];
                        }
                    }
                    else{
                        if(tokens[3].equals(name)){
                            rank = tokens[0];
                        }
                    }
                }
                outputText.setText((gender == 'M' || gender == 'm' ? "Boy " : "Girl ")
                                    +"name " +name +" is ranked # " +rank +" in " + year +" year");
            }
            catch (WrongYearException wye){
                System.out.println("This is a wrong year");
            }
            catch (WrongGenderException wge){
                System.out.println("The gender that is entered is wrong!");
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        });

        primaryStage.setTitle("Search Name Ranging Application");
        primaryStage.setScene(new Scene(pane, 300, 275));
        primaryStage.show();

        btnExit.setOnAction(event -> {
            primaryStage.hide();
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}


