package Algorithms;

import Generic.GenericState;
import Generic.Searchable;
import Generic.Solution;
import Generic.State;

import java.util.List;
import java.util.HashSet;

public class BestFS extends CommonSearcher{
	
		@Override
		<T> Solution algoSearch(Searchable<T> problem) {
			
		this.addToOpenList(problem.getInitState());
	    HashSet<GenericState> closedSet = new HashSet<>();
	
	    while (this.openList.size() > 0) {
	    	GenericState<T> state = this.popOpenList();
	        closedSet.add(state);
	
	        if (problem.isGoal(state)) {
	            return this.backTrace(state, problem.getInitState());
	        }
	
	        List<GenericState<T>> allStates = problem.getAllStates(state);
	        
	        for (GenericState<T> possibleState : allStates) {
	            if (!closedSet.contains(possibleState)) {
	            	possibleState.setCameFrom(state);
	            	possibleState.setCost(state.getCost()+1);
	            	if (!openList.contains(possibleState)) {
	            		addToOpenList(possibleState);
	            	}
	            	else if (openList.removeIf(e -> e.equals(possibleState) && e.getCost() > possibleState.getCost())) {
	            		addToOpenList(possibleState);
	            	}
	
	            }
	        }
		}
	
	    return null;	
	}

}

