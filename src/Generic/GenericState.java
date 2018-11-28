package Generic;

import java.io.Serializable;

public interface GenericState<T> extends Comparable<GenericState<T>>, Serializable {

    T getState();
    
    double getCost();
    
    Click getClick();

    void setCameFrom(GenericState<T> camefrom);

    GenericState<T> getCameFrom();

    void setCost(double cost);

    void printState();
}
