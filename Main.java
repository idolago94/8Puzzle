
//DO NOT EDIT THIS FILE !!!
public class Main {

	public static void main(String[] args) {

		Board board = new Board("123046758");
		System.out.println("puzzle:");
		board.print();

		char type = '0';// args[1].charAt(0);
		String heuristics = "1";// args[2];

		PartialSolution ps = AStar.search(board, type, heuristics);
		System.out.println("solution:");
		ps.print();
	}

}
