package testModel;

import main.ScheduleAlgorithmExecutor;
import model.schedulingAlgorithms.FIFO;
import model.schedulingAlgorithms.SchedulingAlgorithm;
import model.tasks.Task;
import model.tasks.TaskGrid;
import model.tasks.TaskSystem;
import org.junit.Assert;

public class TestSchedulingAlgorithm
{
    public static void testAll(SchedulingAlgorithm algorithm, TaskGrid expectedGrid,
                                int maxWaitTime, int maxResponseTime,
                                float averageWaitTime, float averageTime, float averageResponseTime)
    {
        ScheduleAlgorithmExecutor executor = new ScheduleAlgorithmExecutor(algorithm);

        Assert.assertEquals(expectedGrid, executor.getTaskGrid());

        testCalculations(executor, maxWaitTime, maxResponseTime, averageWaitTime, averageTime, averageResponseTime);
    }

    public static void testCalculations(ScheduleAlgorithmExecutor executor,
            int maxWaitTime, int maxResponseTime,
            float averageWaitTime, float averageTime, float averageResponseTime)
    {
        Assert.assertEquals(roundTwo(averageWaitTime), roundTwo(executor.getAverageWaitTime()));
        Assert.assertEquals(roundTwo(averageTime), roundTwo(executor.getAverageTime()));
        Assert.assertEquals(roundTwo(averageResponseTime), roundTwo(executor.getAverageResponseTime()));

        Assert.assertEquals(maxWaitTime, executor.getMaxWaitTime());
        Assert.assertEquals(maxResponseTime, executor.getMaxResponseTime());
    }


    private static int roundTwo(float f)
    {
        return Math.round(f * 100);
    }
}
