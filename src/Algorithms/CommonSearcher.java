package Algorithms;
import Generic.State;
import Generic.GenericState;
import Generic.Searchable;
import Generic.Searcher;
import Generic.Solution;

import java.util.PriorityQueue;
import java.util.ArrayList;

 abstract class CommonSearcher implements Searcher{

	PriorityQueue<GenericState<?>> openList = new PriorityQueue<>();
    private int evaluatedNodes = 0;
    
    void addToNodes() {
        this.evaluatedNodes++;
    }
  
    <T> GenericState<T> popOpenList() {
        this.addToNodes();
        return (GenericState<T>)this.openList.poll();
    }

    void addToOpenList(GenericState<?> state) {
        this.openList.add(state);
    }
    
    abstract <T> Solution algoSearch(Searchable<T> searchable);

    @Override
    public Solution searcher(Searchable searchable) {
        long startTime = System.currentTimeMillis();

        Solution solution = this.algoSearch(searchable);

        long stopTime = System.currentTimeMillis();
        
        double elapsedTime = stopTime - startTime;
        
        return solution;
    }

    Solution backTrace(GenericState<?> state, GenericState<?> initialState) {
        ArrayList<GenericState<?>> trace = new ArrayList<>();
        GenericState<?> currentState = state;
        trace.add(currentState);
        while (currentState.getCameFrom() != null) {
        	trace.add(currentState.getCameFrom());
        	currentState = currentState.getCameFrom();
        }
        Solution solution = new AlgorithmSol(trace, initialState, state);
        solution.reverse();
        return solution;
    }



    
    
    
    
}
