package model.tasks;

import java.util.Arrays;
import java.util.List;

public class TaskGrid
{
    List<TaskSystem> allTaskSystems;

    public TaskGrid(TaskSystem...taskSystems)
    {
        allTaskSystems = Arrays.asList(taskSystems);
    }

    public TaskSystem getTaskSystem(int index)
    {
        return allTaskSystems.get(index);
    }

    protected List<TaskSystem> getAllTaskSystems()
    {
        return allTaskSystems;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for(TaskSystem taskSystem : allTaskSystems)
        {
            builder.append(taskSystem.toString() + "\n");
        }

        return builder.toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof TaskGrid)
        {
            TaskGrid o = (TaskGrid)obj;

            for(int i = 0; i < allTaskSystems.size(); i++)
            {
                if(getTaskSystem(i).equals(o.getTaskSystem(i)) == false)
                {
                    return false;
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }
}
