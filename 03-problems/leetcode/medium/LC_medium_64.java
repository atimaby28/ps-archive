package leetcode.medium;

import java.io.*;
import java.util.StringTokenizer;

public class LC_medium_64 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] grid = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = minPathSum(grid);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int minPathSum(int[][] grid) {
        return 0;
    }
}
