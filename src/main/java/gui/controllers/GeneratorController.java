package gui.controllers;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.TaskGenerator;
import model.tasks.Task;

import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Logger;

public class GeneratorController
{
    private Button generateButton;
    private TextField minTasksField;
    private TextField maxTasksField;
    private TextField maxEintrittszeitField;
    private TextField maxRechenzeitField;
    private TextField maxPrioritaetField;

    private TaskTableController taskTableController;

    public GeneratorController(Parent root, TaskTableController taskTableController)
    {
        generateButton = (Button)root.lookup("#GenerierenButton");
        minTasksField = (TextField)root.lookup("#MinTasksTextField");
        maxTasksField = (TextField)root.lookup("#MaxTasksTextField");
        maxEintrittszeitField = (TextField)root.lookup("#MaxEintrittszeitTextField");
        maxRechenzeitField = (TextField)root.lookup("#MaxRechenzeitTextField");
        maxPrioritaetField = (TextField)root.lookup("#MaxPrioritaetTextField");

        this.taskTableController = taskTableController;

        generateButton.setOnMouseClicked(e -> {
            handleClickOnButton();
        });
    }

    public void handleClickOnButton()
    {
        int minTasks = 0;
        int maxTasks = 0;
        int maxEintrittszeit = 0;
        int maxRechenzeit = 0;
        int maxPrioritaet = 0;

        try {
            minTasks = NumberFormat.getInstance().parse(minTasksField.getText()).intValue();
            maxTasks = NumberFormat.getInstance().parse(maxTasksField.getText()).intValue();
            maxEintrittszeit = NumberFormat.getInstance().parse(maxEintrittszeitField.getText()).intValue();
            maxRechenzeit = NumberFormat.getInstance().parse(maxRechenzeitField.getText()).intValue();
            maxPrioritaet = NumberFormat.getInstance().parse(maxPrioritaetField.getText()).intValue();


            List<Task> allTasks = TaskGenerator.getTasks(minTasks, maxTasks, maxEintrittszeit, maxRechenzeit, maxPrioritaet);

            if(allTasks == null)
            {
                Logger.getGlobal().severe("Argument Error in generator");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Argument-Error");
                alert.setHeaderText("Minimale Taskanzahl darf nicht größer sein als maximale Taskanzahl.");
                alert.show();
                return;
            }

            taskTableController.clear();

            for(Task task : allTasks)
            {
                taskTableController.addColumn(task);
            }

        } catch (ParseException e) {

            Logger.getGlobal().severe("No numbers in textField");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Argument-Error");
            alert.setHeaderText("Bitte gebe nur Zahlen in die Textfelder ein.");
            alert.show();
        }
    }
}
