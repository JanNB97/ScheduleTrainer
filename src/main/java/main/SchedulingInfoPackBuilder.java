package main;

import model.schedulingAlgorithms.*;
import model.tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SchedulingInfoPackBuilder
{
    public static List<SchedulingInfoPack> getSchedulingInfoPacks(List<Task> tasks)
    {
        Task[] taskArray = tasks.toArray(new Task[tasks.size()]);

        List<SchedulingInfoPack> allSIP = new ArrayList<>();

        allSIP.add(getSchedulingInfoPack(new FIFO(taskArray)));
        allSIP.add(getSchedulingInfoPack(new LIFO(taskArray)));
        allSIP.add(getSchedulingInfoPack(new SJN(taskArray)));
        allSIP.add(getSchedulingInfoPack(new SRTF(taskArray)));
        allSIP.add(getSchedulingInfoPack(new HPF(taskArray)));
        allSIP.add(getSchedulingInfoPack(new RR(2, taskArray)));

        return allSIP;
    }

    private static SchedulingInfoPack getSchedulingInfoPack(SchedulingAlgorithm algorithm)
    {
        ScheduleAlgorithmExecutor executor = new ScheduleAlgorithmExecutor(algorithm);

        return new SchedulingInfoPack(algorithm.getName(), executor.getTaskGrid(),
                executor.getAverageWaitTime(), executor.getAverageResponseTime(), executor.getAverageTime(), executor.getMaxWaitTime()
                , executor.getMaxResponseTime(),
                executor.getAllResponseTimes(), executor.getAllWaitTimes());
    }

    public static SchedulingInfoPack filter(List<SchedulingInfoPack> allInfoPacks, String algoName)
    {
        for(SchedulingInfoPack infoPack : allInfoPacks)
        {
            if(infoPack.getName().equals(algoName))
            {
                return infoPack;
            }
        }

        return null;
    }
}
