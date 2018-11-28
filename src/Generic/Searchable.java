package Generic;

import java.util.ArrayList;
import java.util.List;

public interface Searchable<T> {

	GenericState<T> getInitState();
	boolean isGoal(GenericState<T> state);
	List<GenericState<T>> getAllStates(GenericState<T> state);

}
