
public class Board {

	private final int SIZE = 3;

	private int[][] board = new int[SIZE][SIZE];
	private int misplaced_tiles = 0;

	// c-tor
	public Board(String s) {
		final int RADIX = 10;
		board[0][0] = Integer.parseInt(s, 0, 1, RADIX);
		board[0][1] = Integer.parseInt(s, 1, 2, RADIX);
		board[0][2] = Integer.parseInt(s, 2, 3, RADIX);
		board[1][0] = Integer.parseInt(s, 3, 4, RADIX);
		board[1][1] = Integer.parseInt(s, 4, 5, RADIX);
		board[1][2] = Integer.parseInt(s, 5, 6, RADIX);
		board[2][0] = Integer.parseInt(s, 6, 7, RADIX);
		board[2][1] = Integer.parseInt(s, 7, 8, RADIX);
		board[2][2] = Integer.parseInt(s, 8, 9, RADIX);
	}

	public Board(int[][] b) {
		this.board = b;
	}

	// print board
	public void print() {
		System.out.println(board[0][0] + " " + board[0][1] + " " + board[0][2]);
		System.out.println(board[1][0] + " " + board[1][1] + " " + board[1][2]);
		System.out.println(board[2][0] + " " + board[2][1] + " " + board[2][2]);
		System.out.println("misplaced_tiles=" + misplaced_tiles); // TODO: remove
		System.out.println();
	}

	public int[][] getInstance() {
		return this.board;
	}

	public boolean isItemEqual(Board b, int i, int j) {
		return b.getInstance()[i][j] == this.board[i][j];
	}

	public boolean arriveToGoal(Board goal) {
		boolean isCorrect = true;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (!this.isItemEqual(goal, i, j) && this.board[i][j] != 0) {
					isCorrect = false;
					break;
				}
			}
			if (!isCorrect)
				break;
		}
		return isCorrect;
	}

	public PartialSolution getNextStates() {
		PartialSolution nextStates = new PartialSolution();
		int[] zeroPosition = this.findZero();
		int x = zeroPosition[0];
		int y = zeroPosition[1];
		int[][] moves = { { x, y - 1 }, { x, y + 1 }, { x - 1, y }, { x + 1, y } };
		for (int[] pos : moves) {
			if (pos[0] >= 0 && pos[0] < SIZE && pos[1] >= 0 && pos[1] < SIZE) {
				int[][] newState = this.swap(zeroPosition, pos);
				nextStates.add(new Board(newState));
			}
		}

		return nextStates;
	}

	public int[][] copy() {
		int[][] copyArr = new int[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				copyArr[i][j] = this.board[i][j];
			}
		}
		return copyArr;
	}

	public int[][] swap(int[] a, int[] b) {
		int[][] newState = this.copy();
		int temp = newState[a[0]][a[1]];
		newState[a[0]][a[1]] = newState[b[0]][b[1]];
		newState[b[0]][b[1]] = temp;
		return newState;
	}

	public int[] findZero() {
		int[] zeroPosition = new int[2];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (this.board[i][j] == 0) {
					zeroPosition[0] = i;
					zeroPosition[1] = j;
					return zeroPosition;
				}
			}
		}
		return zeroPosition;
	}

	public int size() {
		return this.SIZE;
	}

	public void setMisplacedTiles(int n) {
		this.misplaced_tiles = n;
	}
}
