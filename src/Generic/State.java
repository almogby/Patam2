package Generic;

import java.util.Objects;
import java.io.Serializable;


public class State<T> implements GenericState<T>{
	
	protected T state;
    protected GenericState<T> cameFrom;
    double cost = 0;
    private Click click;
	protected int hashC;
	
	public State() {}
	
	public State(T state) {
		this.state = state;
		this.cameFrom = null;
	}
	
    public State(T state, Click click) {
        this.state = state;
        this.click = click;
        this.hashC = hashCode();
    }
	
	public State(T otherState, GenericState<T> cameFrom, double cost) {
		this.state = otherState;
		this.cameFrom = cameFrom;
		this.cost = cost;
	}
	
	@Override
	public T getState() {
		return this.state;
	}
	
	public void setState(T otherState) {
		this.state = otherState;
	}
	
	@Override
	public GenericState<T> getCameFrom(){
		return this.cameFrom;
	}
	
	@Override
	public void setCameFrom(GenericState<T> state) {
		this.cameFrom = state;
	}
	
	@Override
    public double getCost() {
        return this.cost;
    }
    
	@Override
    public void setCost(double cost) {
        this.cost = cost;
    }
    
	@Override
    public Click getClick() {
        return this.click;
    }
   
    @Override
    public void printState() {}

	@Override
	public int compareTo(GenericState<T> o) {
        return Double.compare(this.cost, o.getCost());
	}

}
