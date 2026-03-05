package leetcode;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class LC_medium_200 {

    static int M, N;
    static int[][] grid;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        grid = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        int islandCount = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] || grid[i][j] == 0) continue;
                bfs(i, j);
                islandCount++;
            }
        }

        return islandCount;
    }

    private static void bfs(int r, int c) {
        Queue<int[]> position = new ArrayDeque<>();

        position.add(new int[]{r, c});
        visited[r][c] = true;

        while (!position.isEmpty()) {
            int[] cur = position.poll();

            int cr = cur[0];
            int cc = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 0 || nr >= M || nc < 0 || nc >= N) continue;
                if (visited[nr][nc] || grid[nr][nc] == 0) continue;

                visited[nr][nc] = true;
                position.add(new int[]{nr, nc});
            }
        }
    }
}
