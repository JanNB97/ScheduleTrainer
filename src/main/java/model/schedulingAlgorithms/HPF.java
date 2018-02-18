package model.schedulingAlgorithms;

import model.tasks.Task;

import java.util.List;

public class HPF extends SchedulingAlgorithm
{
    public HPF(Task... tasks)
    {
        super("HPF", true, tasks);
    }

    private void putTaskInQueue(Task task)
    {
        for(int i = 0; i < waiting.size(); i++)
        {
            Task taskInQ = waiting.getTaskQueue().get(i);

            if(taskInQ.getPrioritaet() < task.getPrioritaet()
                    || taskInQ.getPrioritaet() == task.getPrioritaet()
                    && (taskInQ.getEintrittszeit() > task.getEintrittszeit() || taskInQ.getEintrittszeit() == task.getEintrittszeit() && taskInQ.getID() > task.getID()))
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
        for(Task task : waiting.getTaskQueue())
        {
            if(processing.getPrioritaet() < task.getPrioritaet())
            {
                return true;
            }
        }

        return false;
    }
}
