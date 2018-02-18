package main;

import model.tasks.TaskGrid;

import java.util.List;

public class SchedulingInfoPack
{
    private String name;

    private TaskGrid taskGrid;

    private float avgWaitTime;
    private float avgResponseTime;
    private float avgTime;
    private int maxWaitTime;
    private int maxResponseTime;

    private List<Integer> allResponseTimes;
    private List<Integer> allWaitTimes;

    public SchedulingInfoPack(String name, TaskGrid taskGrid,
                              float avgWaitTime, float avgResponseTime, float avgTime,
                              int maxWaitTime, int maxResponseTime,
                              List<Integer> allResponseTimes, List<Integer> allWaitTimes
    )
    {
        this.name = name;
        this.taskGrid = taskGrid;
        this.avgWaitTime = avgWaitTime;
        this.avgResponseTime = avgResponseTime;
        this.avgTime = avgTime;
        this.maxWaitTime = maxWaitTime;
        this.maxResponseTime = maxResponseTime;
        this.allResponseTimes = allResponseTimes;
        this.allWaitTimes = allWaitTimes;
    }

    public String getName()
    {
        return name;
    }

    public TaskGrid getTaskGrid()
    {
        return taskGrid;
    }

    public float getAvgWaitTime()
    {
        return avgWaitTime;
    }

    public float getAvgResponseTime()
    {
        return avgResponseTime;
    }

    public float getAvgTime()
    {
        return avgTime;
    }

    public int getMaxWaitTime()
    {
        return maxWaitTime;
    }

    public int getMaxResponseTime()
    {
        return maxResponseTime;
    }

    public List<Integer> getAllResponseTimes()
    {
        return allResponseTimes;
    }

    public List<Integer> getAllWaitTimes()
    {
        return allWaitTimes;
    }

    @Override
    public String toString()
    {
        return taskGrid.toString();
    }
}
