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

        //TODO
        /*
        TestSchedulingAlgorithm.testAll(lifo, new TaskGrid(
                new TaskSystem()
        ));*/
    }
}
