package model.schedulingAlgorithms;

import model.tasks.Task;
import java.util.List;

public class FIFO extends SchedulingAlgorithm
{
    public FIFO(Task... tasks)
    {
        super("FIFO", false, tasks);
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
        return false;
    }
}
