package model.schedulingAlgorithms;

import model.tasks.Task;

import java.util.List;

public class LIFO extends SchedulingAlgorithm
{
    public LIFO(Task... tasks)
    {
        super("Last in, first out", false, tasks);
    }

    private void putTaskInQueue(Task task)
    {
        waiting.addTask(0, task);
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
