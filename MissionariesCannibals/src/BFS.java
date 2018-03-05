import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;

public class BFS {

	public static void main(String[] args) {
		System.out.println("Welcome to the Missionaries and Cannibals problem");
		BFS bfs = new BFS();
		State initialState = new State (3, 3, "left", 0, 0);
		State answer = bfs.start(initialState);
		bfs.printSolution(answer);
	}
	
	private State start(State initialState) {
		if (initialState.isGoal()) 
			return initialState;
		
		List<State> visited = new ArrayList<>();
		Queue<State> queue = new LinkedList<State>();
		queue.add(initialState);
		
		while (true) {
			if (queue.isEmpty()) 
				return null;
			
			State state = queue.poll();
			visited.add(state);
			List<State> moves = state.getMoves();
			
			for (int i = 0; i < moves.size(); i++){
				State node = moves.get(i);
				
				if (!visited.contains(node) || !queue.contains(node)) {
					if (node.isGoal()) 
						return node;
					queue.add(node);
				}
			}
		}
	}
	
	private void printSolution(State solution) {
			System.out.println("\nThe States are : ");
			List<State> path = new ArrayList<State>();
			State state = solution;
			while(state != null) {
				path.add(state);
				state = state.getParentState();
			}

			int depth = path.size() - 1;
			for (int i = depth; i >= 0; i--) {
				state = path.get(i);
				System.out.println(depth - i + " " + state.toString() + " ");
			}
	}
}
