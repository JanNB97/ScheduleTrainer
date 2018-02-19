package model.tasks;

import java.util.Arrays;
import java.util.List;

public class TaskGrid
{
    List<TaskSystem> allTaskSystems;

    public TaskGrid(TaskSystem...taskSystems)
    {
        allTaskSystems = Arrays.asList(taskSystems);

        for(int i = 0; i < taskSystems.length; i++)
        {
            assert taskSystems[i].getZeitschritt() == i + 1;
        }
    }

    public TaskSystem getTaskSystem(int index)
    {
        return allTaskSystems.get(index);
    }

    public List<TaskSystem> getAllTaskSystems()
    {
        return allTaskSystems;
    }

    public int size()
    {
        return allTaskSystems.size();
    }

    public int getMaxTasksystemSize()
    {
        int max = -1;

        for(TaskSystem taskSystem : allTaskSystems)
        {
            if(taskSystem.getAllTaskIDs().size() > max)
            {
                max = taskSystem.getAllTaskIDs().size();
            }
        }

        return max;
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

            if(getAllTaskSystems().size() != o.getAllTaskSystems().size())
            {
                return false;
            }

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
