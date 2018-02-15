package testModel;

import main.ScheduleAlgorithmExecutor;
import model.schedulingAlgorithms.HPF;
import model.schedulingAlgorithms.SRTF;
import model.schedulingAlgorithms.SchedulingAlgorithm;
import model.tasks.Task;
import model.tasks.TaskGrid;
import model.tasks.TaskSystem;
import org.junit.Test;

public class testSRTF
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

        SchedulingAlgorithm srtf = new SRTF(A, B, C, D, E, F);

        TestSchedulingAlgorithm.testAll(srtf, new TaskGrid(
                new TaskSystem(1, F, B),
                new TaskSystem(2, F, B, E),
                new TaskSystem(3, A, D, B, E),
                new TaskSystem(4, D, B, E),
                new TaskSystem(5, D, B, E, C),
                new TaskSystem(6, B, E, C),
                new TaskSystem(7, B, E, C),
                new TaskSystem(8, B, E, C),
                new TaskSystem(9, E, C),
                new TaskSystem(10, E, C),
                new TaskSystem(11, E, C),
                new TaskSystem(12, E, C),
                new TaskSystem(13, C),
                new TaskSystem(14, C),
                new TaskSystem(15, C),
                new TaskSystem(16, C)
        ), 8, 12, 3.5f, 2.67f, 6.17f);
    }
}
