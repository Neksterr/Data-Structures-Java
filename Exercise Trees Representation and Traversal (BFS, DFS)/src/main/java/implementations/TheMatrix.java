package implementations;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayDeque;
import java.util.Deque;

public class TheMatrix {
    private char[][] matrix;
    private char fillChar;
    private char startChar;
    private int startRow;
    private int startCol;

    public TheMatrix(char[][] matrix, char fillChar, int startRow, int startCol) {
        this.matrix = matrix;
        this.fillChar = fillChar;
        this.startRow = startRow;
        this.startCol = startCol;
        this.startChar = this.matrix[this.startRow][this.startCol];
    }

    public void solve() {
        Deque<int[]> coordinates = new ArrayDeque<>();
        coordinates.add(new int[]{startRow,startCol});
        while(!coordinates.isEmpty()){
            int[] position = coordinates.poll();
            int row = position[0];
            int col = position[1];
            this.matrix[row][col] = this.fillChar;
            if(isInBonds(row +1,col) && this.matrix[row+1][col] == this.startChar){
                coordinates.offer(new int[]{row + 1, col});
            }
            if(isInBonds(row ,col + 1) && this.matrix[row][col + 1] == this.startChar){
                coordinates.offer(new int[]{row , col + 1});
            }
            if(isInBonds(row - 1 ,col) && this.matrix[row - 1][col] == this.startChar){
                coordinates.offer(new int[]{row - 1, col});
            }
            if(isInBonds(row ,col - 1) && this.matrix[row][col - 1] == this.startChar){
                coordinates.offer(new int[]{row, col - 1});
            }
        }

    }

    private boolean isInBonds(int row, int col) {
        return !isOutOfBonds(row, col);
    }

//    DFS
//    private void fillMatrix(int row, int col) {
//        if (isOtOfBonds(row,col) || this.matrix[row][col] != startChar)
//        this.matrix[row][col] = this.fillChar;
//        System.out.println(this.toOutputString());
//        System.out.println();
//        this.fillMatrix(row + 1, col);
//        this.fillMatrix(row, col + 1);
//        this.fillMatrix(row - 1,  col);
//        this.fillMatrix(row, col - 1);
//    }

    private boolean isOutOfBonds(int row, int col) {
        return row < 0 || row >= this.matrix.length || col < 0 || col >= this.matrix[row].length;
    }

    public String toOutputString() {
       StringBuilder builder = new StringBuilder();
        for (int r = 0; r < this.matrix.length; r++) {
            for (int c = 0; c < this.matrix[r].length; c++) {
                builder.append(this.matrix[r][c]);
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
