package Algorithms;
import Generic.GenericState;
import Generic.Searchable;
import Generic.Solution;
import Generic.State;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.List;

public class BFS extends CommonSearcher {
	
	@Override
	<T> Solution algoSearch(Searchable<T> problem) {
		
		addToOpenList(problem.getInitState());
        HashSet<GenericState<T>> closedSet = new HashSet<>();

        while (this.openList.size() >0) {
        	GenericState<T> state = popOpenList();
            closedSet.add(state);

            if (problem.isGoal(state)) {
                return this.backTrace(state, problem.getInitState());
            }

            List<GenericState<T>> allStates = problem.getAllStates(state);

            for (GenericState<T> possibleState: allStates) {
                if (!closedSet.contains(possibleState) && !this.openList.contains(possibleState)) {
                    possibleState.setCameFrom(state);
                    possibleState.setCost(state.getCost() + 1);
                    addToOpenList(possibleState);
                }
            }

        }

        return null;
    }	
		/*
		
		State<T> start = (State<T>) problem.getInitState();
		open.add(start);
		int lengthOfRoute = 0;
		
		while(!open.isEmpty()) {
			State<T> current = open.poll();
			close.add(current);
			lengthOfRoute++;
			
			if(problem.isGoal(current)) {
				return BackTrace(current);
			}
			
			ArrayList<State<T>> successors = problem.getAllStates(current);
			for(State<T> s : successors) {
				if(!close.contains(s) && !open.contains(s)) {
					s.setCameFrom(current);
					open.add(s);
			}
		}
		
	}
		return null;
		*/
}

