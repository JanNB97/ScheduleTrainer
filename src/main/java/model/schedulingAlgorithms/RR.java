package model.schedulingAlgorithms;

import model.tasks.Task;

import java.util.List;

public class RR extends SchedulingAlgorithm
{
    private int zeitscheibe;
    private int robinStartTime = 0;

    public RR(int zeitscheibe, Task... tasks)
    {
        super("RR", true, tasks);

        this.zeitscheibe = zeitscheibe;
    }


    private void putTaskInQueue(Task task)
    {
        waiting.addTask(task);
    }

    @Override
    void putTasksInQueue(List<Task> tasks)
    {
        tasks.sort((s, t) -> {
            return s.getID() - t.getID();
        });

        for(Task task : tasks)
        {
            putTaskInQueue(task);
        }
    }

    @Override
    boolean shouldInterrupt()
    {
        int robinTime = zeitSchritt - robinStartTime;

        return robinTime == zeitscheibe;
    }

    @Override
    protected void newProcessingTask()
    {
        robinStartTime = zeitSchritt;
    }
}
