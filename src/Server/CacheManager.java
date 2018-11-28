package Server;

import Generic.Solution;

public interface CacheManager {
	
	public void save(String problem, Solution solution);
	public Solution load(String problem);

}
