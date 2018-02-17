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

        TaskGrid actualGrid = executor.getTaskGrid();

        Assert.assertEquals("Expected:\n" + expectedGrid.toString() + "\n\nActual:\n" + actualGrid.toString(),
                expectedGrid, actualGrid);

        testCalculations(executor, maxWaitTime, maxResponseTime, averageWaitTime, averageTime, averageResponseTime);

        System.out.println("All response times:\n\t" + executor.getAllResponseTimes()
                + "\n\nAll waiting times:\n\t" + executor.getAllWaitTimes());
    }

    public static void testCalculations(ScheduleAlgorithmExecutor executor,
            int maxWaitTime, int maxResponseTime,
            float averageWaitTime, float averageTime, float averageResponseTime)
    {
        Assert.assertEquals(maxWaitTime, executor.getMaxWaitTime());
        Assert.assertEquals(maxResponseTime, executor.getMaxResponseTime());

        Assert.assertEquals(roundTwo(averageWaitTime), roundTwo(executor.getAverageWaitTime()));
        Assert.assertEquals(roundTwo(averageTime), roundTwo(executor.getAverageTime()));
        Assert.assertEquals(roundTwo(averageResponseTime), roundTwo(executor.getAverageResponseTime()));
    }


    private static int roundTwo(float f)
    {
        return Math.round(f * 100);
    }
}
