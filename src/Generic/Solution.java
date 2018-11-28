package Generic;

import java.util.ArrayList;
import java.util.List;

public interface Solution {
	
	GenericState<?> getInitState();

	GenericState<?> getGoalState();
    
	List getSolSteps();
	
    void reverse();


	
}

