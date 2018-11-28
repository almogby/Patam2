package PG;
import Generic.Click;
import java.awt.Point;
import Generic.Click;

public class PGClick extends Click{

	private Point p;
	private int NumOfClicks;
	
    public PGClick() {
        this(new Point(), 0);
    }
    
    public PGClick(Point point, int clicks) {
        this.p = point;
        this.NumOfClicks = clicks;
    }
    
	public PGClick(int row, int col, int clicks) {
        this(new Point(row, col), clicks);
    }

    public Point getP() {
        return this.p;
    }

    public int getNumOfClicks() {
        return this.NumOfClicks;
    }

    public void setNumOfClicks(int clicks) {
        this.NumOfClicks = clicks;
    }

    @Override
    public String clickToString() {
    	
        StringBuilder StrBuilder = new StringBuilder();
        
        StrBuilder.append(this.p.x);
        StrBuilder.append(",");
        StrBuilder.append(this.p.y);
        StrBuilder.append(",");
        StrBuilder.append(this.NumOfClicks);
        return StrBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) 
        	return false;
        return this.p == ((PGClick) o).p;
    }
	
}
