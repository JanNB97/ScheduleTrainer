package gui;

import gui.controllers.GeneratorController;
import gui.controllers.SchedulingController;
import gui.controllers.TaskTableController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application
{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("schedulingDesign0_3.fxml"));

        stage.setScene(new Scene(root));
        stage.show();

        TaskTableController taskTableController = new TaskTableController(root);
        GeneratorController generatorController = new GeneratorController(root, taskTableController);
    }
}
