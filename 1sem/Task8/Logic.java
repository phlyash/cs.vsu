import java.util.*;

public class Logic {
    public static int[][] solve(int[][] array) {
        int[][] answer;

        int[][] indexesToDelete = findIndexesToDelete(array);
        if (indexesToDelete.length < 2) return new int[][] {{}};
        int[] rows = indexesToDelete[0];
        int[] cols = indexesToDelete[1];
        try {
            answer = new int[array.length - rows.length][array[0].length - cols.length];
        }
        catch (NegativeArraySizeException e) {
            return new int[][] {{}};
        }
        int itRow = 0, itCol = 0;
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[i].length; j++) {
                if (arrayNotContainsNum(rows, i) && arrayNotContainsNum(cols, j)) {
                    answer[itRow][itCol] = array[i][j];
                    itCol++;
                }
            }
            if (itCol != 0) itRow++;
            itCol = 0;
        }

        return answer;
    }
    public static int[][] findIndexesToDelete(int[][] array) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        SortedSet<Integer> rowsMax = new TreeSet<>();
        SortedSet<Integer> colsMax = new TreeSet<>();
        SortedSet<Integer> rowsMin = new TreeSet<>();
        SortedSet<Integer> colsMin = new TreeSet<>();

        for(int i = 0; i < array.length; i++) {
            if (array[i] == null) return new int[][] {{}};
            for(int j = 0; j < array[i].length; j++) {
                if (array[i][j] > max) {
                    max = array[i][j];
                    rowsMax = new TreeSet<>();
                    colsMax = new TreeSet<>();
                    rowsMax.add(i);
                    colsMax.add(j);
                } else if (array[i][j] == max) {
                    rowsMax.add(i);
                    colsMax.add(j);
                }
                if(array[i][j] < min) {
                    min = array[i][j];
                    rowsMin = new TreeSet<>();
                    colsMin = new TreeSet<>();
                    rowsMin.add(i);
                    colsMin.add(j);
                }
                else if (array[i][j] == min) {
                    rowsMin.add(i);
                    colsMin.add(j);
                }
            }
        }
        SortedSet<Integer> rows = new TreeSet<>(), cols = new TreeSet<>();
        rows.addAll(rowsMax);
        rows.addAll(rowsMin);
        cols.addAll(colsMax);
        cols.addAll(colsMin);

        return new int[][] {rows.stream().mapToInt(i->i).toArray(), cols.stream().mapToInt(i->i).toArray()};
    }
    public static boolean arrayNotContainsNum(int[] array, int num) {
        for(int i: array) {
            if (i == num) return false;
        }
        return true;
    }
}
