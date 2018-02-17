package testModel;

import main.ScheduleAlgorithmExecutor;
import model.schedulingAlgorithms.RR;
import model.schedulingAlgorithms.SchedulingAlgorithm;
import model.tasks.Task;
import model.tasks.TaskGrid;
import model.tasks.TaskSystem;
import org.junit.Test;

public class testRR
{
    @Test
    public void HA3()
    {
        Task A = new Task('A', 2, 1, 1);
        Task B = new Task('B', 0, 3, 1);
        Task C = new Task('C', 4, 4, 3);
        Task D = new Task('D', 2, 2, 2);
        Task E = new Task('E', 1, 4, 1);
        Task F = new Task('F', 0, 2, 1);

        SchedulingAlgorithm rr2 = new RR(2, A, B, C, D, E, F);

        TestSchedulingAlgorithm.testAll(rr2, new TaskGrid(
                new TaskSystem(1, B, F),
                new TaskSystem(2, B, F, E),
                new TaskSystem(3, F, E, A, D, B),
                new TaskSystem(4, F, E, A, D, B),
                new TaskSystem(5, E, A, D, B, C),
                new TaskSystem(6, E, A, D, B, C),
                new TaskSystem(7, A, D, B, C, E),
                new TaskSystem(8, D, B, C, E),
                new TaskSystem(9, D, B, C, E),
                new TaskSystem(10, B, C, E),
                new TaskSystem(11, C, E),
                new TaskSystem(12, C, E),
                new TaskSystem(13, E, C),
                new TaskSystem(14, E, C),
                new TaskSystem(15, C),
                new TaskSystem(16, C)

        ), 9, 13, 5.83f, 2.67f, 8.5f);
    }

    @Test
    public void Uebung3()
    {
        Task A = new Task('A', 1, 5, 1);
        Task B = new Task('B', 0, 3, 1);
        Task C = new Task('C', 3, 3, 3);
        Task D = new Task('D', 2, 3, 2);
        Task E = new Task('E', 2, 1, 1);
        Task F = new Task('F', 0, 1, 1);

        SchedulingAlgorithm rr1 = new RR(1, A, B, C, D, E, F);

        ScheduleAlgorithmExecutor executor = new ScheduleAlgorithmExecutor(rr1);
        //System.out.println(executor.toString());
    }
}
