package freezingAgent;

import sim.engine.SimState;
import sim.field.grid.SparseGrid2D;

public class Environment extends SimState {
	public Environment(long seed) {
		super(seed);
		// TODO Auto-generated constructor stub
	}
	// new datamember here:
	// public boolean bounded = true;
	public boolean oneCellPerAgent;
	public boolean broad_or_narrow;
	public int n = 50;
	public int gridWidth = 50;
	public int gridHeight = 50;
	public SparseGrid2D space;
	public boolean isBroad_or_narrow() {
		return broad_or_narrow;
	}
	// setter for setting initial bounds, new function:
	// public void setBounds {
		
	// new function here: should be in the Agent class
	/* public Bag changeBounds {
		if (bounded) {
			Bag neighbors =  getMooreNeighbors(x, y, 1, SparseGrid2D.TOROIDAL, false);
			bounded = false;
		} else {
			Bag neighbors =  getMooreNeighbors(x, y, 1, SparseGrid2D.BOUNDED, false);
			bounded = true;
		}
		return Bag;
	} */
		
	public boolean isOneCellPerAgent() {
		return oneCellPerAgent;
	}
	public void setBroad_or_narrow(boolean broad_or_narrow) {
		this.broad_or_narrow = broad_or_narrow;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public int getGridWidth() {
		return gridWidth;
	}
	public void setGridWidth(int gridWidth) {
		this.gridWidth = gridWidth;
	}
	public int getGridHeight() {
		return gridHeight;
	}
	public void setGridHeight(int gridHeight) {
		this.gridHeight = gridHeight;
	}
	public SparseGrid2D getSpace() {
		return space;
	}
	public void setSpace(SparseGrid2D space) {
		this.space = space;
	}
	
	public void makeAgents() {
		for (int i = 0; i<n; i++) {
			if (i == 0) {
				int x = gridWidth / 2;
				int y = gridHeight / 2;
				
				Agent a = new Agent(x,y,dirx,diry, true);
			}
		int x = random.nextInt(gridWidth);
		int y = random.nextInt(gridHeight);
		Object o = space.getObjectsAtLocation(x,y);
		while (this.oneCellPerAgent && o != null) {
		x = random.nextInt(gridWidth);
		y = random.nextInt(gridHeight);
		o = space.getObjectsAtLocation(x, y);
		}
		int dirx = random.nextInt(3)-1;
		int diry = random.nextInt(3)-1;
		Agent a = new Agent(x,y,dirx,diry);
		space.setObjectLocation(a, x, y);
		schedule.scheduleRepeating(a);
		}
		}
	
	public void start() {
		super.start();
		space = new SparseGrid2D(gridWidth, gridHeight);
		makeAgents();
		}
		}
}
