package main;

import model.tasks.TaskGrid;
import model.tasks.TaskSystem;
import model.schedulingAlgorithms.SchedulingAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAlgorithmExecutor
{
    private SchedulingAlgorithm algorithm;
    private TaskGrid taskGrid;

    public ScheduleAlgorithmExecutor(SchedulingAlgorithm algorithm)
    {
        this.algorithm = algorithm;
        taskGrid = execute();
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
