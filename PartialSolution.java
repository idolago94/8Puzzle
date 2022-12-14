import java.util.ArrayList;
import java.util.List;

public class PartialSolution implements Comparable<PartialSolution> {

	// a partial solution is a legal series of successive boards
	private List<Board> partialSolution = new ArrayList<Board>();

	// c-tor
	public PartialSolution() {
	}

	public List<Board> getSolutions() {
		return this.partialSolution;
	}

	public void add(Board b) {
		this.partialSolution.add(b);
	}

	// print path
	public void print() {
		for (Board b : partialSolution) {
			b.print();
		}
	}

	public Board getBestSolution() {
		Board bestSolution = this.partialSolution.get(0);
		for (Board solution : this.partialSolution) {
			if (solution.getMisplacedTiles() < bestSolution.getMisplacedTiles())
				bestSolution = solution;
		}
		return bestSolution;
	}

	@Override
	public int compareTo(PartialSolution proc) {
		// implement this
		return 0;
	}
}
