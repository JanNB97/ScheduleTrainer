package gui.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import main.SchedulingInfoPack;
import main.SchedulingInfoPackBuilder;
import model.tasks.Task;

import java.util.List;

public class SchedulingController
{
    private ChoiceBox schedlingChoiceBox;
    private TaskTableController taskTableController;

    private List<SchedulingInfoPack> allInfoPacks;

    private Label avgWaitTime;
    private Label avgTaskTime;
    private Label avgResponseTime;

    private Label maxWaitTime;
    private Label maxResponseTime;

    public SchedulingController(Parent root, TaskTableController taskTableController, List<SchedulingInfoPack> allInfoPacks)
    {
        this.allInfoPacks = allInfoPacks;
        this.taskTableController = taskTableController;

        avgWaitTime = (Label)root.lookup("#AvgWartezeitLabel");
        avgTaskTime = (Label)root.lookup("#AvgTaskdauerLabel");
        avgResponseTime = (Label)root.lookup("#AvgAntwortzeitLabel");

        maxWaitTime = (Label)root.lookup("#MaxWartezeitLabel");
        maxResponseTime = (Label)root.lookup("#MaxAntwortzeitLabel");

        schedlingChoiceBox = (ChoiceBox)root.lookup("#AlgorithmChooser");

        schedlingChoiceBox.getItems().remove(0, schedlingChoiceBox.getItems().size());
        schedlingChoiceBox.getItems().addAll("Kein Algorithmus ausgewählt", "FIFO", "LIFO", "SJN", "SRTF", "HPF", "RR");
        schedlingChoiceBox.getSelectionModel().selectFirst();

        schedlingChoiceBox.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener()
                {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue)
                    {
                        if(newValue != null)
                        {
                            handleChangedItem((String)newValue);
                        }
                    }
                });
    }

    private void handleChangedItem(String newValue)
    {
        if(newValue.equals("Kein Algorithmus ausgewählt") == false)
        {
            SchedulingInfoPack infoPack = SchedulingInfoPackBuilder.filter(allInfoPacks, newValue);
            setTaskTable(infoPack);
            setAvgMaxTable(infoPack);
        }
        else
        {
            clearAll();
        }
    }

    private void setAvgMaxTable(SchedulingInfoPack infoPack)
    {
        avgWaitTime.setText(Float.toString(infoPack.getAvgWaitTime()));
        avgResponseTime.setText(Float.toString(infoPack.getAvgResponseTime()));
        avgTaskTime.setText(Float.toString(infoPack.getAvgTime()));

        maxResponseTime.setText("" + infoPack.getMaxResponseTime());
        maxWaitTime.setText("" + infoPack.getMaxWaitTime());
    }

    private void setTaskTable(SchedulingInfoPack infoPack)
    {
        for(int i = 0; i < taskTableController.size(); i++)
        {
            taskTableController.setWartezeit(i, infoPack.getAllWaitTimes().get(i));
            taskTableController.setAntwortzeit(i, infoPack.getAllResponseTimes().get(i));
        }
    }

    private void clearAll()
    {
        for(int i = 0; i < taskTableController.size(); i++)
        {
            taskTableController.setWartezeit(i, -1);
            taskTableController.setAntwortzeit(i, -1);
        }

        avgWaitTime.setText("-");
        avgResponseTime.setText("-");
        avgTaskTime.setText("-");

        maxResponseTime.setText("-");
        maxWaitTime.setText("-");
    }
}
