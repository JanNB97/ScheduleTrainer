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

        for(Task task : allTasks)
        {
            task.reset();
        }
    }

    public void doZeitschritt()
    {
        //FIRST NEW TASKS
        putTasksInQueue(getNewTasks(zeitSchritt));

        //SECOND INTERRUPTED TASKS
        if(processing != null && processing.isFinished() == false
                && withInterupts && shouldInterrupt())
        {
            putTasksInQueue(Arrays.asList(processing));
            processing = null;
        }

        //THIRD NEW PROCESSING TASK
        if(processing == null || processing.isFinished())
        {
            putNewProcessingTask();
        }

        //FORTH CALCULATING
        if(processing != null)
        {
            processing.calculates(1);
        }

        zeitSchritt++;
    }

    private void putNewProcessingTask()
    {
        processing = waiting.getNewProcessingTask();
        newProcessingTask();
    }

    protected void newProcessingTask()
    {

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
