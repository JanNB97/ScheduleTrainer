package testModel;

import main.ScheduleAlgorithmExecutor;
import model.schedulingAlgorithms.HPF;
import model.schedulingAlgorithms.SchedulingAlgorithm;
import model.tasks.Task;
import model.tasks.TaskGrid;
import model.tasks.TaskSystem;
import org.junit.Test;

public class TestHPF
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

        SchedulingAlgorithm hpf = new HPF(A, B, C, D, E, F);

        TestSchedulingAlgorithm.testAll(hpf, new TaskGrid(
                new TaskSystem(1, B, F),
                new TaskSystem(2, B, F, E),
                new TaskSystem(3, D, B, F, E, A),
                new TaskSystem(4, D, B, F, E, A),
                new TaskSystem(5, C, B, F, E, A),
                new TaskSystem(6, C, B, F, E, A),
                new TaskSystem(7, C, B, F, E, A),
                new TaskSystem(8, C, B, F, E, A),
                new TaskSystem(9, B, F, E, A),
                new TaskSystem(10, F, E, A),
                new TaskSystem(11, F, E, A),
                new TaskSystem(12, E, A),
                new TaskSystem(13, E, A),
                new TaskSystem(14, E, A),
                new TaskSystem(15, E, A),
                new TaskSystem(16, A)
        ), 13, 14, 6.33f, 2.67f, 9);
    }
}
