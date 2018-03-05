import java.util.ArrayList;
import java.util.List;

public class State {

		private String boat;
		private int cannibalLeft;
		private int missionaryLeft;
		private int cannibalRight;
		private int missionaryRight;
		private State parentState;

		public State(int cleft, int mleft, String boat, int cright, int mright) {
			this.boat = boat;
			this.cannibalLeft = cleft;
			this.missionaryLeft = mleft;
			this.cannibalRight = cright;
			this.missionaryRight = mright;
		}

		public boolean isGoal() {
			return (cannibalLeft == 0 && missionaryLeft == 0);
		}

		public boolean isValid() {
			return (missionaryLeft >= 0 && missionaryRight >= 0 && cannibalLeft >= 0 && cannibalRight >= 0
		               && (missionaryLeft == 0 || missionaryLeft >= cannibalLeft)
		               && (missionaryRight == 0 || missionaryRight >= cannibalRight)) ;
		}

		private void add(List<State> successors, State newState) {
			if (newState.isValid()) {
				newState.setParentState(this);
				successors.add(newState);
			}
		}

		public State getParentState() {
			return parentState;
		}

		public void setParentState(State parentState) {
			this.parentState = parentState;
		}

		public String toString() {
				return "{" + cannibalLeft + "," + missionaryLeft + ", " + boat + ", " + cannibalRight + "," + missionaryRight + "}";
	     }
		
		public List<State> getMoves() {
			List<State> moves = new ArrayList<State>();
			if (boat == "left") {
				add(moves, new State(cannibalLeft, missionaryLeft - 2, "right", cannibalRight, missionaryRight + 2)); 
				add(moves, new State(cannibalLeft - 2, missionaryLeft, "right", cannibalRight + 2, missionaryRight));
				add(moves, new State(cannibalLeft - 1, missionaryLeft - 1, "right", cannibalRight + 1, missionaryRight + 1)); 
				add(moves, new State(cannibalLeft, missionaryLeft - 1, "right", cannibalRight, missionaryRight + 1)); 
				add(moves, new State(cannibalLeft - 1, missionaryLeft, "right", cannibalRight + 1, missionaryRight));
			} else {
				add(moves, new State(cannibalLeft, missionaryLeft + 2, "left", cannibalRight, missionaryRight - 2)); 
				add(moves, new State(cannibalLeft + 2, missionaryLeft, "left", cannibalRight - 2, missionaryRight)); 
				add(moves, new State(cannibalLeft + 1, missionaryLeft + 1, "left", cannibalRight - 1, missionaryRight - 1)); 
				add(moves, new State(cannibalLeft, missionaryLeft + 1, "left", cannibalRight, missionaryRight - 1)); 
				add(moves, new State(cannibalLeft + 1, missionaryLeft, "left", cannibalRight - 1, missionaryRight)); 
			}
			return moves;
		}
}
