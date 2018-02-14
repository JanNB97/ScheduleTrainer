package testModel;

import main.ScheduleAlgorithmExecutor;
import model.tasks.Task;
import model.schedulingAlgorithms.FIFO;
import model.schedulingAlgorithms.SchedulingAlgorithm;
import model.tasks.TaskGrid;
import model.tasks.TaskSystem;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class testFIFO
{
    @Test
    public void gridTest()
    {
        Task A = new Task('A', 1, 5, 1);
        Task B = new Task('B', 0, 3, 1);
        Task C = new Task('C', 3, 3, 3);
        Task D = new Task('D', 2, 3, 2);
        Task E = new Task('E', 2, 1, 1);
        Task F = new Task('F', 0, 1, 1);

        SchedulingAlgorithm fifo = new FIFO(A,B,C,D,E,F);
        ScheduleAlgorithmExecutor executor = new ScheduleAlgorithmExecutor(fifo);

        Assert.assertEquals(new TaskGrid(
                new TaskSystem(1, B, F),
                new TaskSystem(2, B, F, A),
                new TaskSystem(3, B, F, A, D, E),
                new TaskSystem(4, F, A, D, E, C),
                new TaskSystem(5, A, D, E, C),
                new TaskSystem(6, A, D, E, C),
                new TaskSystem(7, A, D, E, C),
                new TaskSystem(8, A, D, E, C),
                new TaskSystem(9, A, D, E, C),
                new TaskSystem(10, D, E, C),
                new TaskSystem(11, D, E, C),
                new TaskSystem(12, D, E, C),
                new TaskSystem(13, E, C),
                new TaskSystem(14, C),
                new TaskSystem(15, C),
                new TaskSystem(16, C)),

                executor.getTaskGrid());

        Assert.assertEquals(10, executor.getMaxWaitTime());
        Assert.assertEquals(13, executor.getMaxResponseTime());

        Assert.assertEquals(roundTwo(5.5f), roundTwo(executor.getAverageWaitTime()));
        Assert.assertEquals(roundTwo(2.67f), roundTwo(executor.getAverageTime()));
        Assert.assertEquals(roundTwo(8.17f), roundTwo(executor.getAverageResponseTime()));
    }

    public int roundTwo(float f)
    {
        return Math.round(f * 100);
    }
}
