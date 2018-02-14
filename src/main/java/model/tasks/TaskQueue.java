package model.tasks;

import model.tasks.Task;

import java.util.*;

public class TaskQueue
{
    private ArrayList<Task> taskQueue = new ArrayList<>();

    public TaskQueue(Task...tasks)
    {
        for(Task task : tasks)
        {
            taskQueue.add(task);
        }
    }

    public Task getNewProcessingTask()
    {
        return taskQueue.remove(0);
    }

    public void addTask(int index, Task task)
    {
        taskQueue.add(index, task);
    }

    public void addTask(Task task)
    {
        taskQueue.add(task);
    }

    public List<Task> getTaskQueue()
    {
        return taskQueue;
    }

    public int size()
    {
        return taskQueue.size();
    }
}
