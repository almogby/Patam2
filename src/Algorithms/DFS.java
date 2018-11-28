package Algorithms;

import java.util.HashSet;
import java.util.Stack;

import Generic.GenericState;
import Generic.Searchable;
import Generic.Solution;
import Generic.State;

import java.util.List;

public class DFS extends CommonSearcher{

	 @Override
    <T> Solution algoSearch(Searchable<T>  problem) {
		 
        Stack<GenericState<T>> stack = new Stack<>();
        HashSet<GenericState> closedSet = new HashSet<>();

        stack.add(problem.getInitState());

        while (stack.size() > 0) {
        	GenericState<T> state = stack.pop();
            this.addToNodes();
            closedSet.add(state);

            if (problem.isGoal(state)) {
                return backTrace(state, problem.getInitState());
            }

            List<GenericState<T>> allStates = problem.getAllStates(state);

            for (GenericState<T> possibleState : allStates) {
                if (!closedSet.contains(possibleState) && !stack.contains(possibleState)) {
                    possibleState.setCameFrom(state);
                    possibleState.setCost(state.getCost() + 1);
                    stack.add(possibleState);
                }
            }
        }

        return null;
    }
	
}
