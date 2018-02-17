package main;

import model.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class TaskGenerator
{
    public static List<Task> getTasks(int minNoOfTasks, int maxNoOfTasks,
            int maxEintrittszeit, int maxRechenzeit, int maxPrioritaet)
    {
        if(minNoOfTasks > maxNoOfTasks)
        {
            Logger.getGlobal().severe("Argument Error - minNoOfTasks > maxNoOfTasks");
            return null;
        }

        List<Task> tasks = new ArrayList<>();
        Random rand = new Random();

        int noOfTasks = rand.nextInt(maxNoOfTasks - minNoOfTasks + 1) + minNoOfTasks;

        for(int i = 0; i < noOfTasks; i++)
        {
            int eintrittszeit = rand.nextInt(maxEintrittszeit + 1);
            int rechenzeit = rand.nextInt(maxRechenzeit + 1);
            int prioritaet = rand.nextInt(maxPrioritaet + 1);

           tasks.add(new Task((char)(i + 65), eintrittszeit, rechenzeit, prioritaet));
        }

        return tasks;
    }
}
