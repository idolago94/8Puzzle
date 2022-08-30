import java.util.ArrayList;

public class Puzzle {
    private final String goal = "123456780";
    private final int[][] moveOptions = {
            { 1, 3 }, // 0
            { 0, 2, 4 }, // 1
            { 1, 5 }, // 2
            { 0, 6, 4 }, // 3
            { 1, 3, 5, 7 }, // 4
            { 2, 4, 8 }, // 5
            { 3, 7 }, // 6
            { 6, 4, 8 }, // 7
            { 5, 7 } // 8
    };
    private String state = "123046758";
    private int g = 0;

    public static void main(String[] args) {
        Puzzle pz = new Puzzle();
        ArrayList nextStates = pz.getNextLevelOptions();
        System.out.println(nextStates.toString());
    }

    private ArrayList getNextLevelOptions() {
        ArrayList nextStates = new ArrayList<String>();
        int zeroPosition = this.state.indexOf('0');
        int[] options = this.moveOptions[zeroPosition];
        for (int opt : options) {
            System.out.println(opt);
            String newState = this.state.replace('0', '_');
            newState = newState.replace(this.state.charAt(opt), '0');
            newState = newState.replace('_', this.state.charAt(opt));
            System.out.println(newState);
            nextStates.add(newState);
        }
        return nextStates;
    }

    private int getHScore(String currentState) {
        int missedPlace = 0;
        for (int i = 0; i < currentState.length(); i++) {
            if (currentState.charAt(i) != this.goal.charAt(i) && currentState.charAt(i) != '0') {
                missedPlace++;
            }
        }
        return missedPlace;
    }
}
