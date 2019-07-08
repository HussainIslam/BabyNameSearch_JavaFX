package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox mainPane = new VBox();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setSpacing(10);
        GridPane pane = new GridPane();
        mainPane.getChildren().add(pane);
        pane.setAlignment(Pos.CENTER);
        pane.setPrefWidth(250);
        pane.setHgap(10);
        pane.setVgap(10);

        TextField textYear = new TextField();
        textYear.setPromptText("Enter Year");
        textYear.setPrefWidth(100);
        TextField textGender = new TextField();
        textGender.setPromptText("Enter Gender");
        textGender.setPrefWidth(50);
        TextField textName = new TextField();
        textName.setPromptText("Enter Name");
        textName.setPrefWidth(100);

        pane.add(new Label("Enter the Year"), 0, 0);
        pane.add(textYear, 1, 0);
        pane.add(new Label("Enter the Gender: "), 0, 1);
        pane.add(textGender, 1, 1);
        pane.add(new Label("Enter the Name: "), 0, 2);
        pane.add(textName, 1, 2);

        Label outputText = new Label("");
        pane.add(outputText, 0, 3);
        GridPane.setColumnSpan(outputText, 3);

        HBox buttons = new HBox();
        //buttons.setPrefWidth(380);
        GridPane.setColumnSpan(buttons, 3);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);
        mainPane.getChildren().add(buttons);


        Button btnSubmitQuery = new Button("Submit Query");
        btnSubmitQuery.prefWidth(100);
        Button btnExit = new Button("Exit");
        btnExit.setPrefWidth(100);
        Button btnRetry = new Button("Try Again");
        btnRetry.setPrefWidth(100);
        buttons.getChildren().addAll(btnSubmitQuery, btnRetry, btnExit);


        btnSubmitQuery.setOnAction(e ->{
            int year;
            String gender;
            String name;
            try {
                if(textYear.getText().equals("") || textName.equals("") || textGender.equals("")){
                    throw new EmptyInputException();
                }
                year = Integer.parseInt(textYear.getText());
                if(year < 2001 || year > 2010){
                    throw new WrongYearException();
                }

                gender = textGender.getText().toLowerCase();
                if (gender.length() > 1){
                    throw new WrongGenderException();
                }
                if(!(gender.charAt(0) == 'm' || gender.charAt(0) == 'f')){
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
                    if(gender.charAt(0) == 'm'){
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
                if(rank.equals("")){
                    throw new NameNotFoundException();
                }
                else{
                    outputText.setText((gender.charAt(0) == 'm' ? "Boy " : "Girl ")
                                        +"name " +name +" is ranked # " +rank +" in " + year +" year");

                }

            }
            catch (NameNotFoundException nnfe){
                textYear.setText("");
                textGender.setText("");
                textName.setText("");
                this.generateAlert(Alert.AlertType.INFORMATION, "Information", "Not found", "The queried name was not found in database");
            }
            catch (EmptyInputException eix){
                this.generateAlert(Alert.AlertType.WARNING, "Warning!", "Empty Fields", "Please enter values in all the fields");
            }
            catch (WrongYearException wye){
                this.generateAlert(Alert.AlertType.ERROR, "Error!!!", "Invalid Year", "Please enter a year between 2001 and 2010");
            }
            catch (WrongGenderException wge){
                this.generateAlert(Alert.AlertType.ERROR, "Error!!!", "Invalid Gender", "Please enter (M/m) for male or (F/f) for female");
            }
            catch (Exception ex){
                this.generateAlert(Alert.AlertType.ERROR, "Error!!!", "Unexpected Error", ex.getMessage());
            }
        });

        primaryStage.setTitle("Search Name Ranging Application");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(mainPane, 350, 250));
        primaryStage.show();

        btnExit.setOnAction(event -> {
            primaryStage.hide();
        });

        btnRetry.setOnAction(event -> {
            textYear.setText("");
            textGender.setText("");
            textName.setText("");
        });
    }

    public void generateAlert(Alert.AlertType type, String title, String header, String message){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }



    public static void main(String[] args) {
        launch(args);
    }
}


