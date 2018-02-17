package gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.tasks.Task;

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

        taskTable.getColumns().addAll(IDColumn, eintrittsC, rechenzeitC, prioritaetC);
    }

    public void clear()
    {
        taskTable.getItems().remove(0, taskTable.getItems().size());
    }

    public void addColumn(Task task)
    {
        taskTable.getItems().add(task);
    }
}
