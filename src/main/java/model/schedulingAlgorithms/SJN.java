package model.schedulingAlgorithms;

import model.tasks.Task;

import java.util.List;

public class SJN extends SchedulingAlgorithm
{
    public SJN(Task... tasks)
    {
        super("Shortest Job Next", false, tasks);
    }

    private void putTaskInQueue(Task task)
    {
        for(int i = 0; i < waiting.size(); i++)
        {
            Task taskInQ = waiting.getTaskQueue().get(i);

            if(taskInQ.getRechenzeit() > task.getRechenzeit())
            {
                waiting.addTask(i, task);
                return;
            }
        }

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
