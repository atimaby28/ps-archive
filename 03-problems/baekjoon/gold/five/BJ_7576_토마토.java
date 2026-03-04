package baekjoon.gold.five;

import java.io.*;
import java.util.*;

public class BJ_7576_토마토 {

    static int N, M, days;
    static int[][] box;
    static boolean[][] visited;
    static Queue<int[]> tomatoes;
    static int unripe = 0;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        box = new int[N][M];
        visited = new boolean[N][M];
        tomatoes = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 1) {
                    tomatoes.add(new int[]{i, j});
                    visited[i][j] = true;
                } else if (box[i][j] == 0) unripe++;
            }
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        int days = bfs();
        return unripe == 0 ? days : -1;
    }

    private static int bfs() {
        int days = 0;

        // 1: 익은 토마토, 0: 익지 않은 토마토, -1: 빈 칸
        while (!tomatoes.isEmpty()) {
            int tomatoNum = tomatoes.size();
            boolean ripened = false;

            for (int i = 0; i < tomatoNum; i++) {
                int[] tomato = tomatoes.poll();

                int cr = tomato[0];
                int cc = tomato[1];

                for (int d = 0; d < 4; d++) {
                    int nr = cr + dr[d];
                    int nc = cc + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                    if (visited[nr][nc] || box[nr][nc] == -1) continue;

                    box[nr][nc] = 1;
                    unripe--; ripened = true;
                    visited[nr][nc] = true;
                    tomatoes.offer(new int[]{nr, nc});
                }
            }
            if(ripened) days++;
        }

        return days;
    }
}
