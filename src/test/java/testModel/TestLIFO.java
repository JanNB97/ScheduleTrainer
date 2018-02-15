package testModel;

import main.ScheduleAlgorithmExecutor;
import model.schedulingAlgorithms.FIFO;
import model.schedulingAlgorithms.LIFO;
import model.schedulingAlgorithms.SchedulingAlgorithm;
import model.tasks.Task;
import model.tasks.TaskGrid;
import model.tasks.TaskSystem;
import org.junit.Assert;
import org.junit.Test;

public class TestLIFO
{
    @Test
    public void test()
    {
        Task A = new Task('A', 2, 1, 1);
        Task B = new Task('B', 0, 3, 1);
        Task C = new Task('C', 4, 4, 3);
        Task D = new Task('D', 2, 2, 2);
        Task E = new Task('E', 1, 4, 1);
        Task F = new Task('F', 0, 2, 1);

        SchedulingAlgorithm lifo = new LIFO(A,B,C,D,E,F);

        TestSchedulingAlgorithm.testAll(lifo, new TaskGrid(
                new TaskSystem(1, F, B),
                new TaskSystem(2, F, E, B),
                new TaskSystem(3, D, A, E, B),
                new TaskSystem(4, D, A, E, B),
                new TaskSystem(5, C, A, E, B),
                new TaskSystem(6, C, A, E, B),
                new TaskSystem(7, C, A, E, B),
                new TaskSystem(8, C, A, E, B),
                new TaskSystem(9, A, E, B),
                new TaskSystem(10, E, B),
                new TaskSystem(11, E, B),
                new TaskSystem(12, E, B),
                new TaskSystem(13, E, B),
                new TaskSystem(14, B),
                new TaskSystem(15, B),
                new TaskSystem(16, B)

        ),13,16,4.5f,2.67f,7.17f);
    }
}
