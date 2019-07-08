package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

        Label output = new Label("Boy name Javiar is ranked # 190 in 2010 year");
        pane.add(output, 0, 3);
        GridPane.setColumnSpan(output, 2);


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
            try{
                int year = Integer.parseInt(textYear.getText());
                if(year < 2001 || year > 2010){
                    throw new Exception("Wrong year");
                }

                char gender = textGender.getText().charAt(0);
                if(!(gender == 'M' || gender == 'm' || gender == 'F' || gender == 'f')){
                    throw new Exception("Gender didn't match");
                }

                String name = textName.getText();
                System.out.println("Year: " +year +"Gender: " +gender + "Name: " +name);
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


