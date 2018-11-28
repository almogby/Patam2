package Generic;

import java.io.Serializable;

public abstract class Click implements Serializable{

    @Override
    public String toString()
    {
        return clickToString();
    }
    
    public abstract String clickToString();
    
}

