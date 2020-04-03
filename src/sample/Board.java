package sample;

public class Board {

    public boolean [][] simulation(Rule rule, int x, int y){
        boolean board [][] = initiateBoard(y,x);
        for(int i=0; i<y-1;i++){
            for(int j=1;j<x-1; j++){
                boolean nextGenerationValue = rule.nextValue(board[i][j-1], board[i][j],board[i][j+1]);
                board[i+1][j] = nextGenerationValue;
            }
        }
        return board;
    }

    private boolean[][] initiateBoard(int y, int x) {
        boolean [][] board = new boolean [y][x];

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                board[i][j] = false;
            }
        }
        int middle = x/2;
        board[0][middle] = true;
        return board;
    }



}