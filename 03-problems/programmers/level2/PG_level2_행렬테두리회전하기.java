package programmers.level2;

import java.io.*;
import java.util.StringTokenizer;

public class PG_level2_행렬테두리회전하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int rows = Integer.parseInt(st.nextToken());
        int columns = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(st.nextToken());

        int[][] queries = new int[k][4];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            queries[i][0] = Integer.parseInt(st.nextToken());
            queries[i][1] = Integer.parseInt(st.nextToken());
            queries[i][2] = Integer.parseInt(st.nextToken());
            queries[i][3] = Integer.parseInt(st.nextToken());
        }

        int[] result = solution(rows, columns, queries);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        int[][] map = new int[rows][columns];

        init(map);

        for (int k = 0; k < queries.length; k++) {
            answer[k] = rotate(map, queries[k][0] - 1, queries[k][1] - 1, queries[k][2] - 1, queries[k][3] - 1);
        }

        return answer;
    }

    private static void init(int[][] map) {
        int count = 1;

        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                map[r][c] = count++;
            }
        }
    }

    private static int rotate(int[][] map, int startRow, int startCol, int endRow, int endCol) {
        int temp = map[startRow][endCol];
        int min = temp;

        // top
        for (int i = endCol; i > startCol; i--) {
            map[startRow][i] = map[startRow][i - 1];
            min = Math.min(min, map[startRow][i]);
        }

        // left
        for (int i = startRow; i < endRow; i++) {
            map[i][startCol] = map[i + 1][startCol];
            min = Math.min(min, map[i][startCol]);
        }

        // bottom
        for (int j = startCol; j < endCol; j++) {
            map[endRow][j] = map[endRow][j + 1];
            min = Math.min(min, map[endRow][j]);
        }

        // right
        for (int i = endRow; i > startRow; i--) {
            map[i][endCol] = map[i - 1][endCol];
            min = Math.min(min, map[i][endCol]);
        }

        map[startRow + 1][endCol] = temp;

        return min;
    }
}
