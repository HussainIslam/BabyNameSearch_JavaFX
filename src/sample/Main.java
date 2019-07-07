package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);

        pane.add(new Label("Enter the Year"), 0, 0);
        pane.add(new TextField(), 1, 0);
        pane.add(new Label("Enter the Gender: "), 0, 1);
        pane.add(new TextField(), 1, 1);
        pane.add(new Label("Enter the Name: "), 0, 2);
        pane.add(new TextField(), 1, 2);

        Label output = new Label("Boy name Javiar is ranked # 190 in 2010 year");
        pane.add(output, 0, 3);
        GridPane.setColumnSpan(output, 2);


        HBox buttons = new HBox();
        GridPane.setColumnSpan(buttons, 2);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);
        pane.add(buttons, 0, 4);


        Button submitQuery = new Button("Submit Query");
        submitQuery.prefWidth(100);
        Button exit = new Button("Exit");
        exit.setPrefWidth(100);
        buttons.getChildren().addAll(submitQuery, exit);






        primaryStage.setTitle("Search Name Ranging Application");
        primaryStage.setScene(new Scene(pane, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
