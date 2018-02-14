package model.schedulingAlgorithms;

import model.tasks.Task;
import model.tasks.TaskQueue;
import model.tasks.TaskSystem;

import java.util.*;

public abstract class SchedulingAlgorithm
{
    protected boolean withInterupts;

    protected String name;
    protected List<Task> allTasks;

    protected TaskQueue waiting = new TaskQueue();
    protected Task processing;

    protected int zeitSchritt;


    public SchedulingAlgorithm(String name, boolean withInterupts, Task...tasks)
    {
        this.name = name;
        this.withInterupts = withInterupts;
        allTasks = Arrays.asList(tasks);
    }

    public void doZeitschritt()
    {
        putTasksInQueue(getNewTasks(zeitSchritt));

        if(processing == null)
        {
            //No task is processing
            putNewProcessingTask();
        }
        else
        {
            //Task is processing
            if(processing.isFinished())
            {
                putNewProcessingTask();
            }
            else
            {
                if(withInterupts && shouldInterrupt())
                {
                    putTasksInQueue(Arrays.asList(processing));
                    putNewProcessingTask();
                }
            }
        }

        if(processing != null)
        {
            processing.calculates(1);
        }

        zeitSchritt++;
    }

    private void putNewProcessingTask()
    {
        processing = waiting.getNewProcessingTask();
    }

    private List<Task> getNewTasks(int zeitSchritt)
    {
        ArrayList<Task> tasks = new ArrayList<>();

        for(Task task : allTasks)
        {
            if(task.getEintrittszeit() == zeitSchritt)
            {
                tasks.add(task);
            }
        }

        return tasks;
    }

    public boolean isFinished()
    {
        for(Task task : allTasks)
        {
            if(task.isFinished() == false)
            {
                return false;
            }
        }

        return true;
    }

    abstract void putTasksInQueue(List<Task> tasks);

    abstract boolean shouldInterrupt();

    //-----------------------Getter---------------------------


    public List<Task> getAllTasks()
    {
        return allTasks;
    }

    public TaskSystem getTaskSystem()
    {
        return new TaskSystem(zeitSchritt, processing, (Task[])waiting.getTaskQueue().toArray(new Task[waiting.getTaskQueue().size()]));
    }

    public String getName()
    {
        return name;
    }
}
