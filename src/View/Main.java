package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent parent = fxmlLoader.load(getClass().getResource("view.fxml").openStream());
        Scene scene = new Scene(parent);
        scene.getStylesheets().add("View/dark-theme.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
