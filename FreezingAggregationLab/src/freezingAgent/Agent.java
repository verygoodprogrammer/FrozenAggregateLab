package freezingAgent;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.util.Bag;

public class Agent implements Steppable {
	int x;
	int y;
	int dirx;
	int diry;
	// boolean contact_with_frozen;
	boolean frozen;
	
	public Agent(int x, int y, int dirx, int diry) {
		super();
		this.dirx = dirx;
		this.diry = diry;
		this.x = x;
		this.y = y;
		// this.contact_with_frozen = false; // rename later to frozen
		this.frozen = false;
	}
	
    public void placeAgent(Environment state) {
        if(state.oneCellPerAgent) {//only one agent per cell
             int tempx = state.space.stx(x + dirx);//tempx and tempy location
             int tempy = state.space.sty(y + diry);
             Bag b = state.space.getObjectsAtLocation(tempx, tempy);
             if(b == null){//if empty, agent moves to new location
                   x = tempx;
                   y = tempy;
                   state.space.setObjectLocation(this, x, y);
             }//otherwise it does not move.
        }
        else {              
             x = state.space.stx(x + dirx);
             y = state.space.sty(y + diry);
             state.space.setObjectLocation(this, x, y);
        }
   }
	
	
	public void Aggregate (Environment state) {
		/* if (!contact_with_frozen) {
			dirx = state.random.nextInt(3)-1;
			diry = state.random.nextInt(3)-1;
		} if (this.x == x && this.y == y && !this.frozen) {
			this.x = x;
			this.y = y;
		} else if (this.frozen) {
			this.frozen = true;
		} */
		
		Agent a = (Agent)state.space.getObjectsAtLocation(x,y);
		
		if (a != null) { //narrow
			if (a.frozen) {
				this.frozen = true;
			} else {
				
			}
			
		}
		else {
			dirx = state.random.nextInt(3)-1;
			diry = state.random.nextInt(3)-1;
		}
		
		if(!a.frozen) { //broad
			Bag b = state.space.getMooreNeighbors(x,y, 1, state.space.TOROIDAL, false);
			//dirx = decideX(state,b);
			//diry = decideY(state,b);
			
			dirx = state.random.nextInt(3)-1;
			diry = state.random.nextInt(3)-1;
			
			int tempx = state.space.stx(x + dirx);
			int tempy = state.space.sty(y + diry);
			
			//Agent a = (Agent)state.space.getObjectsAtLocation(tempx,tempy);
			
			if (a != null) {
				if (a.frozen) {
					placeAgent(state);
				} for (int i = 0; i < b.numObjs; i++) {
					Agent neighbor = (Agent)b.objs[i];
					if (neighbor.frozen) {
						a.frozen = true;
					}
				}
			}
		}
		
		
		
	}


	@Override
	public void step(SimState state) {
		// TODO Auto-generated method stub
		
	}
}
