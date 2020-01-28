package freezingAgent;

import java.awt.Color;

import sim.engine.SimState;
import sweep.GUISimState;

public class GUI extends  GUISimState {
	public GUI(SimState state, String spaceName, int gridWidth, int gridHeight, Color backdrop, Color agentDefaultColor, boolean agentPortrayal) {
		super(state, spaceName, gridWidth, gridHeight, backdrop, agentDefaultColor, agentPortrayal);
		
	}
	
	public static void main(String[] args) {
		GUI.initialize(Environment.class, GUI.class, 400, 400, Color.white, Color.red, true, "space");
	}
}
