package model.tasks;

public class Task
{
    private char ID;

    private int eintrittszeit;
    private int rechenzeit;
    private int prioritaet;

    private int remainingTime;

    public Task(char ID, int eintrittszeit, int rechenzeit, int prioritaet)
    {
        this.ID = ID;
        this.eintrittszeit = eintrittszeit;
        this.rechenzeit = rechenzeit;
        this.prioritaet = prioritaet;

        remainingTime = rechenzeit;
    }

    public void calculates(int time)
    {
        remainingTime -= time;
    }

    public void reset()
    {
        remainingTime = rechenzeit;
    }

    public boolean isFinished()
    {
        return remainingTime == 0;
    }

    //-------------Overritten----------------------

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Task)
        {
            Task o = (Task)obj;

            return ID == o.ID;
        }
        else {
            return false;
        }
    }

    //--------------Getter-------------------------

    public int getRemainingTime()
    {
        return remainingTime;
    }

    public char getID()
    {
        return ID;
    }

    public int getEintrittszeit()
    {
        return eintrittszeit;
    }

    public int getRechenzeit()
    {
        return rechenzeit;
    }

    public int getPrioritaet()
    {
        return prioritaet;
    }
}
