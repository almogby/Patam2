package Server;

public abstract class PriorityJobQueue implements Comparable<PriorityJobQueue>, Runnable
{
    private int priority;
    
    public PriorityJobQueue(int priority) {
        this.priority = priority;
    }
    
    @Override
    public int compareTo(final PriorityJobQueue runnable) {
        return this.priority - runnable.priority;
    }
}