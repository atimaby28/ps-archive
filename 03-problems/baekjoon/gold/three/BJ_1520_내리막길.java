package baekjoon.gold.three;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1520_내리막길 {

    static int N, M;
    static int[][] map;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        int[][] dp = new int[N][M];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        return dfs(0, 0, dp);
    }

    private static int dfs(int r, int c, int[][] dp) {
        if (r == N - 1 && c == M - 1) return 1;

        if (dp[r][c] != -1) return dp[r][c];

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
            if (map[nr][nc] >= map[r][c]) continue;

            dp[r][c] += dfs(nr, nc, dp);
        }

        return dp[r][c];
    }
}
