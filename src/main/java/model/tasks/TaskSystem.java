package model.tasks;

import model.tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskSystem
{
    private List<Task> waiting;
    private Task processing;

    private List<Character> allTaskIDs;

    int zeitschritt;

    public TaskSystem(int zeitschritt, Task processing, Task...waiting)
    {
        this.waiting = Arrays.asList(waiting);
        this.processing = processing;
        this.zeitschritt = zeitschritt;

        allTaskIDs = new ArrayList<>();

        if(processing != null)
        {
            allTaskIDs.add(processing.getID());
        }

        for(Task task : waiting)
        {
            allTaskIDs.add(task.getID());
        }
    }

    public List<Task> getWaiting()
    {
        return waiting;
    }

    public Task getProcessing()
    {
        return processing;
    }

    public int getZeitschritt()
    {
        return zeitschritt;
    }

    public List<Character> getAllTaskIDs()
    {
        return allTaskIDs;
    }

    //----------------------Overritten methods----------------

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append(zeitschritt + ":\t" + processing.getID() + " |");

        for(Task task : waiting)
        {
            builder.append(" " + task.getID());
        }

        return builder.toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof TaskSystem)
        {
            TaskSystem o = (TaskSystem)obj;

            for(int i = 0; i < waiting.size(); i++)
            {
                if(waiting.get(i).equals(o.waiting.get(i)) == false)
                {
                    return false;
                }
            }

            return processing.equals(o.processing)
                    && zeitschritt == o.zeitschritt;
        }
        else
        {
            return false;
        }
    }
}