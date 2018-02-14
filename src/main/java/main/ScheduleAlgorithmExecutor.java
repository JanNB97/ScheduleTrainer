package main;

import model.tasks.Task;
import model.tasks.TaskGrid;
import model.tasks.TaskSystem;
import model.schedulingAlgorithms.SchedulingAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAlgorithmExecutor
{
    private SchedulingAlgorithm algorithm;
    private TaskGrid taskGrid;
    private List<Task> allTasks;

    public ScheduleAlgorithmExecutor(SchedulingAlgorithm algorithm)
    {
        this.algorithm = algorithm;
        taskGrid = execute();
        this.allTasks = algorithm.getAllTasks();
    }

    private TaskGrid execute()
    {
        List<TaskSystem> allTaskSystems = new ArrayList<>();

        while(algorithm.isFinished() == false)
        {
            algorithm.doZeitschritt();
            allTaskSystems.add(algorithm.getTaskSystem());
        }

        return new TaskGrid((TaskSystem[])allTaskSystems.toArray(new TaskSystem[allTaskSystems.size()]));
    }

    //-------------------Average---------------------------

    public float getAverageWaitTime()
    {
        return getAverageResponseTime() - getAverageTime();
    }

    public float getAverageTime()
    {
        int tGesamt = 0;

        for(Task task : allTasks)
        {
            tGesamt += task.getRechenzeit();
        }

        return (float)tGesamt / allTasks.size();
    }

    public float getAverageResponseTime()
    {
        int rGes = 0;

        for(Task task : allTasks)
        {
            rGes += getResponseTime(task);
        }

        return (float)rGes / allTasks.size();
    }

    //--------------------One task---------------------------

    public int getWaitTime(Task task)
    {
        return getResponseTime(task) - task.getRechenzeit();
    }

    private int getResponseTime(Task task)
    {
        return getLastApperance(task) - task.getEintrittszeit();
    }

    private int getLastApperance(Task task)
    {
        char ID = task.getID();

        for(int i = task.getEintrittszeit(); i < taskGrid.getAllTaskSystems().size(); i++)
        {
            TaskSystem taskSystem = taskGrid.getTaskSystem(i);

            if(taskSystem.getAllTaskIDs().contains(ID) == false)
            {
                return i;
            }

            if(i == taskGrid.getAllTaskSystems().size() - 1)
            {
                return i + 1;
            }
        }

        //Unreachable
        return -1;
    }

    //-----------------Max-----------------------------------

    public int getMaxWaitTime()
    {
        int wMax = -1;

        for(Task task : allTasks)
        {
            int w = getWaitTime(task);

            if(w > wMax)
            {
                wMax = w;
            }
        }

        return wMax;
    }

    public int getMaxResponseTime()
    {
        int rMax = -1;

        for(Task task : allTasks)
        {
            int r = getResponseTime(task);

            if(r > rMax)
            {
                rMax = r;
            }
        }

        return rMax;
    }





    //------------------------Getter------------------------

    public TaskGrid getTaskGrid()
    {
        return taskGrid;
    }

    //-------------------------Overritten-------------------

    @Override
    public String toString()
    {
        return taskGrid.toString();
    }
}
