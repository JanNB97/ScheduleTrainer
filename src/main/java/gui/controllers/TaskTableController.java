package gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskTableController
{
    private TableView taskTable;

    public TaskTableController(Parent root)
    {
        taskTable = (TableView)root.lookup("#TaskTable");

        TableColumn IDColumn = new TableColumn("Name");
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn eintrittsC = new TableColumn("Eintrittszeit");
        eintrittsC.setCellValueFactory(new PropertyValueFactory<>("eintrittszeit"));

        TableColumn rechenzeitC = new TableColumn("Rechenzeit");
        rechenzeitC.setCellValueFactory(new PropertyValueFactory<>("rechenzeit"));

        TableColumn prioritaetC = new TableColumn("Prioritaet");
        prioritaetC.setCellValueFactory(new PropertyValueFactory<>("prioritaet"));

        TableColumn wartezeitC = new TableColumn("W");
        wartezeitC.setCellValueFactory(new PropertyValueFactory<>("wartezeit"));

        TableColumn antwortzeitC = new TableColumn("R");
        antwortzeitC.setCellValueFactory(new PropertyValueFactory<>("antwortzeit"));

        taskTable.getColumns().addAll(IDColumn, eintrittsC, rechenzeitC, prioritaetC, wartezeitC, antwortzeitC);
    }

    public void clear()
    {
        taskTable.getItems().remove(0, taskTable.getItems().size());
    }

    public void addColumn(Task task)
    {
        taskTable.getItems().add(task);
    }

    public void setWartezeit(int index, int wartezeit)
    {
        Task task = (Task)taskTable.getItems().get(index);
        task.setWartezeit(wartezeit);
        taskTable.refresh();
    }

    public void setAntwortzeit(int index, int antwortzeit)
    {
        Task task = (Task)taskTable.getItems().get(index);
        task.setAntwortzeit(antwortzeit);
        taskTable.refresh();
    }

    public int size()
    {
        return taskTable.getItems().size();
    }
}
