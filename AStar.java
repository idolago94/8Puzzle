
import java.util.PriorityQueue;

public class AStar {

	private static Board goal = new Board("123456780");

	public static PartialSolution search(Board board, char type, String heuristics) {

		// initial frontier
		PriorityQueue<PartialSolution> frontier = new PriorityQueue<PartialSolution>();
		// INITIALIZATION CODE...

		PartialSolution boardNextStates = null;
		while (!board.arriveToGoal(goal)) {
			// A* IMPLEMENTATION
			boardNextStates = board.getNextStates();
			for (Board solution : boardNextStates.getSolutions()) {
				solution.setMisplacedTiles(type == '0' ? h_Misplaced(solution) : h_Manhattan(solution));
			}
			frontier.add(boardNextStates);

			board = boardNextStates.getBestSolution();
			board.print();
		}

		return boardNextStates;
	}

	// Manhattan distance
	public static int h_Manhattan(Board b) {
		int[][] board = b.getInstance();
		int distanceSum = 0;
		for (int i = 0; i < b.size(); i++) {
			for (int j = 0; j < b.size(); j++) {
				int item = board[i][j];
				if (item != 0) {
					int goalI = (item - 1) / 3;
					int goalJ = (item - 1) % 3;
					distanceSum += Math.abs(i - goalI) + Math.abs(j - goalJ);
				}
			}
		}
		return distanceSum;
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
