package baekjoon.gold.four;

import java.io.*;
import java.util.*;

public class BJ_2573_빙산 {

    static int N, M;
    static int[][] map;
    static Queue<Iceberg> icebergs;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        icebergs = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] != 0) icebergs.offer(new Iceberg(i, j, map[i][j]));
            }
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        int time = 0;

        while (true) {
            // 1. 연결 요소 세기
            boolean[][] visited = new boolean[N][M];
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j] || map[i][j] == 0) continue;
                    bfs(i, j, visited);
                    count++;
                }
            }

            // 2. 분리됐으면 답
            if (count >= 2) return time;
            // 3. 다 녹았으면 0
            if (count == 0) return 0;

            // 4. 녹이기 (세기 끝난 후)
            melt();
            time++;
        }

    }

    private static void bfs(int r, int c, boolean[][] visited) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r, c});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (visited[nr][nc] || map[nr][nc] == 0) continue;

                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }
    }

    private static void melt() {
        int[][] adj = new int[N][M]; // 인접 바다 수

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;
                for (int d = 0; d < 4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                    if (map[nr][nc] == 0) adj[i][j]++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = Math.max(0, map[i][j] - adj[i][j]);
            }
        }
    }


    static class Iceberg {
        int r, c, size;

        public Iceberg(int r, int c, int size) {
            this.r = r;
            this.c = c;
            this.size = size;
        }
    }

}
