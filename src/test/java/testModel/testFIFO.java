package testModel;

import model.tasks.Task;
import model.schedulingAlgorithms.FIFO;
import model.schedulingAlgorithms.SchedulingAlgorithm;
import model.tasks.TaskGrid;
import model.tasks.TaskSystem;
import org.junit.Assert;
import org.junit.Test;

public class TestFIFO
{
    @Test
    public void test()
    {
        Task A = new Task('A', 1, 5, 1);
        Task B = new Task('B', 0, 3, 1);
        Task C = new Task('C', 3, 3, 3);
        Task D = new Task('D', 2, 3, 2);
        Task E = new Task('E', 2, 1, 1);
        Task F = new Task('F', 0, 1, 1);

        SchedulingAlgorithm fifo = new FIFO(A,B,C,D,E,F);

        TestSchedulingAlgorithm.testAll(fifo, new TaskGrid(
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
                10, 13,
            5.5f, 2.67f, 8.17f);
    }
}
