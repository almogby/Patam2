package PG;
import Generic.GenericState;
import Generic.Solution;
import Generic.State;

import java.awt.*;
import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Algorithms.AlgorithmSol;

public class PGSolution implements Serializable {
	
	private ArrayList<PGClick> solListToClient = new ArrayList<>();
	
	
    public ArrayList<PGClick> getSolListToClient() {
        return this.solListToClient;
    }

    public void printClicks(){
    	
        for (PGClick click:solListToClient) 
        {
            System.out.println(click);
        }
        
    }
	
	public PGSolution(Solution sol) {
		
        List<GenericState<?>> statesList = sol.getSolSteps();

        List<PGClick> justClicks = new ArrayList<>();
        GenericState<?> current = statesList.get(statesList.size() - 1);
        
        while(current.getCameFrom() != null) {
        	justClicks.add((PGClick)current.getClick());
            current = current.getCameFrom();	}
        
        Map<Point, Long> collect = justClicks.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(PGClick::getP, Collectors.counting()));
        for (Point point : collect.keySet()) {
            this.solListToClient.add(new PGClick(point, collect.get(point).intValue()%4));
        }
		
	}
}
