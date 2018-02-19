package gui.controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.tasks.TaskGrid;
import model.tasks.TaskSystem;

import java.util.ArrayList;
import java.util.List;

public class SchedulingTableController
{
    private TableView schedulingTable;

    public SchedulingTableController(Parent root)
    {
        schedulingTable = (TableView)root.lookup("#ScheduleTable");
    }

    public void setTable(TaskGrid taskGrid)
    {
        clear();

        addColumns(taskGrid);

        addData(taskGrid);
    }

    private void addColumns(TaskGrid taskGrid)
    {
        TableView<ObservableList<String>> tableView = schedulingTable;

        TableColumn<ObservableList<String>, String>[] columns = new TableColumn[taskGrid.size()];

        //Add columns
        for(int i = 0; i < taskGrid.size(); i++)
        {
            columns[i] = new TableColumn<>(Integer.toString(i + 1));
            int finalI = i;
            columns[i].setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().get(finalI)));
            tableView.getColumns().add(columns[i]);
        }
    }

    private void addData(TaskGrid taskGrid)
    {
        int depth = taskGrid.getMaxTasksystemSize();

        for(int i = 0; i < depth; i++)
        {
            ObservableList<String> list = FXCollections.observableArrayList();

            for(TaskSystem taskSystem : taskGrid.getAllTaskSystems())
            {
                if(i == 0)
                {
                    if(taskSystem.getProcessing() != null)
                    {
                        list.add(Character.toString(taskSystem.getProcessing().getID()));
                    }
                    else
                    {
                        list.add("");
                    }
                }
                else
                {
                    if(taskSystem.getWaiting() != null && taskSystem.getWaiting().size() >= i)
                    {
                        list.add(Character.toString(taskSystem.getWaiting().get(i - 1).getID()));
                    }
                    else
                    {
                        list.add("");
                    }
                }
            }
            schedulingTable.getItems().add(list);
        }


        //Add comment with new tasks
        ObservableList<String> list = FXCollections.observableArrayList();

        if(taskGrid.getTaskSystem(0).getAllTaskIDs().size() != 0)
        {
            StringBuilder b = new StringBuilder("+ ");
            for(Character ID : taskGrid.getTaskSystem(0).getAllTaskIDs())
            {
                b.append(ID + " ");
            }
            list.add(b.toString());
        }
        else
        {
            list.add("");
        }


        for(int i = 0; i < taskGrid.getAllTaskSystems().size() - 1; i++)
        {
            List<Character> newIDs = getNewTasks(taskGrid.getTaskSystem(i), taskGrid.getTaskSystem(i + 1));

            if(newIDs != null)
            {
                StringBuilder builder = new StringBuilder("+ ");
                for(Character ID : newIDs)
                {
                    builder.append(ID + " ");
                }

                list.add(builder.toString());
            }
            else
            {
                list.add("");
            }
        }

        list.add("");
        schedulingTable.getItems().add(list);
    }

    private List<Character> getNewTasks(TaskSystem taskSystem1, TaskSystem taskSystem2)
    {
        List<Character> allc1 = taskSystem1.getAllTaskIDs();
        List<Character> allc2 = taskSystem2.getAllTaskIDs();

        List<Character> newIDs = new ArrayList<>();

        for(Character c2 : allc2)
        {
            if(allc1.contains(c2) == false)
            {
                newIDs.add(c2);
            }
        }

        if(newIDs.size() == 0)
        {
            return null;
        }
        else
        {
            return newIDs;
        }
    }

    public void clear()
    {
        schedulingTable.getItems().remove(0, schedulingTable.getItems().size());
        schedulingTable.getColumns().remove(0, schedulingTable.getColumns().size());
    }
}
