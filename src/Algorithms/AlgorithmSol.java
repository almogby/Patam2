package Algorithms;
import Generic.GenericState;
import Generic.Solution;
import Generic.State;

import java.util.List;
import java.util.Collections;
import java.io.Serializable;

public class AlgorithmSol implements Solution, Serializable{

    private GenericState<?> initState;
    private GenericState<?> goalState;
	private List<GenericState<?>> solSteps;

	
    public AlgorithmSol(List<GenericState<?>> SList, GenericState init, GenericState goal){
    	this.solSteps = SList;
    	this.initState = init;
    	this.goalState = goal;
    	
    }

    @Override
	public GenericState<?> getInitState() {
		return this.initState;
	}

    @Override
	public GenericState<?> getGoalState() {
		return this.goalState;
	}
	
    @Override
	public List<GenericState<?>> getSolSteps(){
		return this.solSteps;
	}

	@Override
	public void reverse() {
		Collections.reverse(solSteps);		
	}
	
}

