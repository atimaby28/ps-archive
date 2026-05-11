package leetcode.medium;

import java.io.*;
import java.util.StringTokenizer;

public class LC_medium_59 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[][] result = solution(n);

        bw.write(result + "\n");
        
        bw.flush();

        bw.close();
        br.close();
    }

    public static int[][] solution(int n) {
        int[][] grid = new int[n][n];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};

        int r = 0, c = 0, dir = 0;

        for (int num = 1; num <= n * n; num++) {
            grid[r][c] = num;          // ① 먼저 현재 칸에 적기

            int nr = r + dr[dir];      // ② 다음 위치
            int nc = c + dc[dir];

            if (nr < 0 || nr >= n || nc < 0 || nc >= n || grid[nr][nc] != 0) {
                dir = (dir + 1) % 4;
                nr = r + dr[dir];
                nc = c + dc[dir];
            }

            r = nr; c = nc;            // ③ 이동
        }

        return grid;
    }

}
