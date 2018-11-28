package Algorithms;
import Generic.State;
import Generic.GenericState;
import Generic.Searchable;
import Generic.Solution;

import java.util.List;
import java.util.Random;

public class HillClimbing extends CommonSearcher {

	private long timeLeft;
    private Heuristic heuristic;
    
    public interface Heuristic {
    	
        double power();
        double calculate(GenericState state);
        boolean compare(double left, double right);
          
    }
    
    public HillClimbing(long timeLeft, Heuristic heuristic) {
        this.timeLeft = timeLeft;
        this.heuristic = heuristic;
    }

    @Override
	<T> Solution algoSearch(Searchable<T> problem) {
    	
        Generic.GenericState<T> n = problem.getInitState();
        long timeZero = System.currentTimeMillis();
        
       while (System.currentTimeMillis() - timeZero < this.timeLeft) {
            List<GenericState<T>> neighbors = problem.getAllStates(n);

            if (problem.isGoal(n)) {
                return backTrace(n,problem.getInitState());
            }
            if (neighbors.size() > 0)
            {
                if (Math.random() < 0.7)
                {
                    double grade =  this.heuristic.power();
                    
                    for (Generic.GenericState<T> click : neighbors) {
                        double g = this.heuristic.calculate(click);
                        
                        if (this.heuristic.compare(g , grade))
                        {
                            grade = g;
                            n = click;
                        }
                    }
                }
                else
                    {
                        n = neighbors.get(new Random().nextInt(neighbors.size()));
                    }
            }
        }
        return null;
    }
}
