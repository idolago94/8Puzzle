
import java.util.PriorityQueue;

public class AStar {

	private static Board goal = new Board("123456780"); // TODO: move zero to start

	public static PartialSolution search(Board board, char type, String heuristics) {

		// initial frontier
		PriorityQueue<PartialSolution> frontier = new PriorityQueue<PartialSolution>();
		// INITIALIZATION CODE...

		while (!board.arriveToGoal(goal)) {
			// A* IMPLEMENTATION
			PartialSolution boardNextStates = board.getNextStates();
			for (Board solution : boardNextStates.getSolutions()) {
				solution.setMisplacedTiles(h_Misplaced(solution));
			}
			frontier.add(boardNextStates);

			// TODO: override this.board with the best solution
		}

		return frontier.poll();
	}

	// Manhattan distance
	public static int h_Manhattan(Board b) {
		// IMPLEMENT
		return 0;
	}

	// misplaced tiles
	public static int h_Misplaced(Board b) {
		int misplaced = 0;
		for (int i = 0; i < b.size(); i++) {
			for (int j = 0; j < b.size(); j++) {
				if (!b.isItemEqual(goal, i, j) && b.getInstance()[i][j] != 0)
					misplaced++;
			}
		}
		return misplaced;
	}
}
